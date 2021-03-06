package EcoBikeRental.Subsystem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import EcoBikeRental.Dto.BodyToConvertMD5Dto;
import EcoBikeRental.Dto.RequestProcessTransactionDto;
import EcoBikeRental.Dto.RequestResetBalanceDto;
import EcoBikeRental.Dto.RequestTransactionDto;
import EcoBikeRental.Dto.TransactionToConvertMD5Dto;
/**
 * Description: Interbank Connection class execute all action of calling API Interbank
 *
 */
@Service
public class InterbankController implements InterbankInterface{
	//init httpclient variable
	CloseableHttpClient httpclient = HttpClients.createDefault();
	
	// init some const variable to connect with interbank
	private final String URL_PROCESS_TRANSACTION = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	private final String URL_RESET_BALANCE = "https://ecopark-system-api.herokuapp.com/api/card/reset-balance";
	private final String VERSION = "1.0.1";
	//private final String CARD_CODE = "kstn_group12_2021";
	private final String CVV_CODE = "936";
	private final String DATE_EXPIRED = "1125";
	//private final String OWNER = "Group 12";
	private final String APP_CODE = "Cn2iBu0JxIc=";
	private final String SECRET_KEY = "BTrW4tn1vwY=";
	
	@Autowired
	InterbankBoundary boundary;
	
	/**
	 * Description: Method call API process transaction of Interbank
	 * @param command: type of process pay or refund
	 * @param amount: the money amount to transaction
	 * @param transactionContent: content Transaction
	 * @return JsonNode: the response of api process transaction
	 */
	public JsonNode processTransaction(String command, Long amount, String transactionContent, String cardCode, String owner) {
		try {
			// build the body url
			RequestProcessTransactionDto body = new RequestProcessTransactionDto();
			body.setVersion(VERSION);
			RequestTransactionDto transaction = new RequestTransactionDto();
			transaction.setCommand(command);
			transaction.setAmount(amount);
			transaction.setCardCode(cardCode);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			transaction.setCreatedAt(dtf.format(now).toString());
			transaction.setCvvCode(CVV_CODE);
			transaction.setDateExpired(DATE_EXPIRED);
			transaction.setOwner(owner);
			transaction.setTransactionContent(transactionContent);
			body.setTransaction(transaction);
			body.setAppCode(APP_CODE);
			
			//convert hashkey md5
			BodyToConvertMD5Dto bodyHashCode = new BodyToConvertMD5Dto();
			bodyHashCode.setSecretKey(SECRET_KEY);
			
			TransactionToConvertMD5Dto bodyTransaction = new TransactionToConvertMD5Dto();
			bodyTransaction.setCommand(command);
			bodyTransaction.setAmount(amount);
			bodyTransaction.setCardCode(cardCode);
			bodyTransaction.setCvvCode(CVV_CODE);
			bodyTransaction.setDateExpired(DATE_EXPIRED);
			bodyTransaction.setOwner(owner);
			bodyTransaction.setTransactionContent(transactionContent);
			bodyHashCode.setTransaction(bodyTransaction);
			
			ObjectMapper mapper = new ObjectMapper();
			
			String hashCode = MD5String(mapper.writeValueAsString(bodyHashCode));
			
			body.setHashCode(hashCode);
			
			JsonNode resultJson = boundary.post(URL_PROCESS_TRANSACTION, mapper.writeValueAsString(body));
			return resultJson;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Description: method call api reset balance Interbank
	 * @return JsonNode: the response of api reset balance
	 */
	public JsonNode resetBalance(String cardCode, String owner) {
		try {			
			//set body
			RequestResetBalanceDto body = new RequestResetBalanceDto();
			body.setCardCode(cardCode);
			body.setCvvCode(CVV_CODE);
			body.setDateExpired(DATE_EXPIRED);
			body.setOwner(owner);
			
			ObjectMapper mapper = new ObjectMapper();
			
			// convert response to json
			JsonNode resultJson = boundary.post(URL_RESET_BALANCE, mapper.writeValueAsString(body));
			return resultJson;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//convert a string to MD5 Hash
	public String MD5String(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(input.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}

}

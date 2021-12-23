package EcoBikeRental.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import EcoBikeRental.Dto.BodyToConvertMD5Dto;
import EcoBikeRental.Dto.RequestProcessTransactionDto;
import EcoBikeRental.Dto.RequestResetBalanceDto;
import EcoBikeRental.Dto.RequestTransactionDto;
import EcoBikeRental.Dto.TransactionToConvertMD5Dto;
@Service
public class InterbankConnection {
	CloseableHttpClient httpclient = HttpClients.createDefault();
	
	private final String URL_PROCESS_TRANSACTION = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	private final String URL_RESET_BALANCE = "https://ecopark-system-api.herokuapp.com/api/card/reset-balance";
	private final String VERSION = "1.0.1";
	private final String CARD_CODE = "kstn_group12_2021";
	private final String CVV_CODE = "936";
	private final String DATE_EXPIRED = "1125";
	private final String OWNER = "Group 12";
	private final String APP_CODE = "Cn2iBu0JxIc=";
	private final String SECRET_KEY = "Cn2iBu0JxIc=";
	
	public JsonNode processTransaction(String command, Long amount, String transactionContent) {
		try {
			HttpPatch post = new HttpPatch(URL_PROCESS_TRANSACTION);
			
			RequestProcessTransactionDto body = new RequestProcessTransactionDto();
			body.setVersion(VERSION);
			RequestTransactionDto transaction = new RequestTransactionDto();
			transaction.setCommand(command);
			transaction.setAmount(amount);
			transaction.setCardCode(CARD_CODE);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			transaction.setCreatedAt(dtf.format(now).toString());
			transaction.setCvvCode(CVV_CODE);
			transaction.setDateExpired(DATE_EXPIRED);
			transaction.setOwner(OWNER);
			transaction.setTransactionContent(transactionContent);
			body.setTransaction(transaction);
			body.setAppCode(APP_CODE);
			
			BodyToConvertMD5Dto bodyHashCode = new BodyToConvertMD5Dto();
			bodyHashCode.setSecretKey(SECRET_KEY);
			TransactionToConvertMD5Dto bodyTransaction = new TransactionToConvertMD5Dto();
			bodyTransaction.setCommand(command);
			bodyTransaction.setAmount(amount);
			bodyTransaction.setCardCode(CARD_CODE);
			bodyTransaction.setCvvCode(CVV_CODE);
			bodyTransaction.setDateExpired(DATE_EXPIRED);
			bodyTransaction.setOwner(OWNER);
			bodyTransaction.setTransactionContent(transactionContent);
			bodyHashCode.setTransaction(bodyTransaction);
			
			ObjectMapper mapper = new ObjectMapper();
			
			String hashCode = MD5String(mapper.writeValueAsString(bodyHashCode));
			
			body.setHashCode(hashCode);
			
			post.setHeader("Content-Type", "application/json");
			post.setEntity(new StringEntity(mapper.writeValueAsString(body)));
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(post);
			String result = EntityUtils.toString(response.getEntity());
			
			JsonNode resultJson = mapper.readTree(result);
			return resultJson;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public JsonNode resetBalance() {
		try {
			HttpPatch post = new HttpPatch(URL_RESET_BALANCE);
			
			RequestResetBalanceDto body = new RequestResetBalanceDto();
			body.setCardCode(CARD_CODE);
			body.setCvvCode(CVV_CODE);
			body.setDateExpired(DATE_EXPIRED);
			body.setOwner(OWNER);
			
			ObjectMapper mapper = new ObjectMapper();
			
			post.setHeader("Content-Type", "application/json");
			post.setEntity(new StringEntity(mapper.writeValueAsString(body)));
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(post);
			String result = EntityUtils.toString(response.getEntity());
			
			JsonNode resultJson = mapper.readTree(result);
			return resultJson;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
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
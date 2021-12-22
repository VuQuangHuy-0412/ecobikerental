package EcoBikeRental.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import EcoBikeRental.Dto.RequestProcessTransactionDto;
import EcoBikeRental.Dto.RequestResetBalanceDto;
import EcoBikeRental.Dto.RequestTransactionDto;
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
	
	public void processTransaction(String command, Long amount, String transactionContent) {
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
			body.setHashCode("hashCode");
			
			ObjectMapper mapper = new ObjectMapper();
			
			post.setHeader("Content-Type", "application/json");
			post.setEntity(new StringEntity(mapper.writeValueAsString(body)));
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(post);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void resetBalance() {
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
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

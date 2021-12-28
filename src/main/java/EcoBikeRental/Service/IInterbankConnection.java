package EcoBikeRental.Service;

import com.fasterxml.jackson.databind.JsonNode;

public interface IInterbankConnection {

	public JsonNode processTransaction(String command, Long amount, String transactionContent, String cardCode, String owner);
	public JsonNode resetBalance(String cardCode, String owner);
}

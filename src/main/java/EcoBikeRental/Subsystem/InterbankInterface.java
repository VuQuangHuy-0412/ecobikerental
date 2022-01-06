package EcoBikeRental.Subsystem;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Interface Interbank Connection
 *
 */
public interface InterbankInterface {

	public JsonNode processTransaction(String command, Long amount, String transactionContent, String cardCode, String owner);
	public JsonNode resetBalance(String cardCode, String owner);
}

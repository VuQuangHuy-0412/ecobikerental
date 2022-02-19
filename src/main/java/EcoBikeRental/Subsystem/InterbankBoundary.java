package EcoBikeRental.Subsystem;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
/**
 * Class Boundary contain general method call api 
 *
 */
public class InterbankBoundary {
	
	/**
	 * Method post
	 * @param url
	 * @param body
	 * @return result
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public JsonNode post(String url, String body) throws ClientProtocolException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HttpPatch post = new HttpPatch(url);//set header patch method
		post.setHeader("Content-Type", "application/json");
		
		//set body
		post.setEntity(new StringEntity(body));
		
		//get the response
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(post);
		String result = EntityUtils.toString(response.getEntity());
		
		//convert respronse to json
		JsonNode resultJson = mapper.readTree(result);
		return resultJson;
	}
}

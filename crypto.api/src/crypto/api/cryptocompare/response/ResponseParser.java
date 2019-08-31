package crypto.api.cryptocompare.response;

import java.io.IOException;

import org.apache.log4j.Logger;

/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ResponseParser {
	private Request request;
	private boolean successful;
	private String responseString;
	private final OkHttpClient client = new OkHttpClient();
	private static final Logger log = Logger.getLogger(ResponseParser.class);

	public ResponseParser() {
	}
	
	/** Set the request object to parse a response string from
	 * 
	 * @param okhttp3.Request
	 */
	public void sendRequest(Request request){
	  this.request = request;
	}
	
	/** Parse a response string
	 * 
	 * @return String response
	 */
	public String parseResponse(){
		Response response = null;
		try {
			response = client.newCall(request).execute();
			responseString = response.body().string();
			successful = response.isSuccessful();
			response.close();
			
			if(successful)
				return responseString;
			else
				return null;
		} catch (IOException e) {
			log.error("Response Parsing Error - Message: " + response.message());
			return null;
		}
	}
}

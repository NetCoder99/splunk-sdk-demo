import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SplunkSendEvents {

	// curl -k http://192.168.184.128:8088/services/collector -H 'Authorization: Splunk 1ed364cd-125c-4fef-8c6b-1b35a05158bb' -d '{"event": "Hello, world!", "sourcetype": "manual"}'
	
	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();

	private static final String SPLUNK_URL        = "http://192.168.184.128:8088/services/collector";
	private static final String SPLUNK_TOKEN      = "Splunk 1ed364cd-125c-4fef-8c6b-1b35a05158bb";

	private static final String SPLUNK_HOST       = "localhost";
	private static final String SPLUNK_SOURCE     = "JavaDemo";
	private static final String SPLUNK_SOURCETYPE = "java:logging";

	//<property name="SPLUNK_URL"		   value="http://192.168.184.128:8088/" />
	//<property name="SPLUNK_TOKEN"	       value="1ed364cd-125c-4fef-8c6b-1b35a05158bb" />
	//<property name="SPLUNK_HOST"         value="localhost" />
	//<property name="SPLUNK_SOURCE"       value="JavaDemo" />
	//<property name="SPLUNK_SOURCETYPE"   value="java:logging" />
	
	String postHelloWorld() throws IOException {
	  RequestBody body = RequestBody.create(JSON, "{\"event\": \"Hello, world from postHelloWorld!\", \"sourcetype\": \"manual\"}");
	  Request request = new Request.Builder()
	      .url(SPLUNK_URL)
	      .post(body)
	      .header("Authorization", SPLUNK_TOKEN)
	      .build();
	  try (Response response = client.newCall(request).execute()) {
	    return response.body().string();
	  }
	}
}

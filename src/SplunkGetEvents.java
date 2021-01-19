import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.splunk.Args;
import com.splunk.Event;
import com.splunk.ResultsReaderXml;
import com.splunk.Service;
import com.splunk.ServiceArgs;

public class SplunkGetEvents {

	public HashMap<String, String> GetEvents(){
		
		
		// Create login parameters. We suggest finding
		// a better way to store these than hard coding
		// them in your program for production code.
		ServiceArgs serviceArgs = new ServiceArgs();
		serviceArgs.setUsername("splunk");
		serviceArgs.setPassword("John$0001");
		serviceArgs.setHost("192.168.184.128");
		serviceArgs.setPort(8089);

		// Create a Service instance and log in with the argument map
		Service service = Service.connect(serviceArgs);
		
		// Set the parameters for the search
		Args oneshotSearchArgs = new Args();
		// For a full list of options, see:
		//
		//     http://docs.splunk.com/Documentation/Splunk/latest/RESTAPI/RESTsearch#POST_search.2Fjobs

		// oneshotSearchArgs.put("earliest_time", "now-1w");
		// oneshotSearchArgs.put("latest_time",   "now");

		InputStream resultsStream = service.oneshotSearch("search * | head 5", oneshotSearchArgs);

		try {
			ResultsReaderXml resultsReader = new ResultsReaderXml(resultsStream);

			for (Event event : resultsReader) {
				// Process each event
				for (String key: event.keySet()) {
				   System.out.println(key + ": " + event.get(key));
				}
				System.out.println("");
			}

			resultsReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return new HashMap<String, String>();
	}
	
}

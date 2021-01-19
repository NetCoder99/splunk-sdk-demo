import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.splunk.logging.HttpEventCollectorErrorHandler;
import com.splunk.logging.HttpEventCollectorEventInfo;

public class Program {
	private static final Logger logger = LoggerFactory.getLogger(Program.class);
	
	public static void main(String[] args) {
		try
		{
//			HttpEventCollectorErrorHandler.onError(new HttpEventCollectorErrorHandler.ErrorCallback() 
//			{
//				@Override
//				public void error(List<HttpEventCollectorEventInfo> data, Exception ex) 
//				{
//					System.out.println("HttpEventCollectorErrorHandler.onError fired:");
//					ex.printStackTrace();	
//				}
//			});
			
			
			logger.info("------ Program starting ------");
			
			
			String postResponse = new SplunkSendEvents().postHelloWorld();
			logger.info("postResponse:" + postResponse);

			
			logger.info("------ Program Finished ------");
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();	
		}
	}
}

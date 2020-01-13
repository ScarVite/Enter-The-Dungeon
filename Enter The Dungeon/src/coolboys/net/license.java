package coolboys.net;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class license {
	
	
	private final static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static boolean validatekey(String liz) {
		System.out.println(liz);
		HttpGet request = new HttpGet("http://82.165.163.17:6/api/validatekey?key=" + liz);
		try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                String result;
				try {
//	Ich weiß, das ich nen boolean in nen string umwandel und dann wieder zurück, aber ohne das gehts irgendwie nicht
					result = EntityUtils.toString(entity);
					if(Boolean.parseBoolean(result) == true) {
//	In Speicherfile activated = true setzen
					}
					return Boolean.parseBoolean(result);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        } catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.exit(1);
		return false;
		}
	
}

package coolboys.net;

import java.net.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class license {
	
	public static boolean initialized = false;
	
	private final static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	public static void main(String args[]) {
		Scanner scamer = new Scanner(System.in);
		System.out.println("Bitte geben sie Ihren Lizenschlüssel ein");
		String liz = scamer.nextLine();
		System.out.println(validatekey(liz));
	}

	public static boolean validatekey(String liz) {
		System.out.println(liz);
		HttpGet request = new HttpGet("http://82.165.163.17/api/validatekey?key=" + liz);
		try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                String result;
				try {
					result = EntityUtils.toString(entity);
					if(Boolean.parseBoolean(result) == true) {
						initialized = true;
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

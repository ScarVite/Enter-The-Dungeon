package coolboys.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

public class Networking {
//	Ich weiß, das ich nen boolean in nen string umwandel und dann wieder zurück, aber ohne das gehts irgendwie nicht
	
	
	private final static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static boolean validatekey(String key) {
		HttpGet request = new HttpGet("http://82.165.163.17:6/api/validatekey?key=" + key);
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
	
	public static boolean updateLeaderboard(String username, int score, int userId) {
		HttpGet request = new HttpGet("http://82.165.163.17:6/api/updateleaderboard?username=" + username +"&score=" + score + "&userId=" + userId) ;
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

	public static  JSONArray getLeaderboard() {
		HttpGet request = new HttpGet("http://82.165.163.17:6/api/getleaderboard");
		try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);
            if (entity != null) {
				try {
					JSONArray jsonarray = new JSONArray(EntityUtils.toString(entity));
					System.out.println(jsonarray.getJSONObject(0));
					return jsonarray;
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
		return null;
		}
	
	public static  boolean addUser(String Email, String Username, String Password) {
		String HashedPassword = " ";
		try {
			HashedPassword = hashPW(Password);
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(HashedPassword == " ") {
			System.exit(2);
		}
		HttpPost post = new HttpPost("http://82.165.163.17:6/api/addUser");
		List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", Username));
        urlParameters.add(new BasicNameValuePair("password", HashedPassword));
        urlParameters.add(new BasicNameValuePair("email", Email));
        try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
        	System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);
            if (entity != null) {
                String result;
				try {
					result = EntityUtils.toString(entity);
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
	
	public static  JSONArray login(String Email, String Password) {
		String HashedPassword = " ";
		try {
			HashedPassword = hashPW(Password);
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(HashedPassword == " ") {
			System.exit(2);
		}
		HttpPost post = new HttpPost("http://82.165.163.17:6/api/login");
		List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("email", Email));
        urlParameters.add(new BasicNameValuePair("password", HashedPassword));
        try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
        	System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);
            if (entity != null) {
				try {
//					jsonarray[0] = entweder true oder false, bei true is an [1] ein object bei false null
					JSONArray jsonarray = new JSONArray(EntityUtils.toString(entity));
					System.out.println(jsonarray.getJSONObject(0));
					return jsonarray;
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
		return null;
		}
	
	protected static String hashPW(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
		String hex = String.format("%064x", new BigInteger(1, digest));
		System.out.println(hex);
		return hex;
	}
	
}

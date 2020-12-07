package enterTheDungeon.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import enterTheDungeon.game.Oberklassen.User;

public class Networking {

	private static String userToken = null;
	private static String baseUrl = "https://api.scarvite.de/etd";

	private final static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static boolean validatekey(String key) {
		if (userToken == null)
			userToken = new User().getToken();
		HttpGet request = new HttpGet(baseUrl + "/validatekey?key=" + key);
		request.setHeader("authorization", userToken);
		System.out.println(userToken);
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			System.out.println(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();
			Header headers = entity.getContentType();
			System.out.println(headers);
			if (entity != null) {
				try {
					return Boolean.parseBoolean(EntityUtils.toString(entity));
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

	public static boolean updateLeaderboard(String username, int score) {
		if (userToken == null)
			userToken = new User().getToken();
		System.out.println("hier");
		HttpPost post = new HttpPost(baseUrl + "/updateleaderboard");
		post.setHeader("authorization", userToken);
		/*
		 * List<NameValuePair> urlParameters = new ArrayList<>(); urlParameters.add(new
		 * BasicNameValuePair("username", username)); urlParameters.add(new
		 * BasicNameValuePair("score", Integer.toString(score))); urlParameters.add(new
		 * BasicNameValuePair("token", "33317-200-10-5-5-992")); try {
		 * post.setEntity(new UrlEncodedFormEntity(urlParameters)); } catch
		 * (UnsupportedEncodingException e2) { // TODO Auto-generated catch block
		 * e2.printStackTrace(); }
		 */
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {
			System.out.println(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();
			Header headers = entity.getContentType();
			System.out.println(headers);
			if (entity != null) {
				try {
					return true;
				} catch (ParseException e) {
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

	public static JSONArray getLeaderboard() {
		if (userToken == null)
			userToken = new User().getToken();
		HttpGet request = new HttpGet(baseUrl + "/getleaderboard");
		request.setHeader("authorization", userToken);
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			System.out.println(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();
			Header headers = entity.getContentType();
			System.out.println(headers);
			if (entity != null) {
				try {
					JSONParser parser = new JSONParser();
					JSONArray jsonarray = (JSONArray) parser.parse(EntityUtils.toString(entity));
					System.out.println(jsonarray.get(0));
					return jsonarray;
				} catch (org.json.simple.parser.ParseException e) {
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

	public static void addUser(String email, String username, String password) {
		String hashedPassword = " ";
		try {
			hashedPassword = hashPW(password);
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.exit(2);
		}
		if (hashedPassword == " ") {
			System.exit(2);
		}
		HttpPost post = new HttpPost(baseUrl + "/addUser");
		post.setHeader("authorization", userToken);
		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("password", hashedPassword));
		urlParameters.add(new BasicNameValuePair("email", email));
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
					JSONParser parser = new JSONParser();
					JSONObject UserObj;
					UserObj = (JSONObject) parser.parse(EntityUtils.toString(entity));
					User.setUser(UserObj);
					if (userToken == null)
						userToken = new User().getToken();
				} catch (ParseException | org.json.simple.parser.ParseException | IOException e) {
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
	}

	public static void login(String email, String password) {
		String hashedPassword = password;
		/*
		 * try { hashedPassword = hashPW(password); } catch (NoSuchAlgorithmException
		 * e2) { // TODO Auto-generated catch block e2.printStackTrace(); }
		 */
		if (hashedPassword == " ") {
			System.exit(2);
		}
		HttpPost post = new HttpPost(baseUrl + "/login");
		post.setHeader("authorization", userToken);
		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters.add(new BasicNameValuePair("email", email));
		urlParameters.add(new BasicNameValuePair("password", hashedPassword));
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
					JSONParser parser = new JSONParser();
					JSONObject UserObj = (JSONObject) parser.parse(EntityUtils.toString(entity));
					User.setUser(UserObj);
					if (userToken == null)
						userToken = new User().getToken();
				} catch (org.json.simple.parser.ParseException | ParseException e) {
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
	}

	protected static String Tokengen(int ziel, int lenght, int max) {

		int a = 0;
		while (a != ziel) {
			Random random = new Random();
			int[] value = new int[lenght];

			for (int i = 0; i < value.length; i++) {
				value[i] = random.nextInt(max);
			}
			String strArray[] = new String[value.length];

			for (int i = 0; i < value.length; i++)
				strArray[i] = String.valueOf(value[i]);
			a = 0;
			for (int i = 0; i < value.length; i++) {
				a = a + value[i];

			}

			if (a == ziel) {
				return Arrays.toString(strArray);
			}
		}
		return null;
	}

	private static String hashPW(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
		String hashedPw = String.format("%064x", new BigInteger(1, digest));
		System.out.println(hashedPw);
		return hashedPw;
	}

}
package enterTheDungeon.api;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
import enterTheDungeon.resource.Filesystem;

public class Networking {

	private static String userToken = null;
	private static String baseUrl = "https://api.scarvite.de/etd";
	private static boolean connected = false;

	private final static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static boolean validatekey(String key) {
		if (userToken == null)
			userToken = new User().getToken();
		HttpGet request = new HttpGet(baseUrl + "/validatekey?key=" + key);
		request.setHeader("authorization", userToken);
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

	public static void  updateLeaderboard(String username, int score) {
		if (userToken == null)
			userToken = new User().getToken();
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
					System.out.println(response.getStatusLine());
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
					if (UserObj.get("error") == null) { 
						User.setUserRegister(UserObj);
						Popup.info("User Created", "Sucess");
					}
					else {
						JSONObject error = (JSONObject) UserObj.get("error");
						Popup.error(error.get("message") + " Code: " + error.get("code"), "Something Went Wrong");
					}
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
					System.out.println(UserObj.get("error"));
					if (UserObj.get("error") == null)
						User.setUser(UserObj);
					else {
						JSONObject error = (JSONObject) UserObj.get("error");
						Popup.error(error.get("message") + " Code: " + error.get("code"), "Something Went Wrong");
					}
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

	public static void checkPing() {
		HttpGet request = new HttpGet("https://api.scarvite.de/status");
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			System.out.println(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();
			Header headers = entity.getContentType();
			System.out.println(headers);
			if (entity != null) {
				connected = true;
			}
		} catch (IOException e1) {
			System.out.println("Offline");
			connected = false;
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static JSONObject getJSONObject(String link) {
		HttpGet request = new HttpGet(link);
		request.setHeader("authorization", userToken);
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			System.out.println(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();
			Header headers = entity.getContentType();
			System.out.println(headers);
			if (entity != null) {
				try {
					JSONParser parser = new JSONParser();
					JSONObject jsonobject = (JSONObject) parser.parse(EntityUtils.toString(entity));
					System.out.println(jsonobject.get(0));
					return jsonobject;
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

	public static Image downloadImage(String link) {
		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			return ImageIO.read(connection.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void downloadSoundandSave(String url, String name) {
		Filesystem filesystem = new Filesystem();
		try (InputStream in = URI.create(url).toURL().openStream()) {
			filesystem.createFolderIfNotExist("/sound");
			Files.copy(in, Paths.get(filesystem.getMainPath() + "/sound/" + name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
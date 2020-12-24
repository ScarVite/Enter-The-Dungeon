package enterTheDungeon.game.Oberklassen;

import org.json.simple.JSONObject;

public class User {
	
	protected static String Name;
	protected static String Email;
	protected static String Token = null;
	
	public String getName() {
		return Name;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public String getToken() {
		return Token;
	}
	
	@SuppressWarnings("unchecked")
	public  JSONObject getUser() {
		JSONObject UserObj = new JSONObject();
		UserObj.put("Name", Name);
		UserObj.put("Email", Email);
		UserObj.put("Token", Token);
		return UserObj;
	}
	
	public static void setUser(JSONObject User) {
		Email = User.get("email").toString();
		Name = User.get("username").toString();
		Token = User.get("token").toString();
	}
	
	public static void setUserRegister(JSONObject User) {
		Email = User.get("email").toString();
		Name = User.get("username").toString();
		JSONObject tok = (JSONObject) User.get("token");
		Token = tok.get("value").toString();
	}
	
}

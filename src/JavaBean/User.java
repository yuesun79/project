package JavaBean;

import java.sql.Timestamp;


public class User {
	private int UID;
	private String email;
	private String userName;
	private String pass;
	private Timestamp dateJoined;
	private boolean whetherPublic;
	
	public boolean isWhetherPublic() {
		return whetherPublic;
	}
	public void setWhetherPublic(boolean whetherPublic) {
		this.whetherPublic = whetherPublic;
	}
	public int getUID() {
		return UID;
	}
	public void setUID(int UID) {
		this.UID = UID;
	}
	public Timestamp getDateJoined() {
		return dateJoined;
	}
	public void setDateJoined(Timestamp dateJoined) {
		this.dateJoined = dateJoined;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public User(String email, String userName, String pass) {
		super();
		this.email = email;
		this.userName = userName;
		this.pass = pass;
	}
	
	public User(int UID, String email, String userName, String pass) {
		super();
		this.UID = UID;
		this.email = email;
		this.userName = userName;
		this.pass = pass;
	}
	
	public User(String userName, String pass) {
		super();
		this.userName = userName;
		this.pass = pass;
	}
	
	public User() {
		
	}
	
	public User(int uID, String email, String userName, String pass, Timestamp dateJoined, boolean whetherPublic) {
		super();
		UID = uID;
		this.email = email;
		this.userName = userName;
		this.pass = pass;
		this.dateJoined = dateJoined;
		this.whetherPublic = whetherPublic;
	}
	@Override
	public String toString() {
		return "User [UID=" + UID + ", email=" + email + ", userName=" + userName + ", pass=" + pass + "]";
	}
	
//	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper(); 
//		
//		User user = new User(10, "123@fudan.cn", "win", "password");
//		String jsonStr = mapper.writeValueAsString(user);
//		System.out.println(jsonStr);
//	}
}

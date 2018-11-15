package cl.minsal.api.object;

public class UserLoginRequest {
	
	private String username;
	private String password;
	
	public UserLoginRequest() {
		super();
	}
	public UserLoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

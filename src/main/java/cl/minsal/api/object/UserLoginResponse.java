package cl.minsal.api.object;

public class UserLoginResponse {
	 
	public UserLoginResponse() {
		super();
	}
	
	public UserLoginResponse(String username, Integer id, String firstName,
			String lastName, String token) {
		super();
		this.username = username;
		this.id = id;
		this.token = token;
	}
	
	private String username;
	private Integer id;
	private String token;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}

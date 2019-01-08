package cl.minsal.api.object;

public class UserLoginResponse {

	private String username;
	private Integer id;
	private String token;
	private String role;
	private Integer codigo_establecimiento;
	
	public UserLoginResponse() {
		super();
	}
	
	public UserLoginResponse(String username, Integer id, String firstName,
			String lastName, String token, String role) {
		super();
		this.username = username;
		this.id = id;
		this.token = token;
		this.role = role;
	}
	
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getCodigo_establecimiento() {
		return codigo_establecimiento;
	}

	public void setCodigo_establecimiento(Integer codigo_establecimiento) {
		this.codigo_establecimiento = codigo_establecimiento;
	}
	
	
}

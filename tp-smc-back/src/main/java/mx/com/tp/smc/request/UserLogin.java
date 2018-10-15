package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import org.hibernate.validator.constraints.Email;

public class UserLogin {
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}	
	
	
}

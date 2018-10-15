package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import org.hibernate.validator.constraints.Email;

public class UserValidationUpdate {
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	
	@NotNull
	private String name;
	
	@NotNull
	private String organization;
	

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
	
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	
}

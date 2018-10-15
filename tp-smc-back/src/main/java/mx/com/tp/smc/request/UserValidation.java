package mx.com.tp.smc.request;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import org.hibernate.validator.constraints.Email;

public class UserValidation {
	@NotNull
	@Size(min = 1, max = 255)
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
		
	
}

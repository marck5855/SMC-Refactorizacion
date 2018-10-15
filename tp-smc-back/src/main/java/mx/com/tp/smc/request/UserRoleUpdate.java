package mx.com.tp.smc.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import org.hibernate.validator.constraints.Email;

public class UserRoleUpdate {
	@NotNull
	private String username;
	
	@NotNull
	private BigDecimal idrol;
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public BigDecimal getIdRol() {
		return idrol;
	}

	public void setIdRol (BigDecimal idrol) {
		this.idrol = idrol;
	}	
	
		
}

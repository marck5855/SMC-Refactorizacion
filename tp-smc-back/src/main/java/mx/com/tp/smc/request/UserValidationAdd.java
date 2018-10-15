package mx.com.tp.smc.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import org.hibernate.validator.constraints.Email;

public class UserValidationAdd {
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	private Integer enabled;

	@NotNull
	private String name;
	
	@NotNull
	private String organization;
	
	private BigDecimal idrol;
	
	private String createuser;
	
	private String usersnum;

	
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
	
	public BigDecimal getIdRol() {
		return idrol;
	}

	public void setIdRol(BigDecimal idrol) {
		this.idrol = idrol;
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
	
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getUsersnum() {
		return usersnum;
	}

	public void setUsersnum(String usersnum) {
		this.usersnum = usersnum;
	}

}

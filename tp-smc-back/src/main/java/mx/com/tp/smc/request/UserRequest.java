package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequest {

	@NotNull
	@Size(min = 1, max = 255)
	private String username;

	@NotNull
	@Size(min = 1, max = 255)
	private String organization;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String createuser;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

}

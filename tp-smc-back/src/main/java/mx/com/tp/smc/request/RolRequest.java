package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;

public class RolRequest {

	@NotNull
	private String rolRole;

	public String getRolRole() {
		return rolRole;
	}

	public void setRolRole(String rolRole) {
		this.rolRole = rolRole;
	}

}

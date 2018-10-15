
package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;

public class RolForOrganization {
	@NotNull
	private String organization;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}

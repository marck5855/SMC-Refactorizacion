
package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationRequest {

	@NotNull
	@Size(min = 1, max = 255)
	private String organization;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Organization [organization=" + organization + "]";
	}

}

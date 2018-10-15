
package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;

public class LinkAdd {

	@NotNull
	private String nombreLink;

	@NotNull
	private String link;

	@NotNull
	private String organization;

	@NotNull
	private String descripcionLink;

	public String getNombreLink() {
		return nombreLink;
	}

	public void setNombreLink(String nombreLink) {
		this.nombreLink = nombreLink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getDescripcionLink() {
		return descripcionLink;
	}

	public void setDescripcionLink(String descripcionLink) {
		this.descripcionLink = descripcionLink;
	}

}

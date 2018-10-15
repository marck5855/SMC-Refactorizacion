package mx.com.tp.smc.entity;

public class Organization {

	private String organizationId;
	private String organizationName;
	private String succes;
	private String mssg;
	private String ipPuertoGrafana;

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getSucces() {
		return succes;
	}

	public void setSucces(String succes) {
		this.succes = succes;
	}

	public String getMssg() {
		return mssg;
	}

	public void setMssg(String mssg) {
		this.mssg = mssg;
	}

	public String getIpPuertoGrafana() {
		return ipPuertoGrafana;
	}

	public void setIpPuertoGrafana(String ipPuertoGrafana) {
		this.ipPuertoGrafana = ipPuertoGrafana;
	}

	@Override
	public String toString() {
		return "Organization [organizationId=" + organizationId + ", organizationName=" + organizationName + ", succes="
				+ succes + ", mssg=" + mssg + ", ipPuertoGrafana=" + ipPuertoGrafana + "]";
	}

}

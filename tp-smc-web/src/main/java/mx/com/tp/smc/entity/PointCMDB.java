package mx.com.tp.smc.entity;

public class PointCMDB {

	private String mssg;
	private String succes;
	private String tenant;
	public String clienteid;
	public String clientename;
	public String puntaid;
	public String puntaname;
	public String inactive;
	public String resourcename;
	public String hostname;
	public String ipaddress;
	public String macaddress;
	public String zcpe;
	
	public PointCMDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PointCMDB(String mssg, String succes, String tenant, String clienteid, String clientename, String puntaid,
			String puntaname, String inactive, String resourcename, String hostname, String ipaddress,
			String macaddress, String zcpe) {
		super();
		this.mssg = mssg;
		this.succes = succes;
		this.tenant = tenant;
		this.clienteid = clienteid;
		this.clientename = clientename;
		this.puntaid = puntaid;
		this.puntaname = puntaname;
		this.inactive = inactive;
		this.resourcename = resourcename;
		this.hostname = hostname;
		this.ipaddress = ipaddress;
		this.macaddress = macaddress;
		this.zcpe = zcpe;
	}

	public String getMssg() {
		return mssg;
	}

	public void setMssg(String mssg) {
		this.mssg = mssg;
	}

	public String getSucces() {
		return succes;
	}

	public void setSucces(String succes) {
		this.succes = succes;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getClienteid() {
		return clienteid;
	}

	public void setClienteid(String clienteid) {
		this.clienteid = clienteid;
	}

	public String getClientename() {
		return clientename;
	}

	public void setClientename(String clientename) {
		this.clientename = clientename;
	}

	public String getPuntaid() {
		return puntaid;
	}

	public void setPuntaid(String puntaid) {
		this.puntaid = puntaid;
	}

	public String getPuntaname() {
		return puntaname;
	}

	public void setPuntaname(String puntaname) {
		this.puntaname = puntaname;
	}

	public String getInactive() {
		return inactive;
	}

	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public String getZcpe() {
		return zcpe;
	}

	public void setZcpe(String zcpe) {
		this.zcpe = zcpe;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PointCMDB [mssg=");
		builder.append(mssg);
		builder.append(", succes=");
		builder.append(succes);
		builder.append(", tenant=");
		builder.append(tenant);
		builder.append(", clienteid=");
		builder.append(clienteid);
		builder.append(", clientename=");
		builder.append(clientename);
		builder.append(", puntaid=");
		builder.append(puntaid);
		builder.append(", puntaname=");
		builder.append(puntaname);
		builder.append(", inactive=");
		builder.append(inactive);
		builder.append(", resourcename=");
		builder.append(resourcename);
		builder.append(", hostname=");
		builder.append(hostname);
		builder.append(", ipaddress=");
		builder.append(ipaddress);
		builder.append(", macaddress=");
		builder.append(macaddress);
		builder.append(", zcpe=");
		builder.append(zcpe);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}

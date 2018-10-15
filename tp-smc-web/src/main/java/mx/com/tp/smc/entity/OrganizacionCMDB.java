package mx.com.tp.smc.entity;

public class OrganizacionCMDB {

	public String dispositivo;
	public String organizacion;
	public String cliente;
	public String tfepunta;
	public String hostname;
	public String macadress;
	public String ipadress;
	public String tenant;
	public Integer inactivo;
	
	public OrganizacionCMDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganizacionCMDB(String dispositivo, String organizacion, String cliente, String tfepunta, String hostname,
			String macadress, String ipadress, String tenant, Integer inactivo) {
		super();
		this.dispositivo = dispositivo;
		this.organizacion = organizacion;
		this.cliente = cliente;
		this.tfepunta = tfepunta;
		this.hostname = hostname;
		this.macadress = macadress;
		this.ipadress = ipadress;
		this.tenant = tenant;
		this.inactivo = inactivo;
	}

	
	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTfepunta() {
		return tfepunta;
	}

	public void setTfepunta(String tfepunta) {
		this.tfepunta = tfepunta;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getMacadress() {
		return macadress;
	}

	public void setMacadress(String macadress) {
		this.macadress = macadress;
	}

	public String getIpadress() {
		return ipadress;
	}

	public void setIpadress(String ipadress) {
		this.ipadress = ipadress;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public Integer getInactivo() {
		return inactivo;
	}

	public void setInactivo(Integer inactivo) {
		this.inactivo = inactivo;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrganizacionCMDB [dispositivo=");
		builder.append(dispositivo);
		builder.append(", organizacion=");
		builder.append(organizacion);
		builder.append(", cliente=");
		builder.append(cliente);
		builder.append(", tfepunta=");
		builder.append(tfepunta);
		builder.append(", hostname=");
		builder.append(hostname);
		builder.append(", macadress=");
		builder.append(macadress);
		builder.append(", ipadress=");
		builder.append(ipadress);
		builder.append(", tenant=");
		builder.append(tenant);
		builder.append(", inactivo=");
		builder.append(inactivo);
		builder.append("]");
		return builder.toString();
	}

}

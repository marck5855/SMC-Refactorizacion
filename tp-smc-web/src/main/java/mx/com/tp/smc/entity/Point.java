package mx.com.tp.smc.entity;

public class Point {

	private String mssg;
	private String succes;
	private String name;
	private String userId;
	private String ipPuertoGrafana;
	private String ipPunta;
	private String tenant;
	private Integer estado;
	private String idPuntasSeveras;
	private String tfe;
	
	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Point(String mssg, String succes, String name, String userId, String ipPuertoGrafana, String ipPunta,
			String tenant, Integer estado, String idPuntasSeveras, String tfe) {
		super();
		this.mssg = mssg;
		this.succes = succes;
		this.name = name;
		this.userId = userId;
		this.ipPuertoGrafana = ipPuertoGrafana;
		this.ipPunta = ipPunta;
		this.tenant = tenant;
		this.estado = estado;
		this.idPuntasSeveras = idPuntasSeveras;
		this.tfe = tfe;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIpPuertoGrafana() {
		return ipPuertoGrafana;
	}

	public void setIpPuertoGrafana(String ipPuertoGrafana) {
		this.ipPuertoGrafana = ipPuertoGrafana;
	}

	public String getIpPunta() {
		return ipPunta;
	}

	public void setIpPunta(String ipPunta) {
		this.ipPunta = ipPunta;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getIdPuntasSeveras() {
		return idPuntasSeveras;
	}

	public void setIdPuntasSeveras(String idPuntasSeveras) {
		this.idPuntasSeveras = idPuntasSeveras;
	}

	public String getTfe() {
		return tfe;
	}

	public void setTfe(String tfe) {
		this.tfe = tfe;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Point [mssg=");
		builder.append(mssg);
		builder.append(", succes=");
		builder.append(succes);
		builder.append(", name=");
		builder.append(name);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", ipPuertoGrafana=");
		builder.append(ipPuertoGrafana);
		builder.append(", ipPunta=");
		builder.append(ipPunta);
		builder.append(", tenant=");
		builder.append(tenant);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", idPuntasSeveras=");
		builder.append(idPuntasSeveras);
		builder.append(", tfe=");
		builder.append(tfe);
		builder.append("]");
		return builder.toString();
	}

}
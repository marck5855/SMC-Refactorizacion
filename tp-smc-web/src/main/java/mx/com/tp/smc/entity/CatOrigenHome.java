package mx.com.tp.smc.entity;

public class CatOrigenHome {

	private Long idHome;
	private String origenHome;
	private String urlHome;
	private String urlDetalle;
	private String descripcion;
	private String urlUno;
	private String urlDos;
	private String urlTre;
	
	private String organizacionNombre;
	private float latencia;
	private float disponibilidad;
	private float paquetes;
	
	private String succes;
	private String mssg;
	
	public CatOrigenHome() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public CatOrigenHome(Long idHome, String origenHome, String urlHome, String urlDetalle, String descripcion,
			String urlUno, String urlDos, String urlTre, String organizacionNombre, float latencia,
			float disponibilidad, float paquetes, String succes, String mssg) {
		super();
		this.idHome = idHome;
		this.origenHome = origenHome;
		this.urlHome = urlHome;
		this.urlDetalle = urlDetalle;
		this.descripcion = descripcion;
		this.urlUno = urlUno;
		this.urlDos = urlDos;
		this.urlTre = urlTre;
		this.organizacionNombre = organizacionNombre;
		this.latencia = latencia;
		this.disponibilidad = disponibilidad;
		this.paquetes = paquetes;
		this.succes = succes;
		this.mssg = mssg;
	}



	public Long getIdHome() {
		return idHome;
	}

	public void setIdHome(Long idHome) {
		this.idHome = idHome;
	}

	public String getOrigenHome() {
		return origenHome;
	}

	public void setOrigenHome(String origenHome) {
		this.origenHome = origenHome;
	}

	public String getUrlHome() {
		return urlHome;
	}

	public void setUrlHome(String urlHome) {
		this.urlHome = urlHome;
	}

	public String getUrlDetalle() {
		return urlDetalle;
	}

	public void setUrlDetalle(String urlDetalle) {
		this.urlDetalle = urlDetalle;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlUno() {
		return urlUno;
	}

	public void setUrlUno(String urlUno) {
		this.urlUno = urlUno;
	}

	public String getUrlDos() {
		return urlDos;
	}

	public void setUrlDos(String urlDos) {
		this.urlDos = urlDos;
	}

	public String getUrlTre() {
		return urlTre;
	}

	public void setUrlTre(String urlTre) {
		this.urlTre = urlTre;
	}

	public String getOrganizacionNombre() {
		return organizacionNombre;
	}

	public void setOrganizacionNombre(String organizacionNombre) {
		this.organizacionNombre = organizacionNombre;
	}

	public float getLatencia() {
		return latencia;
	}

	public void setLatencia(float latencia) {
		this.latencia = latencia;
	}

	public float getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(float disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public float getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(float paquetes) {
		this.paquetes = paquetes;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatOrigenHome [idHome=");
		builder.append(idHome);
		builder.append(", origenHome=");
		builder.append(origenHome);
		builder.append(", urlHome=");
		builder.append(urlHome);
		builder.append(", urlDetalle=");
		builder.append(urlDetalle);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", urlUno=");
		builder.append(urlUno);
		builder.append(", urlDos=");
		builder.append(urlDos);
		builder.append(", urlTre=");
		builder.append(urlTre);
		builder.append(", succes=");
		builder.append(succes);
		builder.append(", mssg=");
		builder.append(mssg);
		builder.append("]");
		return builder.toString();
	}

	
}

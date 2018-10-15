package mx.com.tp.smc.response;

public class Menus {

	private Long idMenu;
	private Long idRol;
	private Long idHome;

	private Long idSubMenu;
	private String nombreMenu;
	private String path;
	private String descripcionMenu;
	private String icono;
	private String tituloPrincipal;

	private String origenHome;
	private String urlHome;
	private String descripcion;

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public Long getIdHome() {
		return idHome;
	}

	public void setIdHome(Long idHome) {
		this.idHome = idHome;
	}

	public Long getIdSubMenu() {
		return idSubMenu;
	}

	public void setIdSubMenu(Long idSubMenu) {
		this.idSubMenu = idSubMenu;
	}

	public String getNombreMenu() {
		return nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescripcionMenu() {
		return descripcionMenu;
	}

	public void setDescripcionMenu(String descripcionMenu) {
		this.descripcionMenu = descripcionMenu;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getTituloPrincipal() {
		return tituloPrincipal;
	}

	public void setTituloPrincipal(String tituloPrincipal) {
		this.tituloPrincipal = tituloPrincipal;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

package mx.com.tp.smc.entity;

public class Menus {

	private Long idMenu;
	private Long idRol;
	private Long idHome;

	private Long idSubMenu;
	private String nombreMenu;
	private String path;
	private String descripcionMenu;
	private String icono;
	private boolean checkedMenu;
	private String tituloPrincipal;
	private boolean tieneMenus;

	private String origenHome;
	private String urlHome;
	private String descripcion;

	private String succes;
	private String mssg;

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

	public boolean isCheckedMenu() {
		return checkedMenu;
	}

	public void setCheckedMenu(boolean checkedMenu) {
		this.checkedMenu = checkedMenu;
	}

	public String getTituloPrincipal() {
		return tituloPrincipal;
	}

	public void setTituloPrincipal(String tituloPrincipal) {
		this.tituloPrincipal = tituloPrincipal;
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

	public boolean isTieneMenus() {
		return tieneMenus;
	}

	public void setTieneMenus(boolean tieneMenus) {
		this.tieneMenus = tieneMenus;
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

package mx.com.tp.smc.entity;

public class CatMenu {

	private Long idMenu;
	private Long idSubMenu;
	private String nombreMenu;
	private String path;
	private String descripcionMenu;
	private String icono;
	private boolean checkedMenu;
	private String tituloPrincipal;

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
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

}

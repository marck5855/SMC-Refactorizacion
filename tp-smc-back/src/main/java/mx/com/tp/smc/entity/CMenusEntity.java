package mx.com.tp.smc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CAT_MENUS")
@NamedQuery(name = "CMenusEntity.findAll", query = "SELECT t FROM CMenusEntity t")
public class CMenusEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_MENU")
	private Long idMenu;

	@Column(name = "ID_SUBMENU")
	private Long idSubMenu;

	@Column(name = "NOMBRE")
	private String nombreMenu;

	@Column(name = "PATH")
	private String path;

	@Column(name = "DESCRIPCION")
	private String descripcionMenu;

	@Column(name = "ICONO")
	private String icono;
	
	@Column(name = "TITULO_PRINCIPAL")
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

	public String getTituloPrincipal() {
		return tituloPrincipal;
	}

	public void setTituloPrincipal(String tituloPrincipal) {
		this.tituloPrincipal = tituloPrincipal;
	}

}

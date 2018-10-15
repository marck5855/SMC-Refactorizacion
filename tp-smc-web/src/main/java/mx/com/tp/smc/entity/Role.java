package mx.com.tp.smc.entity;

import java.math.BigDecimal;
import java.util.ArrayList;


public class Role {

	private Long idRole;
	private String rolRole;
	private String rolDescription;
	private String organizacion;
	private String nombreOrganizacion;
	private String succes;
	private String mssg;
	private BigDecimal idRol;
	private ArrayList<Menus> menusDetalle;
	private ArrayList<Menus> subMenusDetalle;
	private ArrayList<Menus> rolSinMenus;
	private boolean tieneMenus;
	private Long idHome;
	private String descripcion;
	private String origenHome;
	private Long idMenu;
	private boolean disabled;

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getRolRole() {
		return rolRole;
	}

	public void setRolRole(String rolRole) {
		this.rolRole = rolRole;
	}

	public String getRolDescription() {
		return rolDescription;
	}

	public void setRolDescription(String rolDescription) {
		this.rolDescription = rolDescription;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	public String getNombreOrganizacion() {
		return nombreOrganizacion;
	}

	public void setNombreOrganizacion(String nombreOrganizacion) {
		this.nombreOrganizacion = nombreOrganizacion;
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

	public BigDecimal getIdRol() {
		return idRol;
	}

	public void setIdRol(BigDecimal idRol) {
		this.idRol = idRol;
	}

	public ArrayList<Menus> getMenusDetalle() {
		return menusDetalle;
	}

	public void setMenusDetalle(ArrayList<Menus> menusDetalle) {
		this.menusDetalle = menusDetalle;
	}

	public ArrayList<Menus> getSubMenusDetalle() {
		return subMenusDetalle;
	}

	public void setSubMenusDetalle(ArrayList<Menus> subMenusDetalle) {
		this.subMenusDetalle = subMenusDetalle;
	}

	public ArrayList<Menus> getRolSinMenus() {
		return rolSinMenus;
	}

	public void setRolSinMenus(ArrayList<Menus> rolSinMenus) {
		this.rolSinMenus = rolSinMenus;
	}

	public boolean isTieneMenus() {
		return tieneMenus;
	}

	public void setTieneMenus(boolean tieneMenus) {
		this.tieneMenus = tieneMenus;
	}

	public Long getIdHome() {
		return idHome;
	}

	public void setIdHome(Long idHome) {
		this.idHome = idHome;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public String getOrigenHome() {
		return origenHome;
	}

	public void setOrigenHome(String origenHome) {
		this.origenHome = origenHome;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", rolRole=" + rolRole + ", rolDescription=" + rolDescription
				+ ", organizacion=" + organizacion + ", nombreOrganizacion=" + nombreOrganizacion + ", succes=" + succes
				+ ", mssg=" + mssg + ", idRol=" + idRol + ", menusDetalle=" + menusDetalle + ", subMenusDetalle="
				+ subMenusDetalle + ", rolSinMenus=" + rolSinMenus + ", tieneMenus=" + tieneMenus + ", idHome=" + idHome
				+ ", descripcion=" + descripcion + ", origenHome=" + origenHome + ", idMenu=" + idMenu + ", disabled="
				+ disabled + "]";
	}

}

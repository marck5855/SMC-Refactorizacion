package mx.com.tp.smc.response;

import java.math.BigDecimal;

public class Role {

	private BigDecimal idrole;
	private String rolrole;
	private String roldescription;
	private String organizacion;
	private String nombreOrganizacion;

	public BigDecimal getIdRole() {
		return idrole;
	}

	public void setIdRole(BigDecimal idrole) {
		this.idrole = idrole;
	}

	public String getRolRole() {
		return rolrole;
	}

	public void setRolRole(String rolrole) {
		this.rolrole = rolrole;
	}

	public String getRolDescription() {
		return roldescription;
	}

	public void setRolDescription(String roldescription) {
		this.roldescription = roldescription;
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

}

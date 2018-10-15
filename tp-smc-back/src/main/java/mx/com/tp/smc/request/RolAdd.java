package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;

public class RolAdd {

	@NotNull
	private String rol;

	@NotNull
	private String descripcion;

	@NotNull
	private String idOrganizacion;

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdOrganizacion() {
		return idOrganizacion;
	}

	public void setIdOrganizacion(String idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}

}

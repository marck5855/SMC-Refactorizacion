package mx.com.tp.smc.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TROL")
@NamedQuery(name = "TrolEntity.findAll", query = "SELECT t FROM TrolEntity t")
public class TrolEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IDROL")
	private BigDecimal idRol;

	@Column(name = "ROL_ROLE")
	private String rolRole;

	@Column(name = "ROL_DESCRIPTION")
	private String rolDescription;

	@Column(name = "ORGANIZACION")
	private String organizacion;
	
	@Column(name = "NOMBRE_ORGANIZACION")
	private String nombreOrganizacion;
	
	
	public BigDecimal getIdRol() {
		return idRol;
	}

	public void setIdRol(BigDecimal idRol) {
		this.idRol = idRol;
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
}

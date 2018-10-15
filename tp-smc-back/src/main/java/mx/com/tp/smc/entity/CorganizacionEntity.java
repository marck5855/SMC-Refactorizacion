
package mx.com.tp.smc.entity;

import java.io.Serializable;
//import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CAT_ORGANIZACION")
@NamedQuery(name = "CorganizacionEntity.findAll", query = "SELECT t FROM CorganizacionEntity t")
public class CorganizacionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_ORGANIZACION")
	private Long idOrganizacion;

	@Column(name = "NOMBRE_ORGANIZACION")
	private String nombreOrganizacion;

	@Column(name = "ORGANIZACION_ID")
	private String organizacionId;

	@Column(name = "INACTIVO")
	private Integer inactivo;
	
	public Long getIdOrganizacion() {
		return idOrganizacion;
	}

	public void setIdOrganizacion(Long idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}

	public String getNombreOrganizacion() {
		return nombreOrganizacion;
	}

	public void setNombreOrganizacion(String nombreOrganizacion) {
		this.nombreOrganizacion = nombreOrganizacion;
	}

	public String getOrganizacionId() {
		return organizacionId;
	}

	public void setOrganizacionId(String organizacionId) {
		this.organizacionId = organizacionId;
	}

	public Integer getInactivo() {
		return inactivo;
	}

	public void setInactivo(Integer inactivo) {
		this.inactivo = inactivo;
	}
	
	
}

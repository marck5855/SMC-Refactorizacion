
package mx.com.tp.smc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CAT_ORIGEN_HOME")
@NamedQuery(name = "CatOrigenHomeEntity.findAll", query = "SELECT t FROM CatOrigenHomeEntity t")
public class CatOrigenHomeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_HOME")
	private Long idHome;

	@Column(name = "ORIGEN_HOME")
	private String origenHome;

	@Column(name = "URL_HOME")
	private String urlHome;

	@Column(name = "DESCRIPCION")
	private String descripcion;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

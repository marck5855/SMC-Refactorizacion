
package mx.com.tp.smc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CAT_LINKS")
@NamedQuery(name = "CatLinksEntity.findAll", query = "SELECT t FROM CatLinksEntity t")
public class CatLinksEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_LINK")
	private Long idLink;

	@Column(name = "URL")
	private String url;

	@Column(name = "NOMBRE_LINK")
	private String nombreLink;

	@Column(name = "ID_ORGANIZACION")
	private String organizacion;

	@Column(name = "DESCRIPCION_LINK")
	private String descripcionLink;

	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;

	public Long getIdLink() {
		return idLink;
	}

	public void setIdLink(Long idLink) {
		this.idLink = idLink;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNombreLink() {
		return nombreLink;
	}

	public void setNombreLink(String nombreLink) {
		this.nombreLink = nombreLink;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	public String getDescripcionLink() {
		return descripcionLink;
	}

	public void setDescripcionLink(String descripcionLink) {
		this.descripcionLink = descripcionLink;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}

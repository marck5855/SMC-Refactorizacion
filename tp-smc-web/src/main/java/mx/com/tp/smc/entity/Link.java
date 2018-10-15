package mx.com.tp.smc.entity;

import java.util.Date;

public class Link {
	private Long idLink;
	private String url;
	private String nombreLink;
	private String organizacion;
	private String descripcionLink;
	private Date fechaAlta;
	private String succes;
	private String mssg;

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

	@Override
	public String toString() {
		return "Link [idLink=" + idLink + ", url=" + url + ", nombreLink=" + nombreLink + ", organizacion="
				+ organizacion + ", descripcionLink=" + descripcionLink + ", fechaAlta=" + fechaAlta + ", succes="
				+ succes + ", mssg=" + mssg + "]";
	}

}

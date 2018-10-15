package mx.com.tp.smc.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
//import java.sql.Timestamp;

/**
 * The persistent class for the DES_SERVICE database table.
 * 
 */
@Entity
@Table(name = "TSERVICE")
@NamedQuery(name = "DesService.findAll", query = "SELECT d FROM DesService d")
public class DesService implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name = "ACT_REALIZADAS",columnDefinition = "text")
	private String actRealizadas;

	private BigDecimal activo;

	private String afectacion;

	@Column(name = "APE_SOLICITANTE")
	private String apeSolicitante;

	@Column(name = "APE_SOLICITANTE_AA")
	private String apeSolicitanteAa;

	private String categoria;

	@Column(name = "CREADO_VIA")
	private String creadoVia;

	@Column(columnDefinition = "text")
	private String descripcion;

	@Column(name = "DIAG_FINAL",columnDefinition = "text")
	private String diagFinal;

	@Column(name = "DIAG_INICIAL",columnDefinition = "text")
	private String diagInicial;

	@Column(name = "FH_SOLUCION")
	private String fhSolucion;

	private String fhndesolucion;

	@Column(name = "FOLIO_ABIERTO")
	private String folioAbierto;

	@Column(name = "FOLIO_CERRADO")
	private String folioCerrado;

	@Column(name = "FOLIO_DURACION")
	private String folioDuracion;

	@Column(name = "FOLIO_GRUPO")
	private String folioGrupo;

	@Id
	@Column(name = "FOLIO_INCIDENCIA")
	private BigDecimal folioIncidencia;

	@Column(name = "FOLIO_PARENTID")
	private String folioParentid;

	@Column(name = "FOLIO_STATUS")
	private String folioStatus;

	@Column(name = "FOLIO_SUBTIPO")
	private String folioSubtipo;

	@Column(name = "FOLIO_TIPO")
	private String folioTipo;

	@Column(name = "FOLIO_TITULO")
	private String folioTitulo;

	@Column(name = "FOLIO_TITULO2")
	private String folioTitulo2;

	@Column(name = "FOLIO_TITULO3")
	private String folioTitulo3;

	@Column(name = "FOLIO_ULTIMAMODIFICACION")
	private String folioUltimamodificacion;

	private String impacto;

	@Column(name = "NOM_SOLICITANTE")
	private String nomSolicitante;

	@Column(name = "NOM_SOLICITANTE_AA")
	private String nomSolicitanteAa;

	@Column(name = "NOM2_SOLICITANTE")
	private String nom2Solicitante;

	@Column(name = "NOM2_SOLICITANTE_AA")
	private String nom2SolicitanteAa;

	private String prioridad;

	private String region;

	private String regionciudad;

	@Column(columnDefinition = "text")
	private String resumen;

	@Column(columnDefinition = "text")
	private String solucion;

	@Column(name = "TICKET_S_EXTERNO")
	private String ticketSExterno;

	@Column(name = "TIEMPO_IMPUTABLE_CTE")
	private String tiempoImputableCte;

	@Column(name = "TIEMPO_IMPUTABLE_TPE")
	private String tiempoImputableTpe;

	private String urgencia;

	@Column(name = "Z_NOM_PUNTA_INEX")
	private String zNomPuntaInex;

	@Column(name = "ZES_PROACTIVO_REACTIVO")
	private String zesProactivoReactivo;

	@Column(name="ZFALLA_IMPUTABLE_CTE")
	private String zfallaImputableCte;

	@Column(name="ZFALLA_IMPUTABLE_TPE")
	private String zfallaImputableTpe;
	
	@Column(name="ORGANIZACION",columnDefinition = "text")
	private String organizacion;
	
	@Column(name="ZTIEMPO_FALLA_TERCEROS",columnDefinition = "text")
	private String ztiempofallaterceros;
	
	@Column(name="ZTIEMPO_FALLA_PROV",columnDefinition = "text")
	private String ztiempofallaprov;
	

	public DesService() {
	}

	public String getActRealizadas() {
		return this.actRealizadas;
	}

	public void setActRealizadas(String actRealizadas) {
		this.actRealizadas = actRealizadas;
	}

	public BigDecimal getActivo() {
		return this.activo;
	}

	public void setActivo(BigDecimal activo) {
		this.activo = activo;
	}

	public String getAfectacion() {
		return this.afectacion;
	}

	public void setAfectacion(String afectacion) {
		this.afectacion = afectacion;
	}

	public String getApeSolicitante() {
		return this.apeSolicitante;
	}

	public void setApeSolicitante(String apeSolicitante) {
		this.apeSolicitante = apeSolicitante;
	}

	public String getApeSolicitanteAa() {
		return this.apeSolicitanteAa;
	}

	public void setApeSolicitanteAa(String apeSolicitanteAa) {
		this.apeSolicitanteAa = apeSolicitanteAa;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCreadoVia() {
		return this.creadoVia;
	}

	public void setCreadoVia(String creadoVia) {
		this.creadoVia = creadoVia;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDiagFinal() {
		return this.diagFinal;
	}

	public void setDiagFinal(String diagFinal) {
		this.diagFinal = diagFinal;
	}

	public String getDiagInicial() {
		return this.diagInicial;
	}

	public void setDiagInicial(String diagInicial) {
		this.diagInicial = diagInicial;
	}

	public String getFhSolucion() {
		return this.fhSolucion;
	}

	public void setFhSolucion(String fhSolucion) {
		this.fhSolucion = fhSolucion;
	}

	public String getFhndesolucion() {
		return this.fhndesolucion;
	}

	public void setFhndesolucion(String fhndesolucion) {
		this.fhndesolucion = fhndesolucion;
	}

	public String getFolioAbierto() {
		return this.folioAbierto;
	}

	public void setFolioAbierto(String folioAbierto) {
		this.folioAbierto = folioAbierto;
	}

	public String getFolioCerrado() {
		return this.folioCerrado;
	}

	public void setFolioCerrado(String folioCerrado) {
		this.folioCerrado = folioCerrado;
	}

	public String getFolioDuracion() {
		return this.folioDuracion;
	}

	public void setFolioDuracion(String folioDuracion) {
		this.folioDuracion = folioDuracion;
	}

	public String getFolioGrupo() {
		return this.folioGrupo;
	}

	public void setFolioGrupo(String folioGrupo) {
		this.folioGrupo = folioGrupo;
	}

	public BigDecimal getFolioIncidencia() {
		return this.folioIncidencia;
	}

	public void setFolioIncidencia(BigDecimal folioIncidencia) {
		this.folioIncidencia = folioIncidencia;
	}

	public String getFolioParentid() {
		return this.folioParentid;
	}

	public void setFolioParentid(String folioParentid) {
		this.folioParentid = folioParentid;
	}

	public String getFolioStatus() {
		return this.folioStatus;
	}

	public void setFolioStatus(String folioStatus) {
		this.folioStatus = folioStatus;
	}

	public String getFolioSubtipo() {
		return this.folioSubtipo;
	}

	public void setFolioSubtipo(String folioSubtipo) {
		this.folioSubtipo = folioSubtipo;
	}

	public String getFolioTipo() {
		return this.folioTipo;
	}

	public void setFolioTipo(String folioTipo) {
		this.folioTipo = folioTipo;
	}

	public String getFolioTitulo() {
		return this.folioTitulo;
	}

	public void setFolioTitulo(String folioTitulo) {
		this.folioTitulo = folioTitulo;
	}

	public String getFolioTitulo2() {
		return this.folioTitulo2;
	}

	public void setFolioTitulo2(String folioTitulo2) {
		this.folioTitulo2 = folioTitulo2;
	}

	public String getFolioTitulo3() {
		return this.folioTitulo3;
	}

	public void setFolioTitulo3(String folioTitulo3) {
		this.folioTitulo3 = folioTitulo3;
	}

	public String getFolioUltimamodificacion() {
		return this.folioUltimamodificacion;
	}

	public void setFolioUltimamodificacion(String folioUltimamodificacion) {
		this.folioUltimamodificacion = folioUltimamodificacion;
	}

	public String getImpacto() {
		return this.impacto;
	}

	public void setImpacto(String impacto) {
		this.impacto = impacto;
	}

	public String getNomSolicitante() {
		return this.nomSolicitante;
	}

	public void setNomSolicitante(String nomSolicitante) {
		this.nomSolicitante = nomSolicitante;
	}

	public String getNomSolicitanteAa() {
		return this.nomSolicitanteAa;
	}

	public void setNomSolicitanteAa(String nomSolicitanteAa) {
		this.nomSolicitanteAa = nomSolicitanteAa;
	}

	public String getNom2Solicitante() {
		return this.nom2Solicitante;
	}

	public void setNom2Solicitante(String nom2Solicitante) {
		this.nom2Solicitante = nom2Solicitante;
	}

	public String getNom2SolicitanteAa() {
		return this.nom2SolicitanteAa;
	}

	public void setNom2SolicitanteAa(String nom2SolicitanteAa) {
		this.nom2SolicitanteAa = nom2SolicitanteAa;
	}

	public String getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionciudad() {
		return this.regionciudad;
	}

	public void setRegionciudad(String regionciudad) {
		this.regionciudad = regionciudad;
	}

	public String getResumen() {
		return this.resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getSolucion() {
		return this.solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public String getTicketSExterno() {
		return this.ticketSExterno;
	}

	public void setTicketSExterno(String ticketSExterno) {
		this.ticketSExterno = ticketSExterno;
	}

	public String getTiempoImputableCte() {
		return this.tiempoImputableCte;
	}

	public void setTiempoImputableCte(String tiempoImputableCte) {
		this.tiempoImputableCte = tiempoImputableCte;
	}

	public String getTiempoImputableTpe() {
		return this.tiempoImputableTpe;
	}

	public void setTiempoImputableTpe(String tiempoImputableTpe) {
		this.tiempoImputableTpe = tiempoImputableTpe;
	}

	public String getUrgencia() {
		return this.urgencia;
	}

	public void setUrgencia(String urgencia) {
		this.urgencia = urgencia;
	}

	public String getZNomPuntaInex() {
		return this.zNomPuntaInex;
	}

	public void setZNomPuntaInex(String zNomPuntaInex) {
		this.zNomPuntaInex = zNomPuntaInex;
	}

	public String getZesProactivoReactivo() {
		return this.zesProactivoReactivo;
	}

	public void setZesProactivoReactivo(String zesProactivoReactivo) {
		this.zesProactivoReactivo = zesProactivoReactivo;
	}


	public String getzNomPuntaInex() {
		return zNomPuntaInex;
	}

	public void setzNomPuntaInex(String zNomPuntaInex) {
		this.zNomPuntaInex = zNomPuntaInex;
	}



	public String getZfallaImputableCte() {
		return this.zfallaImputableCte;

	}

	public void setZfallaImputableCte(String zfallaImputableCte) {
		this.zfallaImputableCte = zfallaImputableCte;
	}

	public String getZfallaImputableTpe() {
		return zfallaImputableTpe;
	}

	public void setZfallaImputableTpe(String zfallaImputableTpe) {
		this.zfallaImputableTpe = zfallaImputableTpe;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	public String getZtiempofallaterceros() {
		return ztiempofallaterceros;
	}

	public void setZtiempofallaterceros(String ztiempofallaterceros) {
		this.ztiempofallaterceros = ztiempofallaterceros;
	}

	public String getZtiempofallaprov() {
		return ztiempofallaprov;
	}

	public void setZtiempofallaprov(String ztiempofallaprov) {
		this.ztiempofallaprov = ztiempofallaprov;
	}
	
	

}
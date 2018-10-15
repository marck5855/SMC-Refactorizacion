package mx.com.tp.smc.entity;

import java.math.BigDecimal;
//import java.sql.Timestamp;

public class TicketConcilied{
	
	private String actRealizadas;
	private BigDecimal activo;
	private String afectacion;
	private String apeSolicitante;
	private String apeSolicitanteAa;
	private String categoria;
	private String creadoVia;
	private String descripcion;
	private String diagFinal;
	private String diagInicial;
	private String fhSolucion;
	private String fhndesolucion;
	private String folioAbierto;
	private String folioCerrado;
	private String folioDuracion;
	private String folioGrupo;
	private Long folioIncidencia;
	private String folioParentid;
	private String folioStatus;
	private String folioSubtipo;
	private String folioTipo;
	private String folioTitulo;
	private String folioTitulo2;
	private String folioTitulo3;
	private String folioUltimamodificacion;
	private String impacto;
	private String nomSolicitante;
	private String nomSolicitanteAa;
	private String nom2Solicitante;
	private String nom2SolicitanteAa;
	private String prioridad;
	private String region;
	private String regionciudad;
	private String resumen;
	private String solucion;
	private String ticketSExterno;
	private String tiempoImputableCte;
	private String tiempoImputableTpe;
	private String urgencia;
	private String zNomPuntaInex;
	private String zesProactivoReactivo;
	private String zfallaImputableCte;
	private String zfallaImputableTpe;
	private String organizacion;
	private String succes;
	private String mssg;
	private String refNum;
	private String ztiempoFallaProv;
	private String ztiempoFallaTerceros;
	
	public TicketConcilied() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public TicketConcilied(String actRealizadas, BigDecimal activo, String afectacion, String apeSolicitante,
			String apeSolicitanteAa, String categoria, String creadoVia, String descripcion, String diagFinal,
			String diagInicial, String fhSolucion, String fhndesolucion, String folioAbierto, String folioCerrado,
			String folioDuracion, String folioGrupo, Long folioIncidencia, String folioParentid, String folioStatus,
			String folioSubtipo, String folioTipo, String folioTitulo, String folioTitulo2, String folioTitulo3,
			String folioUltimamodificacion, String impacto, String nomSolicitante, String nomSolicitanteAa,
			String nom2Solicitante, String nom2SolicitanteAa, String prioridad, String region, String regionciudad,
			String resumen, String solucion, String ticketSExterno, String tiempoImputableCte,
			String tiempoImputableTpe, String urgencia, String zNomPuntaInex, String zesProactivoReactivo,
			String zfallaImputableCte, String zfallaImputableTpe, String organizacion, String succes, String mssg,
			String refNum, String ztiempoFallaProv, String ztiempoFallaTerceros) {
		super();
		this.actRealizadas = actRealizadas;
		this.activo = activo;
		this.afectacion = afectacion;
		this.apeSolicitante = apeSolicitante;
		this.apeSolicitanteAa = apeSolicitanteAa;
		this.categoria = categoria;
		this.creadoVia = creadoVia;
		this.descripcion = descripcion;
		this.diagFinal = diagFinal;
		this.diagInicial = diagInicial;
		this.fhSolucion = fhSolucion;
		this.fhndesolucion = fhndesolucion;
		this.folioAbierto = folioAbierto;
		this.folioCerrado = folioCerrado;
		this.folioDuracion = folioDuracion;
		this.folioGrupo = folioGrupo;
		this.folioIncidencia = folioIncidencia;
		this.folioParentid = folioParentid;
		this.folioStatus = folioStatus;
		this.folioSubtipo = folioSubtipo;
		this.folioTipo = folioTipo;
		this.folioTitulo = folioTitulo;
		this.folioTitulo2 = folioTitulo2;
		this.folioTitulo3 = folioTitulo3;
		this.folioUltimamodificacion = folioUltimamodificacion;
		this.impacto = impacto;
		this.nomSolicitante = nomSolicitante;
		this.nomSolicitanteAa = nomSolicitanteAa;
		this.nom2Solicitante = nom2Solicitante;
		this.nom2SolicitanteAa = nom2SolicitanteAa;
		this.prioridad = prioridad;
		this.region = region;
		this.regionciudad = regionciudad;
		this.resumen = resumen;
		this.solucion = solucion;
		this.ticketSExterno = ticketSExterno;
		this.tiempoImputableCte = tiempoImputableCte;
		this.tiempoImputableTpe = tiempoImputableTpe;
		this.urgencia = urgencia;
		this.zNomPuntaInex = zNomPuntaInex;
		this.zesProactivoReactivo = zesProactivoReactivo;
		this.zfallaImputableCte = zfallaImputableCte;
		this.zfallaImputableTpe = zfallaImputableTpe;
		this.organizacion = organizacion;
		this.succes = succes;
		this.mssg = mssg;
		this.refNum = refNum;
		this.ztiempoFallaProv = ztiempoFallaProv;
		this.ztiempoFallaTerceros = ztiempoFallaTerceros;
	}



	public String getActRealizadas() {
		return actRealizadas;
	}

	public void setActRealizadas(String actRealizadas) {
		this.actRealizadas = actRealizadas;
	}

	public BigDecimal getActivo() {
		return activo;
	}

	public void setActivo(BigDecimal activo) {
		this.activo = activo;
	}

	public String getAfectacion() {
		return afectacion;
	}

	public void setAfectacion(String afectacion) {
		this.afectacion = afectacion;
	}

	public String getApeSolicitante() {
		return apeSolicitante;
	}

	public void setApeSolicitante(String apeSolicitante) {
		this.apeSolicitante = apeSolicitante;
	}

	public String getApeSolicitanteAa() {
		return apeSolicitanteAa;
	}

	public void setApeSolicitanteAa(String apeSolicitanteAa) {
		this.apeSolicitanteAa = apeSolicitanteAa;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCreadoVia() {
		return creadoVia;
	}

	public void setCreadoVia(String creadoVia) {
		this.creadoVia = creadoVia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDiagFinal() {
		return diagFinal;
	}

	public void setDiagFinal(String diagFinal) {
		this.diagFinal = diagFinal;
	}

	public String getDiagInicial() {
		return diagInicial;
	}

	public void setDiagInicial(String diagInicial) {
		this.diagInicial = diagInicial;
	}

	public String getFhSolucion() {
		return fhSolucion;
	}

	public void setFhSolucion(String fhSolucion) {
		this.fhSolucion = fhSolucion;
	}

	public String getFhndesolucion() {
		return fhndesolucion;
	}

	public void setFhndesolucion(String fhndesolucion) {
		this.fhndesolucion = fhndesolucion;
	}

	public String getFolioAbierto() {
		return folioAbierto;
	}

	public void setFolioAbierto(String folioAbierto) {
		this.folioAbierto = folioAbierto;
	}

	public String getFolioCerrado() {
		return folioCerrado;
	}

	public void setFolioCerrado(String folioCerrado) {
		this.folioCerrado = folioCerrado;
	}

	public String getFolioDuracion() {
		return folioDuracion;
	}

	public void setFolioDuracion(String folioDuracion) {
		this.folioDuracion = folioDuracion;
	}

	public String getFolioGrupo() {
		return folioGrupo;
	}

	public void setFolioGrupo(String folioGrupo) {
		this.folioGrupo = folioGrupo;
	}

	public Long getFolioIncidencia() {
		return folioIncidencia;
	}

	public void setFolioIncidencia(Long folioIncidencia) {
		this.folioIncidencia = folioIncidencia;
	}

	public String getFolioParentid() {
		return folioParentid;
	}

	public void setFolioParentid(String folioParentid) {
		this.folioParentid = folioParentid;
	}

	public String getFolioStatus() {
		return folioStatus;
	}

	public void setFolioStatus(String folioStatus) {
		this.folioStatus = folioStatus;
	}

	public String getFolioSubtipo() {
		return folioSubtipo;
	}

	public void setFolioSubtipo(String folioSubtipo) {
		this.folioSubtipo = folioSubtipo;
	}

	public String getFolioTipo() {
		return folioTipo;
	}

	public void setFolioTipo(String folioTipo) {
		this.folioTipo = folioTipo;
	}

	public String getFolioTitulo() {
		return folioTitulo;
	}

	public void setFolioTitulo(String folioTitulo) {
		this.folioTitulo = folioTitulo;
	}

	public String getFolioTitulo2() {
		return folioTitulo2;
	}

	public void setFolioTitulo2(String folioTitulo2) {
		this.folioTitulo2 = folioTitulo2;
	}

	public String getFolioTitulo3() {
		return folioTitulo3;
	}

	public void setFolioTitulo3(String folioTitulo3) {
		this.folioTitulo3 = folioTitulo3;
	}

	public String getFolioUltimamodificacion() {
		return folioUltimamodificacion;
	}

	public void setFolioUltimamodificacion(String folioUltimamodificacion) {
		this.folioUltimamodificacion = folioUltimamodificacion;
	}

	public String getImpacto() {
		return impacto;
	}

	public void setImpacto(String impacto) {
		this.impacto = impacto;
	}

	public String getNomSolicitante() {
		return nomSolicitante;
	}

	public void setNomSolicitante(String nomSolicitante) {
		this.nomSolicitante = nomSolicitante;
	}

	public String getNomSolicitanteAa() {
		return nomSolicitanteAa;
	}

	public void setNomSolicitanteAa(String nomSolicitanteAa) {
		this.nomSolicitanteAa = nomSolicitanteAa;
	}

	public String getNom2Solicitante() {
		return nom2Solicitante;
	}

	public void setNom2Solicitante(String nom2Solicitante) {
		this.nom2Solicitante = nom2Solicitante;
	}

	public String getNom2SolicitanteAa() {
		return nom2SolicitanteAa;
	}

	public void setNom2SolicitanteAa(String nom2SolicitanteAa) {
		this.nom2SolicitanteAa = nom2SolicitanteAa;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionciudad() {
		return regionciudad;
	}

	public void setRegionciudad(String regionciudad) {
		this.regionciudad = regionciudad;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public String getTicketSExterno() {
		return ticketSExterno;
	}

	public void setTicketSExterno(String ticketSExterno) {
		this.ticketSExterno = ticketSExterno;
	}

	public String getTiempoImputableCte() {
		return tiempoImputableCte;
	}

	public void setTiempoImputableCte(String tiempoImputableCte) {
		this.tiempoImputableCte = tiempoImputableCte;
	}

	public String getTiempoImputableTpe() {
		return tiempoImputableTpe;
	}

	public void setTiempoImputableTpe(String tiempoImputableTpe) {
		this.tiempoImputableTpe = tiempoImputableTpe;
	}

	public String getUrgencia() {
		return urgencia;
	}

	public void setUrgencia(String urgencia) {
		this.urgencia = urgencia;
	}

	public String getzNomPuntaInex() {
		return zNomPuntaInex;
	}

	public void setzNomPuntaInex(String zNomPuntaInex) {
		this.zNomPuntaInex = zNomPuntaInex;
	}

	public String getZesProactivoReactivo() {
		return zesProactivoReactivo;
	}

	public void setZesProactivoReactivo(String zesProactivoReactivo) {
		this.zesProactivoReactivo = zesProactivoReactivo;
	}

	public String getZfallaImputableCte() {
		return zfallaImputableCte;
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

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getZtiempoFallaProv() {
		return ztiempoFallaProv;
	}

	public void setZtiempoFallaProv(String ztiempoFallaProv) {
		this.ztiempoFallaProv = ztiempoFallaProv;
	}

	public String getZtiempoFallaTerceros() {
		return ztiempoFallaTerceros;
	}

	public void setZtiempoFallaTerceros(String ztiempoFallaTerceros) {
		this.ztiempoFallaTerceros = ztiempoFallaTerceros;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketConcilied [actRealizadas=");
		builder.append(actRealizadas);
		builder.append(", activo=");
		builder.append(activo);
		builder.append(", afectacion=");
		builder.append(afectacion);
		builder.append(", apeSolicitante=");
		builder.append(apeSolicitante);
		builder.append(", apeSolicitanteAa=");
		builder.append(apeSolicitanteAa);
		builder.append(", categoria=");
		builder.append(categoria);
		builder.append(", creadoVia=");
		builder.append(creadoVia);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", diagFinal=");
		builder.append(diagFinal);
		builder.append(", diagInicial=");
		builder.append(diagInicial);
		builder.append(", fhSolucion=");
		builder.append(fhSolucion);
		builder.append(", fhndesolucion=");
		builder.append(fhndesolucion);
		builder.append(", folioAbierto=");
		builder.append(folioAbierto);
		builder.append(", folioCerrado=");
		builder.append(folioCerrado);
		builder.append(", folioDuracion=");
		builder.append(folioDuracion);
		builder.append(", folioGrupo=");
		builder.append(folioGrupo);
		builder.append(", folioIncidencia=");
		builder.append(folioIncidencia);
		builder.append(", folioParentid=");
		builder.append(folioParentid);
		builder.append(", folioStatus=");
		builder.append(folioStatus);
		builder.append(", folioSubtipo=");
		builder.append(folioSubtipo);
		builder.append(", folioTipo=");
		builder.append(folioTipo);
		builder.append(", folioTitulo=");
		builder.append(folioTitulo);
		builder.append(", folioTitulo2=");
		builder.append(folioTitulo2);
		builder.append(", folioTitulo3=");
		builder.append(folioTitulo3);
		builder.append(", folioUltimamodificacion=");
		builder.append(folioUltimamodificacion);
		builder.append(", impacto=");
		builder.append(impacto);
		builder.append(", nomSolicitante=");
		builder.append(nomSolicitante);
		builder.append(", nomSolicitanteAa=");
		builder.append(nomSolicitanteAa);
		builder.append(", nom2Solicitante=");
		builder.append(nom2Solicitante);
		builder.append(", nom2SolicitanteAa=");
		builder.append(nom2SolicitanteAa);
		builder.append(", prioridad=");
		builder.append(prioridad);
		builder.append(", region=");
		builder.append(region);
		builder.append(", regionciudad=");
		builder.append(regionciudad);
		builder.append(", resumen=");
		builder.append(resumen);
		builder.append(", solucion=");
		builder.append(solucion);
		builder.append(", ticketSExterno=");
		builder.append(ticketSExterno);
		builder.append(", tiempoImputableCte=");
		builder.append(tiempoImputableCte);
		builder.append(", tiempoImputableTpe=");
		builder.append(tiempoImputableTpe);
		builder.append(", urgencia=");
		builder.append(urgencia);
		builder.append(", zNomPuntaInex=");
		builder.append(zNomPuntaInex);
		builder.append(", zesProactivoReactivo=");
		builder.append(zesProactivoReactivo);
		builder.append(", zfallaImputableCte=");
		builder.append(zfallaImputableCte);
		builder.append(", zfallaImputableTpe=");
		builder.append(zfallaImputableTpe);
		builder.append(", organizacion=");
		builder.append(organizacion);
		builder.append(", succes=");
		builder.append(succes);
		builder.append(", mssg=");
		builder.append(mssg);
		builder.append(", refNum=");
		builder.append(refNum);
		builder.append(", ztiempoFallaProv=");
		builder.append(ztiempoFallaProv);
		builder.append(", ztiempoFallaTerceros=");
		builder.append(ztiempoFallaTerceros);
		builder.append("]");
		return builder.toString();
	}
}

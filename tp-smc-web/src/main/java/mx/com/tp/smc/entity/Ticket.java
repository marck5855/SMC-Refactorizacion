package mx.com.tp.smc.entity;

import java.util.List;

public class Ticket {

	private String refNum;
	private String priority;
	private String category;
	private String status;
	private String assignee;
	private String group;
	private String tfe;
	private String contactLocation;
	private String nombreSolicitante;
	private String folioTitulo3;
	private String descripcion;
	private String resumen;
	private String solucion;
	private String fechaHoraSolucion;
	private String fechaApertura;
	private String ultimaModificacion;
	private String fechaCierre;
	private String diagFinal;
	private String cliente;
	private String tiempoEnCurso;
	private Long slaViolation;
	private Long macroPredictViol;
	
	private String persid;
	private String description;
	private String dateInsert;
	private String callReqid;
	
	private List<Comment> comment;

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getTfe() {
		return tfe;
	}

	public void setTfe(String tfe) {
		this.tfe = tfe;
	}

	public String getContactLocation() {
		return contactLocation;
	}

	public void setContactLocation(String contactLocation) {
		this.contactLocation = contactLocation;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFolioTitulo3() {
		return folioTitulo3;
	}

	public void setFolioTitulo3(String folioTitulo3) {
		this.folioTitulo3 = folioTitulo3;
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

	public String getFechaHoraSolucion() {
		return fechaHoraSolucion;
	}

	public void setFechaHoraSolucion(String fechaHoraSolucion) {
		this.fechaHoraSolucion = fechaHoraSolucion;
	}

	public String getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public String getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(String ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getDiagFinal() {
		return diagFinal;
	}

	public void setDiagFinal(String diagFinal) {
		this.diagFinal = diagFinal;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	

	public String getTiempoEnCurso() {
		return tiempoEnCurso;
	}

	public void setTiempoEnCurso(String tiempoEnCurso) {
		this.tiempoEnCurso = tiempoEnCurso;
	}

	public Long getSlaViolation() {
		return slaViolation;
	}

	public void setSlaViolation(Long slaViolation) {
		this.slaViolation = slaViolation;
	}

	public Long getMacroPredictViol() {
		return macroPredictViol;
	}

	public void setMacroPredictViol(Long macroPredictViol) {
		this.macroPredictViol = macroPredictViol;
	}

	public String getPersid() {
		return persid;
	}

	public void setPersid(String persid) {
		this.persid = persid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(String dateInsert) {
		this.dateInsert = dateInsert;
	}

	public String getCallReqid() {
		return callReqid;
	}

	public void setCallReqid(String callReqid) {
		this.callReqid = callReqid;
	}

	
	
	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [refNum=");
		builder.append(refNum);
		builder.append(", priority=");
		builder.append(priority);
		builder.append(", category=");
		builder.append(category);
		builder.append(", status=");
		builder.append(status);
		builder.append(", assignee=");
		builder.append(assignee);
		builder.append(", group=");
		builder.append(group);
		builder.append(", tfe=");
		builder.append(tfe);
		builder.append(", contactLocation=");
		builder.append(contactLocation);
		builder.append(", nombreSolicitante=");
		builder.append(nombreSolicitante);
		builder.append(", folioTitulo3=");
		builder.append(folioTitulo3);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", resumen=");
		builder.append(resumen);
		builder.append(", solucion=");
		builder.append(solucion);
		builder.append(", fechaHoraSolucion=");
		builder.append(fechaHoraSolucion);
		builder.append(", fechaApertura=");
		builder.append(fechaApertura);
		builder.append(", ultimaModificacion=");
		builder.append(ultimaModificacion);
		builder.append(", fechaCierre=");
		builder.append(fechaCierre);
		builder.append(", diagFinal=");
		builder.append(diagFinal);
		builder.append(", cliente=");
		builder.append(cliente);
		builder.append(", tiempoEnCurso=");
		builder.append(tiempoEnCurso);
		builder.append(", slaViolation=");
		builder.append(slaViolation);
		builder.append(", macroPredictViol=");
		builder.append(macroPredictViol);
		builder.append(", persid=");
		builder.append(persid);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateInsert=");
		builder.append(dateInsert);
		builder.append(", callReqid=");
		builder.append(callReqid);
		builder.append("]");
		return builder.toString();
	}


}

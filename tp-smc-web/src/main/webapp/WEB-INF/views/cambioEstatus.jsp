<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp"></jsp:include>

<link href="/portaltpe/static/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
<link href="/portaltpe/static/vendor/bootstrap/css/responsive.bootstrap.min.css" rel="stylesheet">	

<script src="<c:url value='/static/vendor/jquery/js/jquery.js' />"></script>
<script src="<c:url value='/static/vendor/datatables/js/jquery.dataTables.min.js' />"></script>
<script src="<c:url value='/static/vendor/datatables-plugins/dataTables.bootstrap.min.js' />"></script>
<script src="<c:url value='/static/vendor/datatables-responsive/dataTables.responsive.js' />"></script>

<style>
#description {
	background-color: #f5f5f5;
	text-align: center;
	font-weight: bold;
}

#information {
	text-align: justify;
}
</style>

<script type="text/javascript">
	function descargaPDF(){
		alert("Se a exportado la informacion a un Documento PDF - Favor de validar en la Unidad C: de tu equipo.");
	}
</script>

<div class="container-fluid">
	<div class="row">
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Lista de incidentes</div>
					<!-- /.panel-heading -->
					<div class="panel-body">


					<div class="table-responsive" style="overflow-y:scroll; width:100%; height:600px">
						<table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th>Incidente Núm.</th>
									<th>Prioridad</th>
									<th>Categoría</th>
									<th>Estado</th>
									<th>Asignatario</th>
									<th>Punta</th>
<!-- 									<th>Id Servicio</th> -->
									<th>Fecha Apertura</th>
									<th>Tiempo en Curso</th>
									
								</tr>
							</thead>
							
							<tbody>
							 <c:forEach items="${model.tickets}" var="ticket">
								<tr>
								<td>
									<c:set var="sla" value="${ticket.slaViolation}"/> 
									<c:set var="macro" value="${ticket.macroPredictViol}"/>
									
									<c:if test="${sla == 0 && macro ==0}">
										<a href="#" data-toggle="modal" data-target="#${ticket.refNum}" style="color:#04B404"> <c:out value="${ticket.refNum}"/></a>
									</c:if>
									
									<c:if test="${sla == 0  && macro >=1}">
										<a href="#" data-toggle="modal" data-target="#${ticket.refNum}" style="color:#FFBF00"> <c:out value="${ticket.refNum}"/></a>
									</c:if>
									
									<c:if test="${sla >= 1  && macro >=1}">
										<a href="#" data-toggle="modal" data-target="#${ticket.refNum}" style="color:#FF0000"> <c:out value="${ticket.refNum}"/></a>
									</c:if>
						 		</td>
					

										<div class="modal fade" id="${ticket.refNum}" tabindex="-1" role="dialog" aria-labelledby="basicModal" data-backdrop="false" style="position: absolute;">
											<div class="modal-dialog" style="width: 80%">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title h4">Detalles del Incidente</h4>
													</div>
													<div class="modal-body">
														<div class="row">

															<div class="col-lg-4 col-md-4">
																<center>
																	<label>Solicitante</label>
																</center>
															</div>
															<div class="col-lg-4 col-md-4">
																<center>
																	<label>Punta</label>
																</center>
															</div>
															<div class="col-lg-4 col-md-4">
																<center>
																	<label>Categoría</label>
																</center>
															</div>

															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text"
																	name="nombreSolicitante" id="nombreSolicitante"
																	value="${ticket.assignee}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text"
																	name="folioTitulo3" id="folioTitulo3"
																	value="${ticket.cliente}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="category"
																	id="category" value="${ticket.category}" readonly="readonly">
															</div>

															<input type="hidden" name="refNum" id="refNum"
																value="${ticket.refNum}" readonly="readonly">

															<div class="col-lg-12 col-md-12">
																<label>Descripción</label>
															</div>
															<div class="col-lg-12 col-md-12">
																<textarea class="form-control" rows="10" name="resumen" readonly="readonly">${ticket.descripcion}</textarea>
															</div>


															<div class="col-lg-12 col-md-12">
																<center>
																	<label>Información del Incidente</label>
																</center>
															</div>

															<div class="col-lg-10 col-md-10">
																<center>
																	<label>Resumen</label>
																</center>
															</div>
															
															<div class="col-lg-4 col-md-4">
																<center>
																	<label>Estatus</label>
																</center>
															</div>
															
															<div class="col-lg-8 col-md-8">
																<input class="form-control" type="text" name="descripcion" id="descripcion" value="${ticket.resumen}" readonly="readonly"> 
															</div>
															
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="status" id="status" value="${ticket.status}" readonly="readonly">
															</div>

															<div class="col-lg-12 col-md-12">
																<label>Diagnóstico Final</label>
															</div>
															<div class="col-lg-12 col-md-12">
																<input class="form-control" type="text"
																	name="diagnosticofinal" id="diagnosticofinal"
																	value="${ticket.diagFinal}" readonly="readonly"/>
															</div>

															<div class="col-lg-8 col-md-8">
																<label>Solución</label>
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Fecha/Hora de solución</label>
															</div>
															<div class="col-lg-8 col-md-8">
																<input class="form-control" type="text" name="solucion"
																	id="solucion" value="${ticket.solucion}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text"
																	name="fechasolucion" id="fechasolucion"
																	value="${ticket.fechaHoraSolucion}" readonly="readonly">
															</div>

															<div class="col-lg-4 col-md-4">
																<label>Fecha de Apertura</label>
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Última Modificación</label>
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Fecha de Cierre</label>
															</div>

															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text"
																	name="fechaapertura" id="fechaapertura"
																	value="${ticket.fechaApertura}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text"
																	name="ultimaModificacion" id="ultimaModificacion"
																	value="${ticket.ultimaModificacion}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text"
																	name="fechaCierre" id="fechaCierre"
																	value="${ticket.fechaCierre}" readonly="readonly">
															</div>
															
															<div class="col-lg-12 col-md-12">
															<form:form name="formularioAgregarComent" action="/portaltpe/home/incidents/CambiarStatus">
																<c:set var="valor1" value="5200"/><!-- Abierto -->
																<c:set var="valor2" value="5201" /><!-- Cerrado -->
																<c:set var="valor3" value="5208" /><!-- En curso -->
																<c:set var="valor4" value="5212" /><!-- Resuelto -->
																<c:set var="valor6" value="40002" /><!-- Rechazado -->
																<c:set var="valor7" value="5214" /><!-- Cancelado -->
																<c:set var="valor8" value="400357" /><!-- Pre-ticket -->
																<c:set var="valor9" value="5217" /><!-- Pendiente x provedor -->
																<c:set var="valor10" value="5216" /><!-- Pendiente x cliente -->
																<c:set var="valor11" value="400305" /><!-- Pendiente x terceros -->
																
																<div class="col-lg-12 col-md-12">
																<%-- <select name="selectStatus" >
																	<option value="0" selected="selected">Selecciona un status...</option>
																	<c:forEach items="${modelstatus.status}" var="status">
																	<c:if test="${status.idStatus eq valor1 || status.idStatus eq valor2 || status.idStatus eq valor3 || status.idStatus eq valor4 || status.idStatus eq valor5}">
																		<option value="${status.sym}"><c:out value="${status.sym}"/></option>
																	</c:if>
																	</c:forEach>
																</select> --%>
																<!--  -->
																	<label>Seleccionar Estatus</label>
																<select name="selectStatus" class="status">
																	<option value="0" selected="selected">Selecciona un status...</option>
																	<c:forEach items="${modelstatus.status}" var="status">
																	<c:if test="${ticket.status eq 'Abierto'}">
																	<c:if test="${ status.idStatus eq valor3 || status.idStatus eq valor8 || status.idStatus eq valor7}">
																		<option value="${status.sym}"><c:out value="${status.sym}"/></option>
																	</c:if>
																	</c:if>
																	<c:if test="${ticket.status eq 'Cerrado'}">
																		<option></option>
																	</c:if>
																	<c:if test="${ticket.status eq 'En curso'}">
																	<c:if test="${ status.idStatus eq valor4 || status.idStatus eq valor10 || status.idStatus eq valor9 || status.idStatus eq valor11 || status.idStatus eq valor7}">
																		<option value="${status.sym}"><c:out value="${status.sym}"/></option>
																	</c:if>
																	</c:if>
																	<c:if test="${ticket.status eq 'Resuelto'}">
																	<c:if test="${ status.idStatus eq valor3 || status.idStatus eq valor2}">
																		<option value="${status.sym}"><c:out value="${status.sym}"/></option>
																	</c:if>
																	</c:if>
																	<c:if test="${ticket.status eq 'Pre-Ticket'}">
																	<c:if test="${ status.idStatus eq valor3 || status.idStatus eq valor4 || status.idStatus eq valor2}">
																		<option value="${status.sym}"><c:out value="${status.sym}"/></option>
																	</c:if>
																	</c:if>
																	<c:if test="${ticket.status eq 'Pendiente por Cliente'}">
																	<c:if test="${ status.idStatus eq valor3 }">
																		<option value="${status.sym}"><c:out value="${status.sym}"/></option>
																	</c:if>
																	</c:if>
																	<c:if test="${ticket.status eq 'Pendiente por Provedor'}">
																	<c:if test="${ status.idStatus eq valor3 }">
																		<option value="${status.sym}"><c:out value="${status.sym}"/></option>
																	</c:if>
																	</c:if>
																	<c:if test="${ticket.status eq 'Pendiente por Terceros'}">
																	<c:if test="${ status.idStatus eq valor3 }">
																		<option value="${status.sym}"><c:out value="${status.sym}"/></option>
																	</c:if>
																	</c:if>
																	</c:forEach>
																</select>
																
															    <label class="etiquetaFormulario col-md-12">*Region</label>
														 			
														 		<select class="chosen-select" id="region" name="region">
																	<option value="0" selected="selected">Seleccione...</option>
														 			<c:forEach items="${region.regiones}" var="regiones">
																		<option value="${regiones.id}">${regiones.sym}</option>	
																	</c:forEach>
																</select>
														 
																
																</div>
																<!--  -->
																<input type="hidden" name="incidente" value="${ticket.refNum}">
																
																<div class="col-lg-12 col-md-12">
																	<div class="modal-footer">
																		<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
																		<button type="submit" class="btn btn-default" name="btnStatus">Cambiar Estatus</button>																
																	</div>
																</div>
															</form:form>
															
														</div>

													</div>
													

												</div>
											</div>
										</div>


										<td><c:out value="${ticket.priority}" /></td>
										<td><c:out value="${ticket.category}" /></td>
										<td><c:out value="${ticket.status}" /></td>
										<td><c:out value="${ticket.assignee}" /></td>
										
										<td><c:out value="${ticket.cliente}" /></td>
<%-- 										<td><c:out value="${ticket.tfe}" /></td> --%>
										<td><c:out value="${ticket.fechaApertura}"></c:out></td>
										<td><c:out value="${ticket.tiempoEnCurso}" /></td>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- /.table-responsive -->
						</div>
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->

	</div>
	</div>

	<script>
		$(document)
				.ready(
						function() {
							$('#dataTables-example')
									.DataTable(
											{
												responsive : true,
												language : {
													"lengthMenu" : "Mostrar _MENU_ registros por página",
													"zeroRecords" : "No se encontraron registros",
													"info" : "Mostrando _PAGE_ páginas de _PAGES_",
													"infoEmpty" : "No hay registros disponibles",
													"infoFiltered" : "(filtrado de un total de _MAX_ registros)",
													"loadingRecords" : "Cargando...",
													"processing" : "Procesando...",
													"infoPostFix" : "",
													"decimal" : "",
													"search" : "Búsqueda",
													"paginate" : {
														"first" : "Primero",
														"last" : "Último",
														"next" : "Siguiente",
														"previous" : "Previo"
													}
												}
											});
						});
	</script>

	<jsp:include page="footer.jsp"></jsp:include>
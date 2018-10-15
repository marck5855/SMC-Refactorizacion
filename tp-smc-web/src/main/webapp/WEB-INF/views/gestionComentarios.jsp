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
									<th>Cliente</th>
									<th>Id Servicio</th>
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
					

										<div class="modal fade" id="${ticket.refNum}" tabindex="-1" role="dialog" aria-labelledby="basicModal" data-backdrop="false">
											<div class="modal-dialog" style="width: 80%">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title h4">Agregar Comentario al Ticket</h4>
													</div>
													<div class="modal-body">
														<div class="row">
															
															<div class="col-lg-4 col-md-4">
																<label>Agregar Comentario</label>
															</div>
															
															<div class="col-lg-12 col-md-12">
																<form:form name="formularioAgregarComent" action="/portaltpe/home/incidents/AgregarComentario">
															  		<textarea class="form-control" rows="5" name="comentario"></textarea>
															  		<input type="hidden" name="incidente" value="${ticket.refNum}">
															  		<button type="submit" class="botonFormulario" name="btnComent">Agregar Comentario</button>
																</form:form>
															</div>
															
															<div class="col-lg-4 col-md-4">
																<label>Comentarios del Ticket</label>

																		
																		<div class="container">
																		
																		  <div class="row">
																		    <div class="col-6 col-md-4">
																		     ${ticket.dateInsert}
																		    </div>
																		    <div class="col-6 col-md-4">
																		     ${ticket.description}
																		    </div>
																		    <div class="col-6 col-md-4">
																		     SMC
																		    </div>
																		  </div>
																		  <div class="row"> &nbsp;</div>
																
																		</div>
																			
															</div>

															
														</div>

													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Cerrar</button>
													</div>

												</div>
											</div>
										</div>


										<td><c:out value="${ticket.priority}" /></td>
										<td><c:out value="${ticket.category}" /></td>
										<td><c:out value="${ticket.status}" /></td>
										<td><c:out value="${ticket.assignee}" /></td>
										
										<td><c:out value="${ticket.cliente}" /></td>
										<td><c:out value="${ticket.tfe}" /></td>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"></jsp:include>

<link
	href="/portaltpe/static/vendor/datatables-plugins/dataTables.bootstrap.css"
	rel="stylesheet">

<script src="<c:url value='/static/vendor/jquery/js/jquery.js' />"></script>
<script
	src="<c:url value='/static/vendor/datatables/js/jquery.dataTables.min.js' />"></script>
<script
	src="<c:url value='/static/vendor/datatables-plugins/dataTables.bootstrap.min.js' />"></script>
<script
	src="<c:url value='/static/vendor/datatables-responsive/dataTables.responsive.js' />"></script>
	
   
<div class="container-fluid">
	<div class="row">

		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					

					<div class="panel-body">

					

						<table width="100%"
							class="table table-striped table-bordered table-hover"	id="tablaNoConciliados">
							<thead>
								<tr>
									<th>Incidente núm.</th>
									<th>Fecha Apertura</th>
									<th>Resumen</th>
									<th>Sitio</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${model.tickets}" var="TicketConcilied">
							
								<tr>
								    
								    <td>
	                                   	<a href="#" onclick="FormularioPorConciliar('<c:out value="${TicketConcilied.nomSolicitante}" />','<c:out value="${TicketConcilied.nom2Solicitante}" />','<c:out value="${TicketConcilied.apeSolicitante}" />','<c:out value="${TicketConcilied.categoria}" />','<c:out value="${TicketConcilied.folioTitulo}" />  <c:out value="${TicketConcilied.folioTitulo3}" />','<c:out value="${TicketConcilied.descripcion}" />','<c:out value="${TicketConcilied.solucion}" />','<c:out value="${TicketConcilied.folioCerrado}" />','<c:out value="${TicketConcilied.folioAbierto}" />');">
	                                        <c:out value="${TicketConcilied.folioIncidencia}" />
	                                    </a>
	                                </td>
								    
								    
								    								    
								
									<td><c:out value="${TicketConcilied.folioAbierto}" /></td>
									<td><c:out value="${TicketConcilied.resumen}" /></td>
									<td><c:out value="${TicketConcilied.folioTitulo3}" /></td>
									
								</tr>
																
							</c:forEach>	
							</tbody>
						</table>

					</div>

				</div>

			</div>

		</div>


	</div>
</div>	
     							<div class="modal fade" id="ModalPorConciliar" tabindex="-1" role="dialog" aria-labelledby="basicModal" data-backdrop="false">
										      <div class="modal-dialog" style="width:80%">
										        <div class="modal-content">
										          <div class="modal-header">
										            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										            <h4 class="modal-title h4">Detalles del Incidente</h4>
										          </div>
										          <div class="modal-body">
										            <div class="row">
										            <form action="" name="formularioporconciliar" method="post">
										            
															<table width="100%"	class="table-striped table-hover table-responsive">
														<thead>
															
																<tr>
												
																	<th class="active">Incidentes por Conciliar</th>
												
																</tr>	
																<tr>
																	<th class="active">1° Nombre</th>
																	<th class="active">2° Nombre</th>
																	<th class="active">Apellidos</th>
																	<th class="active">Usuario Final Afectado/Cliente</th>
																	<th class="active">Categoría</th>
																</tr>
															
														</thead>
														<tbody>
															<tr>
																<td id="nombre1"> </td>
																<td id="nombre2"> </td>
																<td id="apellidos"> </td>
																<td id="usuariofinal"> </td>
																<td id="categoria"> </td>
									
															</tr>
								
							
													</tbody>
													</table>
													
													
  
									                
													<div class="col-lg-10 col-md-10">
													<center>
														<label>Descripción</label>
													</center>	
													</div>
													<div class="col-lg-8 col-md-8">
														<textarea class="form-control" rows="10" name="descripcion" required=""></textarea>
													</div>	
													
													
													<div class="col-lg-12 col-md-12">
														<center>
															<label>Información de Resumen</label>
														</center>
													</div>
													<div class="col-lg-12 col-md-12">
														
															<label>Resumen</label>
														
													</div>
													
													<div class="col-lg-12 col-md-12" id="resumen">
														
															
														
													</div>
													
													<div class="col-lg-8 col-md-8">
														
															<label>Solución</label>
														
													</div>
													
													<div class="col-lg-4 col-md-4">
														
															<label>Fecha/Hora de solución</label>
														
													</div>
													
													<div class="col-lg-8 col-md-8" id="solucion">
														
															
														
													</div>
													
													<div class="col-lg-4 col-md-4" id="fechasolucion">
														
															
														
													</div>
													
													<div class="col-lg-8 col-md-8">
														
															<label>Fecha/Hora de Apertura</label>
														
													</div>
													
													<div class="col-lg-8 col-md-8" id="fechaapertura">
														
															
														
													</div>
													</form>
										            </div>
										           </div>
										          <div class="modal-footer">
										            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
										          </div>
										        </div>
										      </div>
		 							</div>							    
        
        <script>
		    $(document).ready(function() {
		        $('#tablaNoConciliados').DataTable({
		            responsive: true,
		            language: {
		            	"lengthMenu": "Mostrar _MENU_ registros por página",
		            	"zeroRecords": "No se encontraron registros",
		            	"info": "Mostrando _PAGE_ páginas de _PAGES_",
		            	"infoEmpty": "No hay registros disponibles",
		            	"infoFiltered": "(filtrado de un total de _MAX_ registros)",
		            	"loadingRecords": "Cargando...",
		            	"processing": "Procesando...",
		            	"infoPostFix": "",
		            	"decimal": "",
		            	"search": "Búsqueda",
		            	"paginate": {
		            		"first": "Primero",
		            		"last": "Último",
		            		"next": "Siguiente",
		            		"previous": "Previo"
		            	}
		            }
		        });
		    });
	    </script>
	
        
        
        
	<jsp:include page="footer.jsp"></jsp:include>
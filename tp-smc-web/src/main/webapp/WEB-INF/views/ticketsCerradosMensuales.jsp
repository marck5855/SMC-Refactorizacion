<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"></jsp:include>

<link
	href="/portaltpe/static/vendor/datatables-plugins/dataTables.bootstrap.css"
	rel="stylesheet">

<script src="<c:url value='/static/vendor/jquery/js/jquery.js' />"></script>
<script src="<c:url value='/static/vendor/datatables/js/jquery.dataTables.min.js' />"></script>
<script src="<c:url value='/static/vendor/datatables-plugins/dataTables.bootstrap.min.js' />"></script>
<script src="<c:url value='/static/vendor/datatables-responsive/dataTables.responsive.js' />"></script>
	


<div class="container-fluid">
	<div class="row">

		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					
					 <div class="panel-heading">
                            Tickets Cerrados
                        </div>
					<div class="panel-body">
					<div style="text-align: right">
<%-- 					<form:form action="/portaltpe/home/incidents/exportarpdf"> --%>
<!-- 						<div> -->
<!-- 							<input type="submit" class="btn btn-default" name="btnExportarPDF" download=""  value="Exportar a PDF"> -->
							
<!-- 						</div> -->
<%-- 					</form:form> --%>
					</div>
						<table width="100%"
							class="table table-striped table-bordered table-hover" id="tablaTicketsCerrados">
							<thead>
								<tr>
										<th>Incidente Num.</th>
                                        <th>Usuario Final Afectado/Cliente.</th>
                                        <th>ID del Servicio.</th>
<!--                                         <th>Ticket.</th> -->
                                        <th>Afectación Enlace.</th>
                                        <th>Estatus.</th>
                                        <th>Afectación Enlace Cliente.</th>
                                        <th>Hora Alta Ticket.</th>
                                        <th>Diagnóstico Final.</th>
                                        <th>Solución.</th>
                                        
                                    </tr>
							</thead>
							<tbody>
							<c:forEach items="${model.tickets}" var="ticket">
							
								<tr>
									<td>
										<a href="#" data-toggle="modal" data-target="#${ticket.folioIncidencia}">
											<c:out value="${ticket.folioIncidencia}" />
										</a>
									</td>
									
									<div class="modal fade" id="${ticket.folioIncidencia}" tabindex="-1" role="dialog" aria-labelledby="basicModal" data-backdrop="false" style="position: absolute;">
										      <div class="modal-dialog" style="width: 80%">
										        <div class="modal-content">
										          <div class="modal-header">
										            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										            <h4 class="modal-title h4">Detalles del Incidente</h4>
										          </div>
										          <div class="modal-body">
										            <div class="row">
										            	<form:form action="/portaltpe/home/incidents/updateajustesvalidacion" name="formularioajustesconciliacion">

															<div class="col-lg-2 col-md-2">
																<center>
																	<label>1° Nombre</label>
																</center>
															</div>
															<div class="col-lg-2 col-md-2">
																<center>
																	<label>2° Nombre</label>
																</center>
															</div>
															<div class="col-lg-2 col-md-2">
																<center>
																	<label>Apellidos</label>
																</center>
															</div>
															<div class="col-lg-2 col-md-2">
																<center>
																	<label>Usuario Final</label>
																</center>
															</div>
															<div class="col-lg-2 col-md-2">
																<center>
																	<label>Afectado/Cliente</label>
																</center>
															</div>
															<div class="col-lg-2 col-md-2">
																<center>
																	<label>Categoría</label>
																</center>
															</div>
															
															
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text" name="nombre1" id="nombre1" value="${ticket.nomSolicitante}" pattern="[A-Za-z ´]+">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text" name="nombre2" id="nombre2" value="${ticket.nom2Solicitante}" pattern="[A-Za-z ´]+">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="apellidos" id="apellidos" value="${ticket.apeSolicitante}" pattern="[A-Za-z ´]+">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="usuariofinal" id="usuariofinal" value="${ticket.folioTitulo}" pattern="[A-Za-z -/\_]+" >
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="afectadocliente" id="afectadocliente" value="${ticket.folioTitulo3}" required="" pattern="[A-Za-z -/\_]+">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="categoria" id="categoria" value="${ticket.categoria}" required="">
															</div>
															
															<input type="hidden" name="folioincidencia" id="folioincidencia" value="${ticket.folioIncidencia}" >
															
															<div class="col-lg-10 col-md-10">
																<center>
																	<label>Descripción</label>
																</center>
															</div>
															
<!-- 															Este campo solo se mostrara unicamente con la organizacion de video vigilancia Puebla -->
															
															<div class="col-lg-2 col-md-2">
																<center>
																	<label>Num. Incidente</label>
																</center>
															</div>
															
															
															<div class="col-lg-10 col-md-10">
																<textarea class="form-control" rows="10" name="descripcion" required="">${ticket.descripcion}</textarea>
															</div>
															
<!-- 															Este campo solo se mostrara unicamente con la organizacion de video vigilancia Puebla -->
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text" name="numIncidente" id="numIncidente" value="${ticket.folioIncidencia}">
															</div>
														
															
<!-- 															Este campo solo se mostrara unicamente con la organizacion de video vigilancia Puebla -->
															
															<div class="col-lg-2 col-md-2">
																<center>
																	<label>Ticket Sistema Externo</label>
																</center>
															</div>
														
															
<!-- 															Este campo solo se mostrara unicamente con la organizacion de video vigilancia Puebla -->
															
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text" name="ticketExt" id="ticketExt" value="${ticket.ticketSExterno}">
															</div>
															
															
															
															<div class="col-lg-12 col-md-12">
																<center>
																	<label>Información de Resumen</label>
																</center>
															</div>
															<div class="col-lg-12 col-md-12">
																<label>Resumen</label>
															</div>
															<div class="col-lg-6 col-md-6">
																<label>Estatus</label>
															</div>
															<div class="col-lg-12 col-md-12">
																<input class="form-control" type="text" name="resumen" id="resumen" value="${ticket.resumen}" required="">
															</div>
															<div class="col-lg-6 col-md-6">
																<input class="form-control" type="text" name="estatus" id="estatus" value="${ticket.folioStatus}" required="">
															</div>

															<div class="col-lg-12 col-md-12">
																<label>Diagnóstico Final</label>
															</div>

															<div class="col-lg-12 col-md-12">
																<input class="form-control" type="text" name="diagnosticofinal" id="diagnosticofinal" value="${ticket.diagFinal}" required="">
															</div>
															<div class="col-lg-8 col-md-8">
																<label>Solución</label>
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Fecha/Hora de solución</label>
															</div>
															<div class="col-lg-8 col-md-8">
																<input class="form-control" type="text" name="solucion" id="solucion" value="${ticket.solucion}" required="">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="fechasolucion" id="fechasolucion" value="${ticket.folioCerrado}" required="">
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Fecha/Hora de Apertura</label>
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Tiempo Imputable ENLACE</label>
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Tiempo Imputable Cliente</label>
															</div>

															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="fechaapertura" id="fechaapertura" value="${ticket.folioAbierto}" required="">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="tiempoImputableTpe" id="tiempoImputableTpe"  value="${ticket.tiempoImputableTpe}">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="tiempoImputableCte" id="tiempoImputableCte" value="${ticket.tiempoImputableCte}">
															</div>
															
														
															<div class="col-lg-3 col-md-3">
																<label>Proactivo/Reactivo</label>
															</div>
															<div class="col-lg-3 col-md-3">
																<label>Afectacion al Servicio</label>
															</div>
															   
															<div class="col-lg-3 col-md-3">
																<label>&nbsp;</label>
															</div>
															<div class="col-lg-3 col-md-3">
																<label>&nbsp;</label>
															</div>
															<div class="col-lg-3 col-md-3">
																<input class="form-control" type="text" name="proactivoReactivo" id="proactivoReactivo" value="${ticket.zesProactivoReactivo}" required="">
															</div>
															<div class="col-lg-3 col-md-3">
																<input class="form-control" type="text" name="afectacion" id="afectacion" value="${ticket.afectacion}" required="">
															</div>
															<div class="col-lg-3 col-md-3">
																<label>Falla a Terceros</label>
															</div>
															<div class="col-lg-3 col-md-3">
																<label>Falla a Prov</label>
															</div>

															<div class="col-lg-3 col-md-3">
																<input class="form-control" type="text" name="ztiempoFallaTer" id="ztiempoFallaTer" value="${ticket.ztiempoFallaTerceros}" required=""  placeholder="hh:mn:ss">
															</div>
															<div class="col-lg-3 col-md-3">
																<input class="form-control" type="text" name="ztiempoFallaProv" id="ztiempoFallaProv" value="${ticket.ztiempoFallaProv}" required=""  placeholder="hh:mn:ss">
															</div>
														
										            </div>
													
										           </div>
										          <div class="modal-footer">
<!-- 										            <input type="submit" class="btn btn-default" name="btnConciliado" value="Guardar Ajustes"/> -->
<!-- 													<input type="submit" class="btn btn-default" name="btnConciliado" value="Validar"/> -->
<!-- 													<input type="submit" class="btn btn-default" name="btnConciliado" value="Conciliar"/> -->
													<input type="button" class="btn btn-default" data-dismiss="modal" value="Cerrar Ventana"/>
										          </div>
										          </form:form>
										        </div>
										      </div>
										    
										    
										    </div>
									
									
									
									
								    <td><c:out value="${ticket.folioTitulo}" /> <c:out value="${ticket.folioTitulo3}" /></td>
								    <td><c:out value="${ticket.folioTitulo}" /></td>
<%-- 								    <td><c:out value="${ticket.folioIncidencia}" /></td> --%>
								    <td><c:out value="${ticket.tiempoImputableTpe}" /></td>
								    <td><c:out value="${ticket.folioStatus}" /></td>
								    <td><c:out value="${ticket.tiempoImputableCte}" /></td>
								    <td><c:out value="${ticket.folioAbierto}" /></td>
								    <td><c:out value="${ticket.diagFinal}" /></td>
								    <td><c:out value="${ticket.solucion}" /></td>
								    
								    
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
    
 <script>
		    $(document).ready(function() {
		        $('#tablaTicketsCerrados').DataTable({
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
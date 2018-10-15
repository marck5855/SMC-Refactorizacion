
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                        <div class="panel-heading">
                            Lista de incidentes
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="tablaAjustesConciliacion">
                                <thead>
                                    <tr>
										<th>Incidente núm.</th>
										<th>Fecha Apertura</th>
										<th>Estatus</th>
										<th>Resumen</th>
										<th>Sitio</th>
									</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${model.tickets}" var="TicketConcilied">
	                                    <tr>
	                                        <td>
	                                        	<a href="#" data-toggle="modal" data-target="#${TicketConcilied.folioIncidencia}">
	                                        		<c:out value="${TicketConcilied.folioIncidencia}" />
	                                        	</a>
	                                        </td>
	                                        
	                                        
	                                        
	                                        	                                        
										    <div class="modal fade" id="${TicketConcilied.folioIncidencia}" tabindex="-1" role="dialog" aria-labelledby="basicModal" data-backdrop="false"
										    style=" position: absolute; z-index: 5;">
										      <div class="modal-dialog" style="width: 80%;">
										        <div class="modal-content">
										          <div class="modal-header">
										            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										            <h4 class="modal-title h4">Detalles del Incidente</h4>
										          </div>
										          <div class="modal-body">
										            <div class="row">
														<c:set var="org" value="0xaa6dd020097e9c4ab054104d1d4b3a34"/>
										            	
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
																<input class="form-control" type="text" name="nombre1" id="nombre1" value="${TicketConcilied.nomSolicitante}" pattern="[A-Za-z]+">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text" name="nombre2" id="nombre2" value="${TicketConcilied.nom2Solicitante}" pattern="[A-Za-z]+">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="apellidos" id="apellidos" value="${TicketConcilied.apeSolicitante}" pattern="[A-Za-z]+">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="usuariofinal" id="usuariofinal" value="${TicketConcilied.folioTitulo}" pattern="[A-Za-z -/\_]+" >
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="afectadocliente" id="afectadocliente" value="${TicketConcilied.folioTitulo3}" required="" pattern="[A-Za-z -/\_]+">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="categoria" id="categoria" value="${TicketConcilied.categoria}" required="">
															</div>
															
															<input type="hidden" name="folioincidencia" id="folioincidencia" value="${TicketConcilied.folioIncidencia}" >
															
															<div class="col-lg-10 col-md-10">
																<center>
																	<label>Descripción</label>
																</center>
															</div>
															
															<!-- Este campo solo se mostrara unicamente con la organizacion de video vigilancia Puebla -->
															<c:if test="${TicketConcilied.organizacion eq org}">
															<div class="col-lg-2 col-md-2">
																<center>
																	<label>num. Incidente</label>
																</center>
															</div>
															</c:if>
															
															<div class="col-lg-10 col-md-10">
																<textarea class="form-control" rows="10" name="descripcion" required="">${TicketConcilied.descripcion}</textarea>
															</div>
															
															<!-- Este campo solo se mostrara unicamente con la organizacion de video vigilancia Puebla -->
															<c:if test="${TicketConcilied.organizacion eq org}">
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text" name="numIncidente" id="numIncidente" value="${TicketConcilied.folioIncidencia}">
															</div>
															</c:if>
															
															<!-- Este campo solo se mostrara unicamente con la organizacion de video vigilancia Puebla -->
															<c:if test="${TicketConcilied.organizacion eq org}">
															<div class="col-lg-2 col-md-2">
																<center>
																	<label>Ticket Sistema Externo</label>
																</center>
															</div>
															</c:if>
															
															<!-- Este campo solo se mostrara unicamente con la organizacion de video vigilancia Puebla -->
															<c:if test="${TicketConcilied.organizacion eq org}">
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text" name="ticketExt" id="ticketExt" value="${TicketConcilied.ticketSExterno}">
															</div>
															</c:if>
															
															
															<div class="col-lg-12 col-md-12">
																<center>
																	<label>Información de Resumen</label>
																</center>
															</div>
															<div class="col-lg-12 col-md-12">
																<label>Resumen</label>
															</div>
															<div class="col-lg-12 col-md-12">
																<input class="form-control" type="text" name="resumen" id="resumen" value="${TicketConcilied.resumen}" required="">
															</div>

															<div class="col-lg-12 col-md-12">
																<label>Diagnóstico Final</label>
															</div>

															<div class="col-lg-12 col-md-12">
																<input class="form-control" type="text" name="diagnosticofinal" id="diagnosticofinal" value="${TicketConcilied.diagFinal}" required="">
															</div>
															<div class="col-lg-8 col-md-8">
																<label>Solución</label>
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Fecha/Hora de solución</label>
															</div>
															<div class="col-lg-8 col-md-8">
																<input class="form-control" type="text" name="solucion" id="solucion" value="${TicketConcilied.solucion}" required="">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="fechasolucion" id="fechasolucion" value="${TicketConcilied.folioCerrado}" required="">
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
																<input class="form-control" type="text" name="fechaapertura" id="fechaapertura" value="${TicketConcilied.folioAbierto}" required="">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="tiempoImputableTpe" id="tiempoImputableTpe"  value="${TicketConcilied.tiempoImputableTpe}">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="tiempoImputableCte" id="tiempoImputableCte" value="${TicketConcilied.tiempoImputableCte}">
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
																<input class="form-control" type="text" name="proactivoReactivo" id="proactivoReactivo" value="${TicketConcilied.zesProactivoReactivo}" required="">
															</div>
															<div class="col-lg-3 col-md-3">
																<input class="form-control" type="text" name="afectacion" id="afectacion" value="${TicketConcilied.afectacion}" required="">
															</div>
															<div class="col-lg-3 col-md-3">
																<label>Falla a Terceros</label>
															</div>
															<div class="col-lg-3 col-md-3">
																<label>Falla a Prov</label>
															</div>
															   
<!-- 															<div class="col-lg-3 col-md-3"> -->
<!-- 																<label>&nbsp;</label> -->
<!-- 															</div> -->
<!-- 															<div class="col-lg-3 col-md-3"> -->
<!-- 																<label>&nbsp;</label> -->
<!-- 															</div> -->
															<div class="col-lg-3 col-md-3">
																<input class="form-control" type="text" name="ztiempoFallaTer" id="ztiempoFallaTer" value="${TicketConcilied.ztiempoFallaTerceros}" required="" pattern="^([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$"  placeholder="hh:mn:ss">
															</div>
															<div class="col-lg-3 col-md-3">
																<input class="form-control" type="text" name="ztiempoFallaProv" id="ztiempoFallaProv" value="${TicketConcilied.ztiempoFallaProv}" required="" pattern="^([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$" placeholder="hh:mn:ss">
															</div>
														
										            </div>
													
										           </div>
										          <div class="modal-footer">
										            <input type="submit" class="btn btn-default" name="btnConciliado" value="Guardar Ajustes"/>
													<input type="submit" class="btn btn-default" name="btnConciliado" value="Validar"/>
													<!-- <input type="submit" class="btn btn-default" name="btnConciliado" value="Conciliar"/> -->
													<input type="button" class="btn btn-default" data-dismiss="modal" value="Cerrar Ventana"/>
										          </div>
										          </form:form>
										        </div>
										      </div>
										    
										    
										    </div>
	                                        
	                                        <c:choose>
    											<c:when test="${TicketConcilied.folioStatus eq 'PorValidar'}">
        											<td><font  color ="#9C9B9B"><c:out value="${TicketConcilied.folioAbierto}" /></font></td>
													<td><font  color ="#9C9B9B"><c:out value="${TicketConcilied.folioStatus}" /></font></td>
													<td><font  color ="#9C9B9B"><c:out value="${TicketConcilied.resumen}" /></font></td>
													<td><font  color ="#9C9B9B"><c:out value="${TicketConcilied.folioTitulo3}" /></font></td>
													
	                                        	</c:when>    
    											<c:otherwise>
       												<td><c:out value="${TicketConcilied.folioAbierto}" /></td>
													<td><c:out value="${TicketConcilied.folioStatus}" /></td>
													<td><c:out value="${TicketConcilied.resumen}" /></td>
													<td><c:out value="${TicketConcilied.folioTitulo3}" /></td>
	                                        
        									
    											</c:otherwise>
											</c:choose>
	                                        
	                                        
	                                    </tr>
                                    </c:forEach>                             
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
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


<c:set var = "valor" value="true"/>
        <c:set var = "valor2" value="false"/>
		<c:forEach items="${model.tickets}" var="TicketConcilied">
		     <c:if test="${TicketConcilied.succes eq valor}">
				
				<script>
					
					location.href = "/portaltpe/home/incidents/ajustesvalidacion";
				</script> 
				
							
			</c:if>

			<c:if test="${TicketConcilied.succes eq valor2}">
				
				<script>
					alert("Se Encontraron Errores, vuelva a intentarlo");
					location.href = "/portaltpe/home/incidents/ajustesvalidacion";
				</script> 
				
				
							
			</c:if>		
									
		</c:forEach>	
     






<script>
		    $(document).ready(function() {
		        $('#tablaAjustesConciliacion').DataTable({
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
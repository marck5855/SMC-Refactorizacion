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


<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">
<meta charset="UTF-8">


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
                   
                    <div style="text-align: right">
                    
	                   	  <form:form name="formularioExcel" action="/portaltpe/home/incidents/downloadExcel" >
	                    		<input type="button" class="botonFormulario fa fa-file-excel-o" name="btnExcel" value="Descargar Excel" onclick="descargarExcel()"/>
	                    </form:form>

					</div>
                        
                        	
                        
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
										      <div class="modal-dialog" style="width: 80%">
										        <div class="modal-content">
										          <div class="modal-header">
										            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										            <h4 class="modal-title h4">Detalles del Incidente</h4>
										          </div>
										          <div class="modal-body">
										            <div class="row">
										            	
										            	<form:form action="/portaltpe/home/incidents/updateajustesconciliacion" name="formularioajustesconciliacion">

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
																<input class="form-control" type="text" name="nombre1" id="nombre1" value="${TicketConcilied.nomSolicitante}" readonly="readonly">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text" name="nombre2" id="nombre2" value="${TicketConcilied.nom2Solicitante}" readonly="readonly">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="apellidos" id="apellidos" value="${TicketConcilied.apeSolicitante}" readonly="readonly">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="usuariofinal" id="usuariofinal" value="${TicketConcilied.folioTitulo}"   readonly="readonly">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="afectadocliente" id="afectadocliente" value="${TicketConcilied.folioTitulo3}" readonly="readonly">
															</div>
															<div class="col-lg-2 col-md-2">
																<input class="form-control" type="text"	name="categoria" id="categoria" value="${TicketConcilied.categoria}" readonly="readonly">
															</div>
															
															<input type="hidden" name="folioincidencia" id="folioincidencia" value="${TicketConcilied.folioIncidencia}">
															
															<div class="col-lg-10 col-md-10">
																<center>
																	<label>Descripción</label>
																</center>
															</div>
															
															<div class="col-lg-8 col-md-8">
																<textarea class="form-control" rows="10" name="descripcion" readonly="readonly">${TicketConcilied.descripcion}</textarea>
															</div>
															<div class="col-lg-12 col-md-12">
																<center>
																	<label>Información de Resumen</label>
																</center>
															</div>
															<div class="col-lg-12 col-md-12">
																<label>Resumen</label>
															</div>
															<div class="col-lg-12 col-md-12">
																<input class="form-control" type="text" name="resumen" id="resumen" value="${TicketConcilied.resumen}" readonly="readonly">
															</div>

															<div class="col-lg-12 col-md-12">
																<label>Diagnóstico Final</label>
															</div>

															<div class="col-lg-12 col-md-12">
																<input class="form-control" type="text" name="diagnosticofinal" id="diagnosticofinal" value="${TicketConcilied.diagFinal}" readonly="readonly">
															</div>
															<div class="col-lg-8 col-md-8">
																<label>Solución</label>
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Fecha/Hora de solución</label>
															</div>
															<div class="col-lg-8 col-md-8">
																<input class="form-control" type="text" name="solucion" id="solucion" value="${TicketConcilied.solucion}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="fechasolucion" id="fechasolucion" value="${TicketConcilied.folioCerrado}" readonly="readonly">
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
																<input class="form-control" type="text" name="fechaapertura" id="fechaapertura" value="${TicketConcilied.folioAbierto}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="tiempoImputableTpe" id="tiempoImputableTpe"  value="${TicketConcilied.tiempoImputableTpe}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="tiempoImputableCte" id="tiempoImputableCte" value="${TicketConcilied.tiempoImputableCte}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Proactivo/Reactivo</label>
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Afectación al Servicio</label>
															</div>

															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="fechaapertura" id="fechaapertura" value="${TicketConcilied.zesProactivoReactivo}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="tiempoImputableTpe" id="tiempoImputableTpe"  value="${TicketConcilied.afectacion}" readonly="readonly">
															</div>
															
															<div class="col-lg-4 col-md-4">
																<label>Falla a Terceros</label>
															</div>
															<div class="col-lg-4 col-md-4">
																<label>Falla a Prov</label>
															</div>

															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="fechaapertura" id="fechaapertura" value="${TicketConcilied.ztiempoFallaTerceros}" readonly="readonly">
															</div>
															<div class="col-lg-4 col-md-4">
																<input class="form-control" type="text" name="tiempoImputableTpe" id="tiempoImputableTpe"  value="${TicketConcilied.ztiempoFallaProv}" readonly="readonly">
															</div>
															
										            </div>
													
										           </div>
										          <div class="modal-footer">
										            <!--  
										            <input type="submit" class="btn btn-default" name="btnConciliado" value="Ajuste"/>
													<input type="submit" class="btn btn-default" name="btnConciliado" value="Validacion"/>
													-->
													<input type="submit" class="btn btn-default" name="btnConciliado" value="Regresar a Calidad"/>
													<input type="submit" class="btn btn-default" name="btnConciliado" value="Conciliar"/>
													<input type="button" class="btn btn-default" data-dismiss="modal" value="Cerrar Ventana"/>
										          </div>
										          </form:form>
										        </div>
										      </div>
										    </div>
	                                        
	                                        <td><c:out value="${TicketConcilied.folioAbierto}" /></td>
											<td><c:out value="${TicketConcilied.folioStatus}" /></td>
											<td><c:out value="${TicketConcilied.resumen}" /></td>
											<td><c:out value="${TicketConcilied.folioTitulo3}" /></td>
	                                        
	                                        
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


					<div class="cp_oculta" id="escondetabla">
                        
                        	
                        
                            <table width="100%" class="table table-striped table-bordered table-hover" id="tablaAjustesConciliacionEscondida">
                                <thead>
                                    <tr>
										<th>NOMBRE SOLICITANTE</th>
										<th>NUMERO DE INCIDENTE</th>
										<th>RESUMEN</th>
										<th>FECHA APERTURA</th>
										<th>FECHA SOLUCION</th>
										<th>AFECTADO/CLIENTE</th>
										<th>USUARIO FINAL</th>
										<th>DESCRIPCION</th>
										<th>SOLUCION</th>
										<th>CATEGORIA</th>
										<th>DIAGNOSTICO FINAL</th>
										
									</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${model.tickets}" var="TicketConcilied">
	                                    <tr>
	                                        <td><c:out value="${TicketConcilied.nomSolicitante}"/> <c:out value="${TicketConcilied.nom2Solicitante}"/> <c:out value="${TicketConcilied.apeSolicitante}"/></td>	                                        
	                                        <td><c:out value="${TicketConcilied.folioIncidencia}" /></td>
	                                        <td><c:out value="${TicketConcilied.resumen}" /></td>
											<td><c:out value="${TicketConcilied.folioAbierto}" /></td>
											<td><c:out value="${TicketConcilied.folioCerrado}" /></td>
											<td><c:out value="${TicketConcilied.folioTitulo3}" /></td>
											<td><c:out value="${TicketConcilied.folioTitulo3}" /></td>
	                                        <td><c:out value="${TicketConcilied.descripcion}" /></td>
	                                        <td><c:out value="${TicketConcilied.solucion}" /></td>
	                                        <td><c:out value="${TicketConcilied.categoria}" /></td>
	                                        <td><c:out value="${TicketConcilied.diagFinal}" /></td>
	                                        
	                                    </tr>
                                    </c:forEach>                             
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            
                           
                            
                            
                        </div>


<c:set var = "valor" value="true"/>
        <c:set var = "valor2" value="false"/>
		<c:forEach items="${model.tickets}" var="TicketConcilied">
		     <c:if test="${TicketConcilied.succes eq valor}">
				
				<script>
					
					location.href = "/portaltpe/home/incidents/ajustesconciliacion";
				</script> 
				
							
			</c:if>

			<c:if test="${TicketConcilied.succes eq valor2}">
				
				<script>
					alert("Se Encontraron Errores, vuelva a intentarlo");
					location.href = "/portaltpe/home/incidents/ajustesconciliacion";
				</script> 
				
				
							
			</c:if>		
									
		</c:forEach>	
     


<script>
    function descargarExcel(){
        //Creamos un Elemento Temporal en forma de enlace
        var tmpElemento = document.createElement('a');
        // obtenemos la información desde el div que lo contiene en el html
        // Obtenemos la información de la tabla
        var data_type = 'data:application/vnd.ms-excel';
        var tabla_div = document.getElementById('tablaAjustesConciliacionEscondida');
        var tabla_html = tabla_div.outerHTML.replace(/ /g, '%20');
        tmpElemento.href = data_type + ', ' + tabla_html;
        //Asignamos el nombre a nuestro EXCEL
        tmpElemento.download = 'Reporte.xls';
        // Simulamos el click al elemento creado para descargarlo
        tmpElemento.click();
    }
    //descargarExcel();
</script>

<script>
document
.write('<style type="text/css">div.cp_oculta{display: none;}</style>');
</script>

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
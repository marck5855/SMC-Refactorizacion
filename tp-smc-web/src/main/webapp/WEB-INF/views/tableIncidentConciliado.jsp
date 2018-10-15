<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

	<link href="/portaltpe/static/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="<c:url value='/static/vendor/jquery/js/jquery.js' />" ></script>
	<script src="<c:url value='/static/vendor/datatables/js/jquery.dataTables.min.js' />" ></script>
	<script src="<c:url value='/static/vendor/datatables-plugins/dataTables.bootstrap.min.js' />" ></script>
	<script src="<c:url value='/static/vendor/datatables-responsive/dataTables.responsive.js' />" ></script>	
	
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
	
	    <div class="container-fluid">
	    	<div class="row">

            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Incidentes Conciliados
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>Incidente Núm.</th>
                                        <th>Prioridad</th>
                                        <th>Categoría</th>
                                        <th>Estado</th>
                                        <th>Asignatario</th>
                                        <th>Grupo</th>
                                        <th>Cliente</th>
                                        <th>Ubicación Solicitante</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${model.tickets}" var="ticket">
	                                    <tr>
	                                        <td>
	                                        	<a href="#" data-toggle="modal" data-target="#${ticket.refNum}">
	                                        		<c:out value="${ticket.refNum}" />
	                                        	</a>
	                                        </td>
	                                        	                                        
										    <div class="modal fade" id="${ticket.refNum}" tabindex="-1" role="dialog" aria-labelledby="basicModal" data-backdrop="false">
										      <div class="modal-dialog">
										        <div class="modal-content">
										          <div class="modal-header">
										            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										            <h4 class="modal-title h4">Detalles del Incidente</h4>
										          </div>
										          <div class="modal-body">
										            <div class="row">
										            	<div class="col-lg-12 col-md-12" id="description">
										            		<h5><strong>Detalle del Incidente</strong></h5>
										            	</div>
										            	<div class="col-lg-4 col-md-4">
											            	<h5 id="description">Solicitante</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-4 col-md-4">
											            	<h5 id="description">Cliente</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-4 col-md-4">
											            	<h5 id="description">Categoría</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-12 col-md-12">
										            		<h5 id="description">Descripción</h5>
										            		<p id="information"></p>
										            	</div>
										            </div>
													<div class="row">
								            			<div class="col-lg-12 col-md-12" id="description">
										            		<h5><strong>Información del Incidente</strong></h5>
										            	</div>
										            	<div class="col-lg-4 col-md-4">
											            	<h5 id="description">Resumen</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-4 col-md-4">
											            	<h5 id="description">Tiempo Total</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-4 col-md-4">
											            	<h5 id="description">Creado Vía</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-12 col-md-12">
										            		<h5 id="description">Diagnóstico Final</h5>
										            		<p id="information"></p>
										            	</div>
											            <div class="col-lg-6 col-md-6">
											            	<h5 id="description">Solución</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-6 col-md-6">
											            	<h5 id="description">Fecha / Hora Solución</h5>
											            	<p id="information"></p>
											            </div>										            	
										            	<div class="col-lg-3 col-md-3">
											            	<h5 id="description">Fecha de Apertura</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-3 col-md-3">
											            	<h5 id="description">Última modificación</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-3 col-md-3">
											            	<h5 id="description">Notificación de solución</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-3 col-md-3">
											            	<h5 id="description">Fecha / Hora Cierre</h5>
											            	<p id="information"></p>
											            </div>
										            </div>
										            <div class="col-lg-3 col-md-3">
											            	<h5 id="description">Proactivo/Reactivo</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-3 col-md-3">
											            	<h5 id="description">Afectacion</h5>
											            	<p id="information"></p>
											            </div>
											            <div class="col-lg-3 col-md-3">
											            	<h5 id="description">Falla a Terceros</h5>
											            	<p id="information"></p>
											            </div>
											             <div class="col-lg-3 col-md-3">
											            	<h5 id="description">Falla a Prov</h5>
											            	<p id="information"></p>
											            </div>
										           </div>
										          <div class="modal-footer">
										            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
										          </div>
										        </div>
										      </div>
										    </div>
	                                                                                
	                                        <td><c:out value="${ticket.priority}" /></td>
	                                        <td><c:out value="${ticket.category}" /></td>
	                                        <td><c:out value="${ticket.status}" /></td>
	                                        <td><c:out value="${ticket.assignee}" /></td>
	                                        <td><c:out value="${ticket.group}" /></td>
	                                        <td><c:out value="${ticket.tfe}" /></td>
	                                        <td><c:out value="${ticket.contactLocation}" /></td>
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
		
	    <script>
		    $(document).ready(function() {
		        $('#dataTables-example').DataTable({
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
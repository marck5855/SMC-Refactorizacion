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
                            <table width="100%" class="table table-striped table-bordered table-hover" id="tablaDescargarArchivo">
                                <thead>
                                    <tr>
										<th>Nombre Archivo</th>
										<th>Opción</th>
										
									</tr>
                                </thead>
                                <tbody>
                                	<!--<c:forEach items="${model.tickets}" var="TicketConcilied">-->
	                                    <tr>
	                                       <!--  
	                                        <td>
	                                        	<a href="#" data-toggle="modal" data-target="#${TicketConcilied.folioIncidencia}">
	                                        		<c:out value="${TicketConcilied.folioIncidencia}" />
	                                        	</a>
	                                        </td>
	                                        -->	                                        

	                                        
	                                        <td>archivo1.pdf</td>
											<td><input type="button" name="btnDescargar" id="btnDescargar" value="Descargar"></td>
											
	                                        
	                                    </tr>
                                    <!--</c:forEach> -->                            
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





<script>
		    $(document).ready(function() {
		        $('#tablaDescargarArchivo').DataTable({
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
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

<style>
<!--

-->
	.fabutton{
		background: none;
		padding: 0px;
		border:none;
	}
	
	.fa.fa-cloud-download {
    	font-size: 2em;
		}
</style>

<div class="container-fluid">
	<div class="row">
		<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                          Documentos del Proyecto  
                        </div>
                        <!-- /.panel-heading -->
                        <form:form action="/portaltpe/home/informs/muestraReporte" method="post">
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover display" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>Carpeta</th>
										<th>Nombre Documento</th>
										<th>Fecha de Carga</th>
										<th>Usuario</th>
										<th>Acción</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${archivo.archivosPdf}" var="file">
										<tr>
											<td><img src="/portaltpe/static/images/toggle-collapse-dark.png">
												${file.path}</td>
											<td>${file.archivonombre}</td>
											<td>${file.archivofechacarga}</td>
											<td>${file.username}</td>
											<td><a
												href="/portaltpe/static/tmp/pdf/${file.idArchivo}_${file.archivonombre}"
												download="${file.archivonombre}">Descargar
												</a></td>
										</tr>
									</c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                        </div>
                        </form:form>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

	<script>
		$(document).ready(function() {
			$('#dataTables-example').DataTable( {
				responsive : false,
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
						},
						columnDefs:[{visible:false, targets:0}],
						order: [[ 2, 'asc' ]],
						displayLength: 25,
						drawCallback: function ( settings ) {
							var api = this.api();
							var rows = api.rows( {page:'current'} ).nodes();
							var last=null;
								api.column(0, {page:'current'} ).data().each( function ( group, i ) {
									if ( last !== group ) {
										$(rows).eq( i ).before(
											'<tr class="group" style="background-color:#E6E6E6">'
										   +'<td colspan="5">'+group+'</td></tr>'
										);
										last = group;
									}
								} );
						}
    	} );
 
	    $('#dataTables-example tbody').on( 'click', 'tr.group', function () {
	        var currentOrder = table.order()[0];
	        if ( currentOrder[0] === 2 && currentOrder[1] === 'asc' ) {
	            table.order( [ 2, 'desc' ] ).draw();
	        } else {
	            table.order( [ 2, 'asc' ] ).draw();
	        }
	    } );
											
	});
	</script>

	</div>
</div>
					
<jsp:include page="footer.jsp"></jsp:include>
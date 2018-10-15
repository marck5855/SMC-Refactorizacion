<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
						<b>Links</b>
					</div>

					<div class="panel-body">

						<table width="100%"
							class="table table-striped table-bordered table-hover"
							id="tablaLinks">
							<thead>
								<tr>
									<th>Nombre de Link</th>
									<th>Descripción de Link</th>
									<th>Enlace</th>
								</tr>
							</thead>
							<c:forEach items="${model.links}" var="listlinks">
								<tbody>
									<tr>
										<td><c:out value="${listlinks.nombreLink}" /></td>
										<td><c:out value="${listlinks.descripcionLink}" /></td>
										<td><a href="${listlinks.url}"  target="_blank">Ir a Enlace</a></td>

									</tr>
							</c:forEach>
							</tbody>
						</table>

					</div>

				</div>

			</div>

		</div>


	</div>














	<center>
		<div id="pageNavPosition"></div>
	</center>



	<script>
		$(document)
				.ready(
						function() {
							$('#tablaLinks')
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






	<br /> <br /> <br />
	<jsp:include page="footer.jsp"></jsp:include>
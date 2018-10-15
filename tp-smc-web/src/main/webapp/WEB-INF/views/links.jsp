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

<div class="modal fade" id="eliminarModalLink" role="dialog">
	<div class="modal-dialog modal-lg modal-center">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Eliminar Link</h3>
			</div>
			<form:form name="formularioeliminarrol">
				<input id="idLinkParam" class="form-control" type="hidden" name="idLinkP"
					maxlength="200">
				<input id="nameLinkParam" class="form-control" type="hidden" name="nameLinkP"
					maxlength="200">
				<div class="modal-footer">
					<div class="modal-footer">
						¿Esta seguro que desea eliminar el Link? 
						<input type="button"
							onclick="deleteLink()"
							class="btn btn-danger" style="width: 80px;"
							height: 40px" name="SI" Value="Si"> 
						<input type="button"
							data-dismiss="modal" class="btn btn-danger" style="width: 80px;"
							height: 40px" name="NO" Value="No">
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
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
						<div style="text-align: right">
							<button type="button" class="botonFormulario btn btn-primary"
								data-toggle="modal" data-target="#mostrarmodal">
								<i class="fa fa-user-plus fa-fw"></i> Registrar Link
							</button>
						</div>
						<table width="100%"
							class="table table-striped table-bordered table-hover"
							id="tablaLinks">
							<thead>
								<tr>
									<th>Id</th>
									<th>Enlace</th>
									<th>Nombre de Link</th>
									<th>Descripción de Link</th>
									<th>Acciones</th>
								</tr>
							</thead>
							<c:forEach items="${model.links}" var="listlinks">
								<tbody>
									<tr>
										<td><c:out value="${listlinks.idLink}" /></td>
										<td><c:out value="${listlinks.url}" /></td>
										<td><c:out value="${listlinks.nombreLink}" /></td>
										<td><c:out value="${listlinks.descripcionLink}" /></td>
										<th style="text-align: center;">																								
											<button type="button" name="eliminar" title="Eliminar Link ${listlinks.nombreLink}"                                        
												style="background: white; border: none"                                                                                
												onclick="EliminarLinkFormulario('<c:out value="${listlinks.idLink}" />','<c:out value="${listlinks.nombreLink}" />');">
												<img src="/portaltpe/static/images/eliminar.png" width="20" >                                                          
											</button>                                                                                                                  
										</th>                                                                                                                         
									</tr>
							</c:forEach>
							</tbody>
						</table>

					</div>

				</div>

			</div>

		</div>


	</div>



	<!-- sección Formulario Agregar Rol -->
	<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog"
		aria-labelledby="basicModal" aria-hidden="true" data-backdrop="false">
		<div class="modal-dialog" id="modalTamanio">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 class="modal-title">Registro de Link</h3>
				</div>
				<div class="modal-body">
					<form:form name="formularioaltalink"
						action="/portaltpe/home/other/linkadd"
						onsubmit="validar = (ValidarLinkDescripcion(this) && confirmacionRegistroLink(this)); return validar;">
						<div class="form-group">
							<!-- 							<input type="file" name="fimagen" accept="image/jpeg, image/png"/> -->
							<label for="recipient-name" class="form-control-label">Capturar
								Nombre de Link:</label> <input type="text" class="form-control"
								id="nombreLink" name="nombreLink">
						</div>
						<br>
						<div class="form-group">
							<label for="message-text" class="form-control-label">Capturar
								Link:</label>
							<textarea class="form-control" id="link" name="link"></textarea>
						</div>
						
						<div class="form-group">
							<label for="message-text" class="form-control-label">Capturar Descripcion de
								Link:</label>
							<textarea class="form-control" id="descripcionLink" name="descripcionLink"></textarea>
						</div>

						<div class="modal-footer">
							<button type="button" class="botonFormulario btn btn-primary"
								data-dismiss="modal">Cerrar</button>

							<button type="submit" class="botonFormulario btn btn-primary"
								name="registar">
								<i class="fa fa-floppy-o fa-fw"></i> Guardar
							</button>

						</div>

					</form:form>
				</div>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->






	<c:set var="valor" value="true" />
	<c:set var="valor2" value="false" />
	<c:forEach items="${model1.links}" var="link">
		<c:if test="${link.succes eq valor}">

			<script>
				location.href = "/portaltpe/home/other/links";
			</script>


		</c:if>

		<c:if test="${link.succes eq valor2}">

			<script>
				alert("Se Encontraron Errores, vuelva a intentarlo");
				location.href = "/portaltpe/home/other/links";
			</script>



		</c:if>

	</c:forEach>



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
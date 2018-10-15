<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<script type="text/javascript">
	function enviarMensaje(){
		alert("Ah llegado al limite de Registros de Usuario, para poder registrar nuevamente un Usuario, Favor de contactar con Administrador.")
	}
</script>

<div class="container-fluid">
	<div class="row">

		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<b>Usuarios</b>
					</div>

					<div class="panel-body">
					
					 <c:set var="N" value="N"/>
					 <c:set var="R" value="R" />
					 <c:set var="C" value="C" />
					 
						<div style="text-align: right">
						
						<c:forEach items="${noUser.tickets}" var="user">
							<c:if test="${user.usersnum eq N}">
								<button type="button" class="botonFormulario btn btn-primary" onclick="enviarMensaje()">
									<i class="fa fa-user-plus fa-fw"></i> Registrar Usuarios
								</button>
							</c:if>
							<c:if test="${user.usersnum eq R}">
								<button type="button" class="botonFormulario btn btn-primary"
									data-toggle="modal" data-target="#mostrarmodal">
									<i class="fa fa-user-plus fa-fw"></i> Registrar Usuarios
								</button>
							</c:if>
							<c:if test="${user.usersnum eq C}">
								<button type="button" class="botonFormulario btn btn-primary"
									data-toggle="modal" data-target="#mostrarmodal">
									<i class="fa fa-user-plus fa-fw"></i> Registrar Usuarios
								</button>
							</c:if>
						</c:forEach>
						</div>

						
						<table width="100%"
							class="table table-striped table-bordered table-hover"
							id="tablaUsuarios">
							<thead>
								<tr>
									<th>Organización</th>
									<th>Nombre</th>
									<th>Login</th>
									<th style="width: 150px;">Acciones</th>
								</tr>
							</thead>
							
								<tbody>
								<c:forEach items="${model.tickets}" var="listpoint">
									<tr>
										<td><c:out value="${listpoint.organization}" /></td>
										<td><c:out value="${listpoint.name}" /></td>
										<td><c:out value="${listpoint.username}" /></td>

										<th>
											<button type="button" name="registro"
												title="Ver Detalles del Usuario"
												style="background: white; border: none"
												onclick="MostrarFormulario('<c:out value="${listpoint.username}" />','<c:out value="${listpoint.name}" />','<c:out value="${listpoint.organization}" />','<c:out value="${listpoint.role}" />','p');">
												<img src="/portaltpe/static/images/verdetalle.png"
													width="20">
											</button>
											<button type="button" name="actualiza"
												title="Actualizar los Datos del Usuario"
												style="background: white; border: none"
												onclick="ActualizaraFormulario('<c:out value="${listpoint.username}" />','<c:out value="${listpoint.name}" />','<c:out value="${listpoint.organization}" />','p');">
												<img src="/portaltpe/static/images/editar.png" width="20">
											</button>
											<button type="button" name="eliminar"
												title="Eliminar Usuario"
												style="background: white; border: none"
												onclick="EliminarFormulario('<c:out value="${listpoint.username}" />','<c:out value="${listpoint.name}" />','<c:out value="${listpoint.organization}" />','p');">
												<img src="/portaltpe/static/images/eliminar.png" width="20">
											</button>



											<button type="button" name="permisos"
												title="Cambiar los Permisos del Usuario"
												style="background: white; border: none"
												onclick="CambiarRolFormulario('<c:out value="${listpoint.username}" />');">
												<img src="/portaltpe/static/images/permiso.png" width="20">
											</button>


										</th>
									</tr>
								
<%-- 								<c:if test="${listpoint.mssg != null}"> --%>
<!-- 								<script> -->
<!-- // 									alert("EL USUARIO YA SE ENCUENTRA REGISTRDO EN LA BASE DE DATOS DEL SMC, INTENTAR CON OTRO NOMBRE"); -->
<!-- 								</script>	 -->
									
<%-- 								</c:if> --%>
							</c:forEach>
							</tbody>
						</table>

					</div>

				</div>

			</div>

		</div>


	</div>
	<c:set var="valor" value="true" />
	<c:set var="valor2" value="false" />
	<c:set var="valor3" value="success" />
	<c:forEach items="${model.tickets}" var="listpoint">
		<c:if test="${listpoint.succes eq valor}">

			<script>
				location.href = "/portaltpe/home/manageUsers/users";
			</script>
			
		</c:if>

		<c:if test="${listpoint.succes eq valor2}">

			<script>
				alert("Se Encontraron Errores, vuelva a intentarlo");
				location.href = "/portaltpe/home/manageUsers/users";
			</script>
		</c:if>

		<c:if test="${listpoint.succes eq valor3}">
			<script>
				alert("No Se puede Crear el Usuario , El Usuario a registrar ya Existe");
				location.href = "/portaltpe/home/manageUsers/users";
			</script>
		</c:if>

	</c:forEach>
	





	<!-- sección Formulario ALta Usuario -->

	<div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog"
		aria-labelledby="basicModal" aria-hidden="true" data-backdrop="false" style=" position: absolute; z-index: 5;">
		<div class="modal-dialog modal-lg modal-center">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3>Registrar Usuario</h3>
				</div>

				<form:form name="formularioalta"
					action="/portaltpe/home/manageUsers/usersadd"
					onsubmit="validar = (ValidarOrganizacion(this) && ValidarRol() && Confirmacion()); return validar;">

					<div class="table-responsive">

						<table class="table">

							<thead>

								<tr>
									<th>Usuario *</th>
									<th>Nombre *</th>
									<!-- <th>Organizacón *</th>  -->
									<th>Contraseña *</th>
								</tr>

							</thead>

							<tbody>
								<tr>
									<th><input class="form-control" type="text"
										name="username" id="username" maxlength="200" required=""
										readonly onfocus="this.removeAttribute('readonly')"></th>
									<th><input class="form-control" type="text" name="name"
										id="name" maxlength="200" required="" pattern="[A-Za-z ´]+"
										readonly onfocus="this.removeAttribute('readonly')"></th>
									<!--  <th><input  class="form-control" type="text" name="organization" maxlength="200" required=""></th>-->
									<th><input class="form-control" type="password"
										name="password" maxlength="200" required="" readonly onfocus="this.removeAttribute('readonly')"></th>
									<!-- <th><input  class="form-control" type="text" name="role" maxlength="200" required="" value="ROLE_TPE"></th> -->

								</tr>

							</tbody>

						</table>
						<label>Seleccione Organización:</label>
						<div style="overflow: scroll; width: 100%; height: 250px">
							<table width="100%"
								class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>Opción</th>
										<!--                                         <th>Organización Núm.</th> -->
										<th>Nombre</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${model5.tickets}" var="organization">
										<tr>
											<td><input type="radio" name="organization"
												id="${organization.organizationId}"
												value="${organization.organizationId}"
												onclick="marcado=true"></td>
											<%--                                   		<td><c:out value="${organization.organizationId}" /></td> --%>
											<td><c:out value="${organization.organizationName}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						</br> 
						
						<label>Seleccione Rol:</label> <select class="form-control"
							id="role" name="role">
							<option value="0" selected="selected">- SELECCIONA -</option>

							<c:forEach items="${model2.roles}" var="roles">
								<option value="${roles.idRole}"><c:out value="${roles.rolRole}" /></option>

							</c:forEach>
						</select>
						
						<label>No. de Usuarios:</label>
						<select class="form-control" id="noUsr" name="noUsr">
							<option value="0" selected="selected">- SELECCIONA -</option>
							<option value="1">1 Usuarios </option>
							<option value="2">2 Usuarios </option>
							<option value="3">3 Usuarios </option>
						</select>

					</div>

					<div class="modal-footer">
						<!-- <a href="#" data-dismiss="modal" class="btn btn-danger" style="width:120px; height:40px">Cerrarsss</a>-->
						<div class="modal-footer">
							<input type="submit" class="btn btn-danger"
								style="width: 120px; height: 40px" name="registar"
								Value="Registrar">
							<!-- <input type="button"  class="btn btn-danger" style="width:120px; height:40px" name="limpiar" Value="Limpiar" onclick="LimpiarCajas();">-->
							<input type="reset" class="btn btn-danger"
								style="width: 120px; height: 40px" name="limpiar"
								Value="Limpiar" onclick="LimpiarCajas();"> <input
								type="button" data-dismiss="modal" class="btn btn-danger"
								style="width: 120px; height: 40px" name="cerrar" Value="Cerrar">
						</div>

					</div>

				</form:form>
			</div>

		</div>
	</div>


	<!--  seccion mostrar datos usuario -->
	<div class="modal fade" id="VerDatosModal" tabindex="-1" role="dialog"
		aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-center">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3>Detalles del Usuario</h3>
				</div>

				<form name="formulariomostrardatos" action="" method="post">

					<div class="table-responsive">

						<table class="table">

							<thead>

								<tr>
									<th>Usuario *</th>
									<th>Nombre *</th>
									<th>Organización *</th>
 									<th>Role *</th> 
<!-- 									<th>Contraseña *</th> -->
								</tr>

							</thead>

							<tbody>
								<tr>
									<th><input class="form-control" type="text" name="username" maxlength="100" required="" readonly="readonly"></th>
									<th><input class="form-control" type="text" name="name" maxlength="100" required="" readonly="readonly"></th>
									<th><input class="form-control" type="text" name="organization" maxlength="100" required="" readonly="readonly"></th>
									<th><input class="form-control" type="text" name="role" maxlength="100" required="" readonly="readonly" ></th> 
<!-- 									<th><input class="form-control" type="password" name="password" placeholder="Min 6 characters" pattern=".{6,}" maxlength="200" required="" readonly="readonly"></th> -->
								</tr>
							</tbody>

						</table>



					</div>

					<div class="modal-footer">
						<!-- <a href="#" data-dismiss="modal" class="btn btn-danger" style="width:120px; height:40px">Cerrarsss</a>-->
						<div class="modal-footer">
							<!-- <input type="submit"  class="btn btn-danger" style="width:120px; height:40px" name="registar" Value="Registrar"> -->
							<!-- <input type="button"  class="btn btn-danger" style="width:120px; height:40px" name="limpiar" Value="Limpiar" onclick="LimpiarCajas();"> -->
							<input type="button" data-dismiss="modal" class="btn btn-danger"
								style="width: 120px; height: 40px" name="cerrar" Value="Cerrar">
						</div>

					</div>

				</form>
			</div>

		</div>
	</div>


	<!-- sección Formulario Actualizar Datos Usuario -->

	<div class="modal fade" id="actualizarmodal" tabindex="-1"
		role="dialog" aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-center">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3>Editar Usuario</h3>
				</div>

				<form:form name="formularioactualizar"
					action="/portaltpe/home/manageUsers/usersupdate"
					onsubmit="validar = (ValidarOrganizacion(this) && Confirmacion()); return validar;">

					<div class="table-responsive">

						<table class="table">

							<thead>

								<tr>
									<th>Usuario *</th>
									<th>Nombre *</th>
									<!-- <th>Organizacón *</th>  -->
									<th>Contraseña *</th>
								</tr>

							</thead>

							<tbody>
								<tr>
									<th><input class="form-control" type="text"
										name="username" maxlength="200" readonly="readonly"></th>
									<th><input class="form-control" type="text" name="name"
										maxlength="200" required="" pattern="[A-Za-z ´]+"></th>
									<!-- <th><input  class="form-control" type="text" name="organization" maxlength="200" required=""></th> -->
									<th><input class="form-control" type="password"
										name="password" placeholder="Min 6 characters" pattern=".{6,}"
										maxlength="200" required=""></th>

								</tr>
							</tbody>

						</table>
						<label>Organización</label>
						<div style="overflow: scroll; width: 100%; height: 250px">
							<table width="100%"
								class="table table-striped table-bordered table-hover"
								id="OrganizationUpdate">
								<thead>
									<tr>
										<th>Opción</th>
<!-- 										<th>Organización Núm.</th> -->
										<th>Nombre</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${model5.tickets}" var="organization">
										<tr>
											<td><input type="radio" name="organization"
												id="${organization.organizationId}"
												value="${organization.organizationId}"
												onclick="marcado=true"></td>
<%-- 											<td><c:out value="${organization.organizationId}" /></td> --%>
											<td><c:out value="${organization.organizationName}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>


					</div>

					<div class="modal-footer">
						<!-- <a href="#" data-dismiss="modal" class="btn btn-danger" style="width:120px; height:40px">Cerrarsss</a>-->
						<div class="modal-footer">
							<input type="submit" class="btn btn-danger"
								style="width: 120px; height: 40px" name="registar"
								Value="Aceptar"> <input type="button"
								class="btn btn-danger" style="width: 120px; height: 40px"
								name="limpiar" Value="Limpiar"
								onclick="LimpiarActualizaraFormulario()"> <input
								type="button" data-dismiss="modal" class="btn btn-danger"
								style="width: 120px; height: 40px" name="cerrar" Value="Cerrar">
						</div>

					</div>

				</form:form>
			</div>

		</div>
	</div>


	<!-- sección Formulario Eliminar Datos Usuario -->

	<div class="modal fade" id="eliminarmodal" tabindex="-1" role="dialog"
		aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-center">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3>Eliminar Usuario</h3>
				</div>

				<form:form name="formularioeliminar"
					action="/portaltpe/home/manageUsers/usersdelete">

					<div class="table-responsive">

						<table class="table">

							<thead>

								<tr>
									<th>Usuario *</th>
									<th>Nombre *</th>
									<th>Organizacón *</th>
									<th>Contraseña *</th>
								</tr>

							</thead>

							<tbody>
								<tr>
									<th><input class="form-control" type="text"
										name="username" maxlength="200" required=""></th>
									<th><input class="form-control" type="text" name="name"
										maxlength="200" required=""></th>
									<th><input class="form-control" type="text"
										name="organization" maxlength="200" required=""></th>
									<th><input class="form-control" type="password"
										name="password" placeholder="Min 6 characters" pattern=".{6,}"
										maxlength="200" required=""></th>

								</tr>
							</tbody>

						</table>



					</div>

					<div class="modal-footer">
						<!-- <a href="#" data-dismiss="modal" class="btn btn-danger" style="width:120px; height:40px">Cerrarsss</a>-->
						<div class="modal-footer">
							¿Deseas continuar? <input type="submit" class="btn btn-danger"
								style="width: 120px; height: 40px" name="SI" Value="Si">
							<input type="button" data-dismiss="modal" class="btn btn-danger"
								style="width: 120px; height: 40px" name="NO" Value="No">
							<!-- <input type="button"  class="btn btn-danger" style="width:120px; height:40px" name="cerrar" Value="Cerrar"> -->
						</div>

					</div>

				</form:form>
			</div>

		</div>
	</div>


	<!-- sección Formulario Cambiar Rol del Usuario -->
	<div class="modal fade" id="cambiarrolmodal" tabindex="-1"
		role="dialog" aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-center">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3>Modificar Permisos</h3>
				</div>

				<form:form name="formulariocambiarrol"
					action="/portaltpe/home/manageUsers/roleusersupdate"
					onsubmit="return ValidarRolModificar();">

					<div class="table-responsive">

						<table class="table">

							<thead>

								<tr>

									<input type="hidden" name="username" id="username">
								</tr>

							</thead>

							<tbody>
								<tr>
									<th><select class="form-control" id="role" name="role">
											<option value="0" selected="selected">- SELECCIONA -</option>

											<c:forEach items="${model2.roles}" var="roles">
												<option value="${roles.idRole}"><c:out value="${roles.rolRole}" /></option>

											</c:forEach>
									</select></th>

								</tr>

							</tbody>

						</table>



					</div>

					<div class="modal-footer">
						<div class="modal-footer">
							<input type="submit" class="btn btn-danger"
								style="width: 120px; height: 40px" name="aceptar"
								Value="Aceptar"> <input type="button"
								data-dismiss="modal" class="btn btn-danger"
								style="width: 120px; height: 40px" name="cerrar" Value="Cerrar">
						</div>

					</div>

				</form:form>
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
	
	<script>
		$(document)
				.ready(
						function() {
							$('#tablaUsuarios')
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

	<script>
		$(document)
				.ready(
						function() {
							$('#OrganizationUpdate')
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

	<br/> 
	<jsp:include page="footer.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" 	uri="http://www.springframework.org/security/tags"%>

<jsp:include page="header.jsp"></jsp:include>

<link href="/portaltpe/static/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

<script src="<c:url value='/static/vendor/jquery/js/jquery.js' />"></script>
<script src="<c:url value='/static/vendor/datatables/js/jquery.dataTables.min.js' />"></script>
<script src="<c:url value='/static/vendor/datatables-plugins/dataTables.bootstrap.min.js' />"></script>
<script src="<c:url value='/static/vendor/datatables-responsive/dataTables.responsive.js' />"></script>

<div class="container-fluid">
  <div class="row">
	<div class="row">
	  <div class="col-lg-12">
		<div class="panel panel-default">
		  <div class="panel-heading">
			 <b>Roles</b>
		  </div>

		  <div class="panel-body">
			<div style="text-align: right">
				<button type="button" class="botonFormulario btn btn-primary" data-toggle="modal" data-target="#mostrarmodal">
					<i class="fa fa-user-plus fa-fw"></i> Registrar Rol
				</button>
			
				<button type="button" class="botonFormulario btn btn-primary" data-toggle="modal" data-target="#mostrarmodalsla">
					 Modificar SLA´S
				</button>
			</div>

			<table width="100%"class="table table-striped table-bordered table-hover" id="tablaRoles">
			  <thead>
				<tr>
					<th style="width: 50px;">Id</th>
					<th>Rol</th>
					<th>Descripción de Rol</th>
					<th>Organización</th>
					<th style="width: 150px;">Acciones</th>
				</tr>
			   </thead>
							
			   <tbody>
				
				<c:forEach items="${model.roles}" var="listRol">
				<tr>
				 <td>
				 	<c:out value="${listRol.idRole}" /> <!-- sección Formulario Detalle de Roles -->
					<div class="modal fade" id="mprueba${listRol.idRole}" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="false">
					  <div class="modal-dialog" id="modalTamanio">
						<div class="modal-content">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								<h3 class="modal-title">Detalle de Rol</h3>
						  </div>
						  
						  <div class="modal-body">
								<p>Detalle</p>

								<c:if test="${listRol.tieneMenus == false}">
									<font color="#c63939">
										<c:out value="ROL SIN MENUS, FAVOR DE ASIGNAR MENUS." /></font>
								</c:if>

								<c:if test="${listRol.idMenu == 0}">
 									<font color="#c63939">
 									    <c:out value="ROL SOLO CON " /></font>
								</c:if>

								<input type="hidden" name="idRole" id="idRole" value="${listRol.idRole}">
																
						   <c:forEach items="${listRol.menusDetalle}" var="menu">
								<c:if test="${menu.idMenu != 0 && menu.idSubMenu == null || menu.idSubMenu == 0}">
                                
                                <input type="checkbox" name="idMenuDetalle" id="${menu.idMenu}" value="${menu.idMenu}"
									checked="checked" disabled="disabled" onclick="llenadoIdsMenus('<c:out value="${menu.idMenu}"/>')">
																		
								<font color="#c63939"><c:out value="${menu.nombreMenu}" /></font>

								<ul class="nav nav-second-level">
									<c:forEach items="${listRol.subMenusDetalle}" var="submenu">

									<li>
									  <c:if test="${menu.idMenu == submenu.idSubMenu}">
                                          <input type="checkbox" name="idMenuSubDetalle" id="${submenu.idMenu}" value="${submenu.idMenu}" style="margin-left: 7%;" checked="checked" disabled="disabled"
												onclick="llenadoIdsSubMenus('<c:out value="${submenu.idMenu}"/>')">
												<c:out value="${submenu.nombreMenu}" />
                                      </c:if>
                                    </li>

                                    </c:forEach>

								</ul>
																		
								</c:if>

							</c:forEach>
																
							<br>
							
							<font color="#c63939"><c:out value="PAGINA INICIAL: " /><c:out value="${listRol.origenHome}" /></font>
							
							<div class="modal-footer">
								<button type="button" class="botonFormulario btn btn-primary" data-dismiss="modal">Cerrar</button>
							</div>
					      </div>
						</div>
					 </div>
				</div> <!-- /.modal -->
		       </td>
			   <td><c:out value="${listRol.rolRole}" /></td>
			   <td><c:out value="${listRol.rolDescription}" /></td>
			   <td><c:out value="${listRol.nombreOrganizacion}" /></td>
				
			   <th>
				 <button type="button" name="detalle" title="Ver Detalle de Rol" style="background: white; border: none" onclick="MostrarFormularioVerDetalle('<c:out value="${listRol.idRole}" />','p');">
					<img src="/portaltpe/static/images/verdetalle.png" 	width="20">
				 </button>
											
				<c:if test="${listRol.disabled == false}">							
				<button type="button" name="registro" title="Permisos de Rol" style="background: white; border: none" onclick="MostrarFormularioRol('<c:out value="${listRol.idRole}" />','p');" >
					<img src="/portaltpe/static/images/permiso.png" width="20">
				</button>		
				</c:if>
				
				<c:if test="${listRol.disabled == true}">
				<button type="button" name="registro" title="Permisos de Rol" style="background: white; border: none" disabled="${listRol.disabled}" onclick="MostrarFormularioRol('<c:out value="${listRol.idRole}" />','p');" >
					<img src="/portaltpe/static/images/permiso.png" width="20">
				</button>
                </c:if>
                
                <button type="button" name="eliminar" title="Eliminar Rol" style="background: white; border: none" onclick="EliminarRolFormulario('<c:out value="${listRol.idRole}" />');">
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
 <div class="modal fade" id="mostrarmodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="false" style=" position: absolute; z-index: 5;">
   <div class="modal-dialog" id="modalTamanio">
	 <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 class="modal-title">Registro de Rol</h3>
		 </div>
		
		 <div class="modal-body">
			<form:form name="formularioalta" action="/portaltpe/home/manageUsers/rolesadd" onsubmit="validar = (ValidarOrganizacion(this) && ValidarRolDescripcion(this) && confirmacionRegistroRol(this)); return validar;">
			   <div class="form-group">
				 <label for="recipient-name" class="form-control-label">Capturar Rol:</label> 
				 <input type="text" value="ROLE_USUARIO_" pattern="[A-Za-z0-9 _]+" class="form-control" id="rol" name="rol">
			   </div>
			   
			   <div class="form-group">
				 <label for="message-text" class="form-control-label">Capturar Descripción:</label>
				 <textarea class="form-control" id="descripcion" name="descripcion" pattern="[A-Za-z0-9]+"></textarea>
				</div>

				<div class="form-group">
				   <label for="message-text" class="form-control-label">Seleccione Organización:</label>
				   
				   <table width="100%" class="table table-striped table-bordered table-hover" id="tablaCatOrganizaciones">		
					<thead>
					  <tr>
						<th>Opción</th>
						<th>Organización</th>
					  </tr>
					</thead>
					
					<tbody>
					  <c:forEach items="${model2.tickets}" var="organization">
					  <tr>
						<td>
						  <input type="radio" name="idOrganizacion" id="${organization.organizationId}" value="${organization.organizationId}" onclick="marcado=true">
						</td>
					    <td><c:out value="${organization.organizationName}" /></td>	
					  </tr>
					  </c:forEach>
					</tbody>
				   </table>
				</div>

				<div class="modal-footer">
					<button type="button" class="botonFormulario btn btn-primary" data-dismiss="modal">Cerrar</button>
					<button type="submit" class="botonFormulario btn btn-primary" name="registar">
						<i class="fa fa-floppy-o fa-fw"></i> Guardar
					</button>
				</div>
			</form:form>
		 </div>
		</div>
	   </div>
	  </div>
	
	  <div>
	 </div>
	
	<c:set var="valor3" value="true" />
	<c:set var="valor4" value="false" />
	<c:forEach items="${model.roles}" var="listRol">
		<c:if test="${listRol.succes eq valor3}">
			<script>
			alert("El Rol fue creado satisfactoriamente.");
			location.href = "/portaltpe/home/manageUsers/roles";
			</script>
		</c:if>

		<c:if test="${listRol.succes eq valor4}">
			<script>
			alert("Se Encontraron Errores, vuelva a intentarlo");
			location.href = "/portaltpe/home/manageUsers/roles";
			</script>
		</c:if>
	</c:forEach>



	<!-- sección Formulario Permisos de Roles -->
	<div class="modal fade" id="mostrarmodal2" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="false">
	  <div class="modal-dialog" id="modalTamanio">
		<div class="modal-content">
		  <div class="modal-header">
			 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 class="modal-title">Permisos de Rol</h3>
		  </div>
		  
		  <div class="modal-body">
			 <p>Seleccione Menus</p>
		  
		     <form:form name="formulariopermisos" action="/portaltpe/home/manageUsers/permisosadd" onsubmit="validar = (ValidarIdHome(this) && confirmacionRegistroPermisos()); return validar;">
			 <input type="hidden" name="idRole" id="idRole">
			   
			   <c:forEach items="${model5.menus}" var="menu">
			  <c:if test="${menu.idSubMenu == 0}">
			   <li>
				 <input type="checkbox" name="idMenu" id="${menu.idMenu}" value="${menu.idMenu}" onclick="marcadoMenu=true">
					<font color="#c63939"><c:out value="${menu.nombreMenu}" /></font>
				 
				 <ul class="nav nav-second-level">
					<c:forEach items="${model5.menus}" var="submenu">
					  <c:if test="${menu.idMenu == submenu.idSubMenu}">
					<li>
						<input type="checkbox" name="idMenuSub" id="${submenu.idMenu}" value="${submenu.idMenu}" onclick="marcadoSubMenu=true" style="margin-left: 7%;">
						<c:out value="${submenu.nombreMenu}" />
					</li>
					  </c:if>
					</c:forEach>
                 </ul>
			 </li>
			</c:if>
			 </c:forEach>

			 </br>

			 <div class="form-group">
				<label for="message-text" class="form-control-label">Seleccione Pagina Principal:</label>
				
				<table width="100%" class="table table-striped table-bordered table-hover" id="tablaHome">
				
				<thead>
				  <tr>
					<th>Opción</th>
					<th>Origen Home</th>
					<th>Descripción</th>
				  </tr>
				</thead>
				
				<tbody>
				 <c:forEach items="${model8.origenHome}" var="listHome">
				 <tr>
				   <td>
				     <input type="radio" name="idHome" id="${listHome.idHome}" value="${listHome.idHome}" onclick="marcado=true">
				   </td>
				   <td><c:out value="${listHome.origenHome}" /></td>
				   <td><c:out value="${listHome.descripcion}" /></td>
				 </tr>
				 </c:forEach>
				</tbody>
			    </table>
			</div>

			<div class="modal-footer">
				<button type="button" class="botonFormulario btn btn-primary" data-dismiss="modal">Cerrar</button>
				<button type="submit" class="botonFormulario btn btn-primary">
					<i class="fa fa-floppy-o fa-fw"></i> Dar Permisos
				</button>
            </div>
		    </form:form>
        </div>
       </div>
	  </div>
	 </div>
	
	<!-- sección Formulario Detalle de Roles -->
  <div class="modal fade" id="mostrarmodalDetalle" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog" id="modalTamanio">
	  <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 class="modal-title">Detalle de Rol</h3>
		</div>
		
		<div class="modal-body">
		   <p>Detalle</p>
		   
		   <form name="formularioDetalle" action="" method="post">
			  <input type="hidden" name="idRole" id="idRole">
				<c:forEach items="${modelMenus.menus}" var="menuDetalle">
					<li>
					<c:if test="${menuDetalle.idSubMenu == null }">
					<input type="checkbox" name="idMenuDetalle" id="idMenuDetalle" value="${menuDetalle.idMenu}" checked="true">
						 <font color="#c63939"><c:out value="${menuDetalle.nombreMenu}" /></font>
						
						<ul class="nav nav-second-level">
						  <c:forEach items="${modelSubMenu.menus}" var="submenuDetalle">
						  <li><c:if test="${menuDetalle.idMenu == submenuDetalle.idSubMenu }">
							  <input type="checkbox" name="idMenuSubDetalle" id="idMenuSubDetalle" value="${submenuDetalle.idMenu}" style="margin-left: 7%;" checked="true">
								<c:out value="${submenuDetalle.nombreMenu}" />
							   </c:if>
						   </li>
						   </c:forEach>
						</ul>
					</c:if>
					</li>
			    </c:forEach>
						
				</br>

				<div class="modal-footer">
				   <button type="button" class="botonFormulario btn btn-primary" data-dismiss="modal">Cerrar</button>
				</div>
			 </form>
			</div>
		   </div>
		  </div>
	    </div>
	
	<c:set var="valor" value="true" />
	<c:set var="valor2" value="false" />
	<c:forEach items="${model7.menuRol}" var="menu">
		<c:if test="${menu.succes eq valor}">
			<script>
				alert("Permisos creados satisfactoriamente, para que estos permisos sean aplicados correctamente, favor de cerrar sesion.");
				location.href = "/portaltpe/home/manageUsers/roles";
			</script>
		</c:if>

		<c:if test="${menu.succes eq valor2}">
			<script>
				alert("Se Encontraron Errores, vuelva a intentarlo");
				location.href = "/portaltpe/home/manageUsers/roles";
			</script>
		</c:if>
	</c:forEach>

	<!-- sección Formulario Eliminar Datos Usuario -->

	<div class="modal fade" id="eliminarmodal" role="dialog">
		<div class="modal-dialog modal-lg modal-center">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3>Eliminar Rol</h3>
				</div>

				<form:form name="formularioeliminarrol" action="/portaltpe/home/manageUsers/roldelete">
					<input class="form-control" type="hidden" name="idRole" maxlength="200">

					<div class="modal-footer">
						<div class="modal-footer"> ¿Esta seguro que desea eliminar el Rol? 
						   <input type="submit" class="btn btn-danger" style="width: 80px;" height: 40px" name="SI" Value="Si"> 
						   <input type="button" data-dismiss="modal" class="btn btn-danger" style="width: 80px;" height: 40px" name="NO" Value="No">
						</div>
					</div>
				</form:form>
			</div>

		</div>
	</div>
	
	<c:set var="valor5" value="true" />
    <c:set var="valor6" value="false" />
     <c:forEach items="${modelDelete.roles}" var="deleteRol">
        <c:if test="${deleteRol.succes eq valor5}">
          <script>
             alert("El Rol fue eliminado satisfactoriamente.");
             location.href = "/portaltpe/home/manageUsers/roles";
          </script>
        </c:if>

        <c:if test="${deleteRol.succes eq valor6}">
          <script>
             alert("El usuario tiene asignado uno o mas usuarios favor de elimnarlos para proseguir");
             location.href = "/portaltpe/home/manageUsers/roles";
          </script>
		</c:if>
     </c:forEach>

	<center>
		<div id="pageNavPosition"></div>
	</center>

<div class="modal fade" id="mostrarmodalsla" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="false" style=" position: absolute; z-index: 5;">
	<div class="modal-dialog" id="modalTamanio">
		<div class="modal-content sla">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 class="modal-title">CONFIGURACION DE SLA´S</h3>
			</div>
			
			<div class="modal-body">
				<form:form name="formularioConfigSla" action="/portaltpe/home/manageUsers/configSla">
					
					<table width="100%"class="table table-striped table-bordered table-hover" id="tablaSlas">
					
					<thead>
						<tr>
							<th>Nombre Cliente</th>
							<th>Latencia</th>
							<th>Paquetes</th>
							<th>Disponibilidad</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					
					<c:forEach items="${model9.slas}" var="configuracion">
						<tr>
							<td width="100%"><input type="hidden" value="${configuracion.organizacion_name}" name="org" id="org">${configuracion.organizacion_name}</td>
							<td width="25%"><input type="number" value="${configuracion.latencia}" name="lat" id="lat" size="5"></td>
							<td width="25%"><input type="number" value="${configuracion.paquetes}" name="paq" id="paq" size="5"></td>
							<td width="25%"><input type="number" value="${configuracion.disponibilidad}" name="dis" id="dis" size="5"></td>
							<td width="25%"><button type="submit" class="botonFormulario btn btn-primary" name="updateConfig">
									<i class="fa fa-floppy-o fa-fw"></i> Actualizar
								</button>
							</td>
						</tr>
					</c:forEach>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</div>

<style type="text/css">
	.ver {
		display: block;
	}
	
	.nover {
		display: none;
	}
</style>

	<script>
		//var menus = new Array();
		var menus = "";
		function llenadoIdsMenus(id1) {
			
			menus = menus + id1 + ",";
			
			alert('valor de menus '+ menus);
			
			document.formularioEliminarPermisos.id1.value = menus;
			
			//alert('Tamaño '+document.formularioEliminarPermisos.idMenuDetalle.value = menus);
		}
	</script>
	
	<script>
		
		//var subMenus = new Array();
		//var submenus = new SubMenusList();
		var submenus = "";
		function llenadoIdsSubMenus(id2) {
			
			submenus = submenus + id2 + ",";
			
			alert('valor de submenus '+ submenus);
			
			//subMenus.add(id2);
			
			//alert('Tamaño '+document.formularioEliminarPermisos.id1.value = subMenus);
			
			document.formularioEliminarPermisos.id2.value = subMenus;
			
			//alert('Tamaño de la lista', subMenus.size());
		}
	</script>

	<script type="text/JavaScript">
		function vernover(identificacion) {
			var menu = document.getElementById(identificacion);
			if (menu.className == 'ver') {
				menu.className = 'nover';
			} else {
				menu.className = 'ver';
			}
		}
	</script>


	<script>
		$(document)
				.ready(
						function() {
							$('#tablaRoles')
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
							$('#tablaCatOrganizaciones')
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
							$('#tablaSlas')
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
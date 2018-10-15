<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="icon" type="image/png"
	href="/portaltpe/static/images/iconoETP.ico" />

<!-- Styles -->
<link href="/portaltpe/static/dist/css/sb-admin-2.min.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/metisMenu/metisMenu.min.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/font-awesome/css/font-awesome.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/animate.min.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/rickshaw.min.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/chosen.min.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/chosen-bootstrap.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/bootstrap-checkbox.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/summernote.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/summernote-bs3.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/chosen.min.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/chosen-bootstrap.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/tabdrop.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/morris.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/minoral.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/charts.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/nuevoEstilo.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/paginacion/css/paginacion.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/estilos.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/drawerStyle.css" rel="stylesheet"></link>


<link rel="stylesheet" href="/portaltpe/static/vendor/menu/css/estilo.css">
<script src="<c:url value='/static/vendor/menu/js/scripts.js' />"></script>
<script src="<c:url value='/static/vendor/menu/js/materialize.min.js' />"></script>
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
<link href="/portaltpe/static/vendor/bootstrap/css/tp-empresarial.css" rel="stylesheet"></link>


<!-- JavaScript -->
<%-- <script src="<c:url value='/static/vendor/jquery/jquery.min.js' />"></script> --%>
<script src="<c:url value='/static/vendor/bootstrap/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/static/vendor/metisMenu/metisMenu.min.js' />"></script>
<script src="<c:url value='/static/dist/js/sb-admin-2.js' />"></script>
<script src="<c:url value='/static/vendor/modules/solid-gauge.js' />"></script>
<script src="<c:url value='/static/vendor/js/exporting.js' />"></script>
<script src="<c:url value='/static/vendor/js/basejs.js' />"></script>
<script src="<c:url value='/static/vendor/js/run_prettify.js' />"></script>
<script src="<c:url value='/static/vendor/jquery/jquery.nicescroll.min.js' />"></script>
<script src="<c:url value='/static/vendor/jquery/jquery.blockUI.js' />"></script>
<script src="<c:url value='/static/vendor/js/chosen.jquery.min.js' />"></script>
<script src="<c:url value='/static/vendor/js/parsley.min.js' />"></script>
<script src="<c:url value='/static/vendor/jquery/jquery.bootstrap.wizard.min.js' />"></script>
<script src="<c:url value='/static/vendor/bootstrap/js/bootstrap-tabdrop.min.js' />"></script>
<script src="<c:url value='/static/vendor/js/minoral.min.js' />"></script>
<script src="<c:url value='/static/vendor/paginacion/js/paginaciontabla.js' />"></script>

<!-- cargarPDF -->

<link href="/portaltpe/static/vendor/fileInput/css/fileinput.css" rel="stylesheet">
<link href="/portaltpe/static/vendor/fileInput/themes/explorer/theme.css" rel="stylesheet">
<script src="<c:url value='/static/vendor/fileInput/js/plugins/sortable.js'/>"></script>
<script src="<c:url value='/static/vendor/fileInput/js/fileinput.js'/>"></script>
<script src="<c:url value='/static/vendor/fileInput/js/locales/es.js'/>"></script>
<script src="<c:url value='/static/vendor/fileInput/themes/explorer/theme.js'/>"></script>

	<link href="/portaltpe/static/tree/styles/styles.css" rel="stylesheet">
	<script src="<c:url value='/static/tree/js/validate.js'/>"></script>


<script type="text/javascript">
	function enabled() {
		$('input#archivo').removeAttr('disabled');
	}
	function validar(archivo) {
		extPermitidas = new Array(".xlsx", ".xls", ".docx", ".doc", ".ppt",
				".pptx", ".pdf", ".txt");
		mierror = "";

		if (!archivo) {
			mierror = "No has seleccionado ningun archivo";
		} else {
			extension = (archivo.substring(archivo.lastIndexOf(".")))
					.toLowerCase();
			permitida = false;
			for (var i = 0; i < extPermitidas.length; i++) {
				if (extPermitidas[i] == extension) {
					permitida = true;
					break;
				}
			}
			if (!permitida) {
				mierror = "Comprueba la extension de los archivos a subir.\nSolo se pueden subir archivos con extension"
						+ extPermitidas.join();

			} else {
				//alert("Todo correcto. Voy a submitir el formulario");
				$('button#btnArchivo').removeAttr('disabled');
				$('input#archivo').attr('disabled', 'disabled');
				$('button#btnArchivo').removeAttr('disabled');
				return true;
			}
		}
		alert(mierror);
		$('input#archivo').val('');
		$('button#btnArchivo').attr('disabled', 'disabled');
		
		if($("#btnArchivo").val() == 0){
			alert("Selecciona una carpeta");
		}
		
		return false;
	}
</script>
<!-- cargarPDF -->
<title>Totalplay Empresarial</title>

<script>
	$(document).ready(function() {
		$(".button-collapse").sideNav({
			closeOnClick : false
		});
	});

	function MostrarFormulario(nombreusuario, nombre, organizacion, role,
			contraseña) {

		document.formulariomostrardatos.username.value = nombreusuario;
		document.formulariomostrardatos.name.value = nombre;
		document.formulariomostrardatos.organization.value = organizacion;
		document.formulariomostrardatos.role.value = role;
		//document.formulariomostrardatos.password.value = contraseña;

		$("#VerDatosModal").appendTo("body").modal("show");

		return;

	}

	function MostrarFormularioRol(idRole) {

		document.formulariopermisos.idRole.value = idRole;

		$("#mostrarmodal2").appendTo("body").modal("show");

		return;

	}

	function MostrarFormularioVerDetalle(idRole) {

		//document.formularioDetalle.idRole.value = idRole;
		var mod = "#mprueba" + idRole;
		$(mod).appendTo("body").modal("show");
		//$("#mprueba1").css('display');
		//document.getElementById('mprueba').style.display = 'block';

		return;

	}

	function ActualizaraFormulario(nombreusuario, nombre, organizacion,
			contraseña) {

		document.formularioactualizar.username.value = nombreusuario;
		document.formularioactualizar.name.value = nombre;
		document.formularioactualizar.organization.value = organizacion;
		document.formularioactualizar.password.value = "";

		$("#actualizarmodal").appendTo("body").modal("show");

		return;

	}

	function LimpiarActualizaraFormulario() {

		document.formularioactualizar.name.value = "";
		document.formularioactualizar.organization.value = "";
		document.formularioactualizar.password.value = "";

	}

	function EliminarFormulario(nombreusuario, nombre, organizacion, contraseña) {

		document.formularioeliminar.username.value = nombreusuario;
		document.formularioeliminar.name.value = nombre;
		document.formularioeliminar.organization.value = organizacion;
		document.formularioeliminar.password.value = "000000000000000";

		$("#eliminarmodal").appendTo("body").modal("show");

		return;

	}

	function CambiarRolFormulario(nombreusuario) {

		document.formulariocambiarrol.username.value = nombreusuario;

		$("#cambiarrolmodal").appendTo("body").modal("show");

		return;

	}

	function FormularioAjustesConciliacion(nombre1, nombre2, apellidos,
			categoria, usuariofinal, descripcion, solucion, folioabierto,
			foliocerrado, afectadocliente, diagnosticofinal, folioincidencia,
			resumen) {
		var cadena2 = "";
		var x = 0;

		document.getElementById('nombre1').value = nombre1;
		document.getElementById('nombre2').value = nombre2;
		document.getElementById('apellidos').value = apellidos;
		document.getElementById('categoria').value = categoria;
		document.getElementById('usuariofinal').value = usuariofinal;
		document.getElementById('afectadocliente').value = afectadocliente;
		document.getElementById('diagnosticofinal').value = diagnosticofinal;
		document.getElementById('folioincidencia').value = folioincidencia;
		//Quita los || de la descripcion y los sustituye por un retorno de linea
		for (i = 0; i < descripcion.length; i++) {
			x = i + 1;
			if (descripcion.charAt(i) == "|") {
				cadena2 = cadena2 + "\n";

			} else {

				cadena2 = cadena2 + descripcion.charAt(i);
			}
		}

		document.formularioajustesconciliacion.descripcion.value = cadena2;
		document.getElementById('resumen').value = resumen;
		document.getElementById('solucion').value = solucion;
		document.getElementById('fechaapertura').value = folioabierto;
		document.getElementById('fechasolucion').value = foliocerrado;

		$("#ModalAjustesConciliacion").appendTo("body").modal("show");

		return;

	}

	function FormularioPorConciliar(nombre1, nombre2, apellidos, categoria,
			usuariofinal, descripcion, solucion, folioabierto, foliocerrado) {
		var cadena2 = "";
		var x = 0;

		document.getElementById('nombre1').innerHTML = nombre1;
		document.getElementById('nombre2').innerHTML = nombre2;
		document.getElementById('apellidos').innerHTML = apellidos;
		document.getElementById('categoria').innerHTML = categoria;
		document.getElementById('usuariofinal').innerHTML = usuariofinal;
		//Quita los || de la descripcion y los sustituye por un retorno de linea
		for (i = 0; i < descripcion.length; i++) {
			x = i + 1;
			if (descripcion.charAt(i) == "|") {
				cadena2 = cadena2 + "\n";

			} else {

				cadena2 = cadena2 + descripcion.charAt(i);
			}
		}

		document.formularioporconciliar.descripcion.value = cadena2;
		document.getElementById('resumen').innerHTML = '<p>' + usuariofinal
				+ '</p>';
		document.getElementById('solucion').innerHTML = '<p>' + solucion
				+ '</p>';
		document.getElementById('fechaapertura').innerHTML = '<p>'
				+ folioabierto + '</p>';
		document.getElementById('fechasolucion').innerHTML = '<p>'
				+ foliocerrado + '</p>';

		$("#ModalPorConciliar").appendTo("body").modal("show");

		return;

	}

	function ValidarRegistroIncidente(tenant) {
		punta = tenant;
		//alert("punta: " + punta);
		if (punta == "Seleccione...") {
			alert('Selecciona la Punta');
			return false;
		}

		categoria = document.getElementById('categoria').value;
		if (categoria == 0) {
			alert('Selecciona la Categoría');
			return false;
		}

		return true;
	}

	function ValidarRol() {

		username = document.formularioalta.username.value;
		name = document.formularioalta.name.value;
		role = document.getElementById('role').value;

		if ((role == 0) || (username.length == 0) || (name.length == 0)) {
			alert('Los campos no pueden ir vacios, favor de capturar.');
			return false;
		}

		return true;
	}

	function ValidarRolModificar() {
		role = document.formulariocambiarrol.role.value;
		if (role == 0) {
			alert('Selecciona el Rol');
			return false;
		}

		return true;
	}

	marcado = false;
	function ValidarOrganizacion(f) {
		if (!marcado) {
			alert("Selecciona la Organización");
			return false;
		} else {
			return true;
		}
	}

	function Confirmacion() {
		var mensaje = confirm("¿Deseas Continuar?");

		if (mensaje) {
			return true;
		}
		//Detectamos si el usuario denegó el mensaje
		else {
			return false;
		}
	}

	marcadoOrg = false;
	function ValidarRolDescripcion(f) {

		rol = document.formularioalta.rol.value;
		descripcion = document.formularioalta.descripcion.value;

		var str = rol;
		var res = str.split("_");

		if (res[2] == "") {
			alert('Favor de capturar el rol correctamente.');
			return false;
		}

		if (descripcion.length == 0) {
			alert('Capturar descripcion.');
			return false;
		}

		return true;
	}

	function ValidarLinkDescripcion(f) {

		nombreLink = document.formularioaltalink.nombreLink.value;
		link = document.formularioaltalink.link.value;

		if (nombreLink.length == 0) {
			alert('Capturar Nombre Link.');
			return false;
		}

		if (link.length == 0) {
			alert('Capturar Link.');
			return false;
		}

		return true;
	}

	function confirmacionRegistroLink() {
		var mensaje = confirm("¿Esta seguro que desea registrar el link?");

		if (mensaje) {
			return true;
		}
		//Detectamos si el usuario denegó el mensaje
		else {
			return false;
		}
	}

	function confirmacionRegistroRol() {
		var mensaje = confirm("¿Esta seguro que desea registrar el rol?");

		if (mensaje) {

			return true;
		}
		//Detectamos si el usuario denegó el mensaje
		else {
			return false;
		}
	}

	marcadoMenuP = true;
	marcadoSubMenuP = true;
	function confirmacionEliminarRol(f) {

		if (!marcadoMenuP) {
			alert('Entro al if de marcado');

			alert('valor de marcado ' + marcadoMenuP);
		}

		alert('valor de marcado ' + marcadoMenuP);

		/*if ($('#idMenuDetalle').is(':checked') == false && $('#idMenuSubDetalle').is(':checked') == false) {
			
			alert('Entro al if de checked == false');
			
			alert('Valor de idMenuDetalle'+$('#idMenuDetalle'));
			alert('Valor de idMenuSubDetalle'+$('#idMenuSubDetalle'));
			
			alert('Valor de f'+f);
			//alert('Valor de idSub'+idSub);
			
			//document.formularioEliminarPermisos.idMenuDetalle.value = idMenuDetalle;
			//document.formularioEliminarPermisos.idMenuSubDetalle.value = idMenuSubDetalle;
			
		}*/

		var mensaje = confirm("¿Esta seguro que desea eliminar los permisos al rol?");

		if (mensaje) {
			return true;
		}
		//Detectamos si el usuario denegó el mensaje
		else {
			return false;
		}
	}

	marcado = false;
	function ValidarIdHome(f) {
		if (!marcado) {
			alert("Seleccionar Pagina Inicial.");
			return false;
		} else {
			return true;
		}
	}

	marcadoSubMenu = false;
	marcadoMenu = false;
	function ValidarMenu(f) {
		if (marcadoSubMenu && !marcadoMenu) {
			alert("Seleccionar Menu Padre.");
			return false;
		} else {
			return true;
		}
	}

	function confirmacionRegistroPermisos() {
		var mensaje = confirm("¿Esta seguro que desea registrar los permisos al rol seleccionado?");

		if (mensaje) {
			return true;
		}
		//Detectamos si el usuario denegó el mensaje
		else {
			return;
		}
	}

	function FormularioPermisos(idMenu) {

		document.formulariopermisos.idMenu.value = idMenu;

		//     $("#actualizarmodal").appendTo("body").modal("show");

		return;

	}

	function EliminarRolFormulario(idRole) {

		document.formularioeliminarrol.idRole.value = idRole;

		$("#eliminarmodal").appendTo("body").modal("show");

		return;

	}

	function EliminarLinkFormulario(idLink, nameLink) {
		// alert(idLink + ' - ' + nameLink);
		document.formularioeliminarrol.idLinkP.value = idLink;
		document.formularioeliminarrol.nameLinkP.value = nameLink;
		$("#eliminarModalLink").appendTo("body").modal("show");
	}
	function callbackFunction(xmlhttp) {
		// alert(xmlhttp.responseXML);
	}
	function hideEliminarModalLink() {
		$("#eliminarModalLink").appendTo("body").modal("hide");
	}
	function deleteLink() {
		hideEliminarModalLink();
		try {
			URL = "http://localhost:9090/restportal/links/deleteLink/?idLink="
					+ document.formularioeliminarrol.idLinkParam.value;
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = callbackFunction(xmlhttp);
			xmlhttp.open("POST", URL, false);
			xmlhttp.setRequestHeader("Content-Type", "application/json");
			xmlhttp.onreadystatechange = callbackFunction(xmlhttp);
			xmlhttp.send();
			if (JSON.parse(xmlhttp.responseText).success == true) {
				alert(JSON.parse(xmlhttp.responseText).mssg + ' [ '
						+ document.formularioeliminarrol.nameLinkParam.value
						+ ' ]');
				location.reload();
			} else {
				alert(JSON.parse(xmlhttp.responseText).error);
			}
		} catch (e) {
			alert('Error al consumir el servicio deleteLink: ' + e);
		}
	}
	/* jquery agregado */
	$(document).ready(function(){
		 $("#boton").click(function(){ 
			if( !$("#boton").hasClass("boton")) { 
				$("#contenedor").addClass("important");
			 	$(".navbar-default").css("display","none");
			 	$("header").css("display","none");
			 	$("ol").css("visibility","hidden")
				$("#boton").addClass("boton");   				
				$("div.col-md-12").css("visibility","hidden");
				}else{
 			$("#boton").removeClass("boton");
 		 	$("#breadcrumb").addClass("breadcrumb");
 		 	$("#contenedor").removeClass("important");
 		 	$("div.col-md-12").removeAttr("style")
 		 	$("ol").removeAttr("style");
 		 	setTimeout(function(){
				$("nav.navbar-default").removeAttr("style");
		 		$("header").removeAttr("style");
 		 	},200);}
	     });
      });
	/* jquery agregado */
</script>

</head>

<body>
	<nav class="navbar navbar-default" role="navigation">
		<!-- 		<div class=""> -->
		<!-- 			<a class="" href="/portaltpe/home"> <img class="logotipoMenu" -->
		<!-- 				src="/portaltpe/static/images/enlace-empresarial-01.png" /> -->
		<!-- 			</a> -->
		<!-- 		</div> -->

		<div class="navbar-collapse navbarOverwriting">
			<div class="collapsing-content">
				<div class="user-controls">
					<form id="notification_mega">
						<ul>
							<li class="dropdown messages pfFoto"><a
								class="dropdown-toggle dropdownOverwriting"
								data-toggle="dropdown" href="#"> <strong
									class="clienteNombre">${model.username}</strong>
							</a>
								<div class="profile-photo">
									<img src="/portaltpe/static/images/000000.jpg" />
								</div></li>
							<li class="cerrarSesion"><a href="/portaltpe/cerrado"><i
									class="fa fa-power-off fa-fw"></i> Cerrar Sesión</a></li>
						</ul>
					</form>
				</div>
			</div>

		</div>
	</nav>

	<!-- 	<input type="checkbox" id="menu-toggle" checked /> -->
	<!-- 	<label for="menu-toggle" class="menu-icon"><i -->
	<!-- 		class="fa fa-bars"></i></label> -->

	<!-- 	<div class="fondo slideout-sidebar" role="navigation"> -->
	<!-- 		<div class="sidebar-nav"> -->
	<!-- 			<ul class="logged"> -->
	<!-- 				<li class="loggedImage"> -->
	<!-- 					<div class="imagenGreenFrame"> -->
	<!-- 						<img src="/portaltpe/static/images/000000.jpg" class="iconoUser" -->
	<!-- 							alt='' /> -->
	<!-- 					</div> -->
	<!-- 					<div class="userTagFrame"> -->
	<%-- 						<strong class="clienteNombre2 userTag">${model.username}</strong> --%>
	<!-- 					</div> -->
	<!-- 				</li> -->
	<!-- 			</ul> -->

	<!-- 			<ul class="nav menuLateral" id="side-menu"> -->



	<%-- 				<c:forEach items="${model3.menus}" var="menu"> --%>
	<%-- 					<li><c:if test="${menu.idSubMenu == null}"> --%>

	<%-- 							<a href="#"><i class="${menu.icono}"></i> <c:out --%>
	<%-- 									value="${menu.tituloPrincipal}" /><span --%>
	<!-- 								class="fa arrow flecha"></span></a> -->
	<!-- 							<ul class="nav nav-second-level"> -->
	<%-- 								<c:forEach items="${model4.menus}" var="submenu"> --%>

	<%-- 									<li><c:if test="${menu.idMenu == submenu.idSubMenu}"> --%>

	<%-- 											<a href="${submenu.path}"><i class="${submenu.icono}"></i> --%>
	<%-- 												<c:out value="${submenu.tituloPrincipal}" /></a> --%>

	<%-- 										</c:if></li> --%>

	<%-- 								</c:forEach> --%>

	<!-- 							</ul> -->
	<!-- 							/.nav-second-level -->

	<%-- 						</c:if></li> --%>
	<%-- 				</c:forEach> --%>

	<!-- 				<li class="cerrarMovil"><a href="/portaltpe/logout"><i -->
	<!-- 						class="fa fa-power-off fa-fw iconoMenu"></i> Cerrar Sesión</a></li> -->
	<!-- 			</ul> -->
	<!-- 		</div> -->
	<!-- 	</div> -->



	<header>
		<div id="bandera" style="display: none">1</div>
		<div class="navbar-fixed">
			<nav class="white">
				<div class="container">
					<div class="nav-wrapper">
						<a class="brand-logo" href="/portaltpe/home"> <img
							class="responsive-img"
							src="/portaltpe/static/vendor/menu/images/logo-enlace-tp.png">
						</a> <a class="button-collapse" href="/" data-activates="mobile-demo">
							<i class="material-icons"> <svg fill="#000000" height="24"
									viewBox="0 0 24 24" width="24"
									xmlns="http://www.w3.org/2000/svg">
                            <path d="M0 0h24v24H0z" fill="none"></path>
                  <path
										d="M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z"></path>
                </svg>
						</i>
						</a>
						
						<ul class="hide-on-med-and-down">
							<c:forEach items="${model3.menus}" var="menu">
								<c:if test="${menu.idSubMenu == null}">
									<li><a href="#"> <c:out
												value="${menu.tituloPrincipal}" />
									</a>
										<ul class="center-align">
											<c:forEach items="${model4.menus}" var="submenu">

												<c:if test="${menu.idMenu == submenu.idSubMenu}">
													<li><a href="${submenu.path}" > <c:out
																value="${submenu.tituloPrincipal}" /></a></li>
												</c:if>

											</c:forEach>

										</ul></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			</nav>
		</div>

		<ul class="side-nav center-align" id="mobile-demo"
			style="transform: translateX(-100%);">

			<c:forEach items="${model3.menus}" var="menu">
				<c:if test="${menu.idSubMenu == null}">
					<li><a href="#"> <c:out value="${menu.tituloPrincipal}" />
					</a>
						<ul class="center-align">
							<c:forEach items="${model4.menus}" var="submenu">

								<c:if test="${menu.idMenu == submenu.idSubMenu}">
									<li><a href="${submenu.path}"> <c:out
												value="${submenu.tituloPrincipal}" /></a></li>
								</c:if>

							</c:forEach>
						</ul></li>
				</c:if>
			</c:forEach>
			<ul>
				<li class="cerrarSesion"><a href="/portaltpe/logout"><i
						class="fa fa-power-off fa-fw"></i> Cerrar Sesión</a></li>
			</ul>
		</ul>
	</header>



	<div class="content-container" id="contenedor">
		<ol class="breadcrumb" id="breadcrumb">
			<!-- 			<li><a href="/portaltpe/home"><i class="fa fa-home"></i> Inicio</a> -->
			<%-- 			</li> ${model.category} ${model.subCategory} --%>
		</ol>
</body>

</html>

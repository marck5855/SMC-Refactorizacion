<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="header.jsp"></jsp:include>

<script src="<c:url value='/static/vendor/jquery/js/jquery.js' />"></script>
<script src="<c:url value='/static/vendor/jquery/js/jquery.min.js' />"></script>
<script src="<c:url value='/static/vendor/datatables/js/jquery.dataTables.min.js' />"></script>
<script src="<c:url value='/static/vendor/datatables-plugins/dataTables.bootstrap.min.js' />"></script>
<script src="<c:url value='/static/vendor/datatables-responsive/dataTables.responsive.js' />"></script>

<script type="text/javascript">
	// Init LOAD
	window.onload = function() {
	};
	// End LOAD
	window.addEventListener("load", function(event) {
		$('#myModal-1').modal('hide');
// 		var n = document.getElementById("persona");
// 		alert("EL VALOR" + n);
		prueba(); 
 
	});
	function showLoading(){
		$('#myModal-1').modal('show');
	}
	function getParameterByName(name) {
		name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		results = regex.exec(location.search);
		return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	function validarPunta(){
/* 		$('#myModal-1').modal('show');
		var e = document.getElementById("punta");
		var valueUserId = e.options[e.selectedIndex].value;
		var text = e.options[e.selectedIndex].text;
		document.formulariocategorias.tenant.value = text;
		document.formulariocategorias.userId.value = valueUserId;
		document.getElementById("formulariocategorias").submit();
		return;
 */
		$('#myModal-1').modal('show');
		var selectpunta = $("#punta");
		var idPunta = selectpunta.val();
		var text = selectpunta.find("option:selected").text();

		var tel = $("#tel").val();
		var email  = $("#email").val()
		var horario  = $("#horario").val()
		var descripcion  = $("#descripcion").val()
		var persona  = $("#persona").val()
		var insertar  = $("#insertar").val()
		
		$("#tenant").val(text);
		$("#userId").val(idPunta);
		$("#hidetel").val(tel);
		$("#hidemail").val(email);
		$("#hidehora").val(horario);
		$("#hidedesc").val(descripcion);
		$("#hidepersona").val(persona);
		
		
 		document.getElementById("formulariocategorias").submit(); 
		return;
	}
	
	function validaCampos(){
		console.log(document.getElementById("punta"));
		var e = document.getElementById("punta");
		var point;
		if(e.options) {
			 point = e.options[e.selectedIndex].text;
		} else {
			 point = e.value;
		}
		
		
		var categ  = document.getElementById("categoria").value;
		var pers   = document.getElementById("persona").value;
		var tel    = document.getElementById("tel").value;
		var email  = document.getElementById("email").value;
		var hra	   = document.getElementById("horario").value;
		var des    = document.getElementById("descripcion").value;
		
		
		if(point == 'Seleccione...'){
			 alert("Favor de Seleccionar una punta.");
			 return false;
		}
		if(categ == 0){
			 alert("Favor de Seleccionar una categoria.");
			 return false;
		}

		if(pers == null || pers == ""){
			alert("Favor de ingresar el nombre de la persona que reporta.");
			return false;
		}
		if(tel == null || tel == ""){
			alert("Favor de ingresar el numero Telefonico del contacto del sitio.");
			return false;
		}
		if(email == null || email == ""){
			alert("Favor de ingresar el correo electronico del contacto.");
			return false;
		}
		if(hra == null || hra == ""){
			alert("Favor de ingresar el horario de contacto para el seguimineto del ticket.");
			return false;
		}
		
		if(!/^([0-9])*$/.test(tel)){
			alert("El campo Telefono de contacto debe ser unicamente numeros.");
			return false;
		}
		
		if(des == null || des == ""){
			alert("Favor de ingrasar una descripcion del Incidente.");
			return false;
		}
		
		if(	categ  != null /*&& cuenta != null && comp   != null*/ && pers   != null && 
		    tel    != null && email  != null && hra	   != null && des    != null){
			document.formularioinsetainsidencia.submit();
		}else{
			return false;
		}
	}
	
	$(function() {
		$('.chosen-select').chosen();
		$('.chosen-select-deselect').chosen({
			allow_single_deselect : true
		});
	});
function prueba(){
	console.log("inicio de Prueba")
}
</script>
<div class="row">
	<div class="form-group">
		<h2 class="tituloSeccion">Registrar un Incidente</h2>
		<form:form id="formulariocategorias" name="formulariocategorias"
			action="/portaltpe/home/incidents/registerC" method="get">
			<input type="hidden" name="tenant" id="tenant">
			<input type="hidden" name="userId" id="userId">
			<input type="hidden" name="hidetel" id="hidetel">
			<input type="hidden" name="hidemail" id="hidemail">
			<input type="hidden" name="hidehora" id="hidehora">
			<input type="hidden" name="hidedesc" id="hidedesc">
			<input type="hidden" name="hidepersona" id="hidepersona">

		</form:form>
		<form:form name="formularioinsetainsidencia"
			action="/portaltpe/home/incidents/addincident"
			onsubmit="return ValidarRegistroIncidente(${model2.tenant});">

			<c:set var="host" value="${param.punta}" />
			<div class="col-md-6">
				<label class="etiquetaFormulario etiquetaLateral">Punta </label>
				<c:forEach items="${model2.ticketsCMDB}" var="ipPunta">
					<c:if test="${ipPunta.ipaddress eq host && fn:contains(host,'.')}">
								<input class="conEtiquetaIzquierda form-control " type="text" name="nombrePunta" id="nombrePunta" value="${ipPunta.puntaname}" disabled/>
								<c:set var="controlacategoria" value="n"/>
								<input type="hidden" name="punta" id="punta" value="${ipPunta.puntaid}">
							</c:if>
					</c:forEach>
					<c:if test="${host eq null || !fn:contains(host,'.')}">
					<select class="chosen-select" id="punta" name="punta">
								<option value="0" selected="selected">${model2.tenant}</option>
								<c:set var="controlacategoria" value="n" />
								<c:forEach items="${model2.ticketsCMDB}" var="point">
									<option value="${point.puntaid}"><c:out
											value="${point.puntaname}"/>
									</option>
								</c:forEach>
							</select>
					</c:if>
			</div>

			<div class="col-md-6">
				<label class="etiquetaFormulario etiquetaLateral">Categoría
				</label> <select class="chosen-select" id="categoria" name="categoria">
					<option value="0" selected="selected">Seleccione...</option>
					<c:forEach items="${categoria.tickets}" var="categories">
						<option value="${categories.idCategory}"><c:out
								value="${categories.sym}" /></option>
					</c:forEach>
				</select><input type="hidden" name="puntaUserId" id="puntaUserId"
					value="${model2.userId}">
			</div> 

			<div class="seccionMediaFormulario col-md-12 separador">
				<label class="etiquetaFormulario">* Persona que
					Reporta</label>&nbsp;&nbsp;&nbsp;&nbsp; <input
					class="conEtiquetaIzquierda form-control" type="text"
					name="persona" id="persona"
					placeholder="Ingresar la Persona que reporta" value="${model2.persona}"/>
			</div>

			<div class="seccionMediaFormulario col-md-12">
				<label class="etiquetaFormulario">* Teléfono de contacto</label>&nbsp;&nbsp;
				<input class="conEtiquetaIzquierda form-control" type="text"
					name="tel" id="tel"
					placeholder="Ingresar el Teléfono actualizado del contacto del sitio/servicio" value="${model2.tel}"/>
			</div>

			<div class="seccionMediaFormulario col-md-12">
				<label class="etiquetaFormulario">* Correo Electrónico</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input class="conEtiquetaIzquierda form-control" type="email"
					name="email" id="email"
					placeholder="Ingresar el Correo electrónico actualizado del contacto" value="${model2.email}"/>
			</div>

			<div class="seccionMediaFormulario col-md-12">
				<label class="etiquetaFormulario">* Horario de contacto</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input class="conEtiquetaIzquierda form-control" type="text"
					name="horario" id="horario"
					placeholder="Ingresar el Horario de contacto para el seguimiento del Ticket" value="${model2.horario}"/>
			</div>

			<div class="seccionMediaFormulario col-md-12">
				<label class="etiquetaFormulario">Descripción</label>
				<textarea class="form-control" rows="6" id="descripcion" 
					name="descripcion" ><c:out value="${model2.descripcion}"></c:out> </textarea>
			</div>
			<input type="hidden" name="usuario" id="usuario" value="${model.username}"/>


			<div class="content-file col-md-12">
				   <label class="etiquetaFormulario">Adjuntar Archivo</label> 
				   <input type="file" name="insertar" id="insertar"/>
			</div>
			<div class="botonesFormulario">
				<button type="button" class="botonFormulario btn btn-primary"
					onclick="validaCampos()">
					<i class="fa fa-floppy-o fa-fw"></i> Guardar
				</button>

				<button type="reset" class="botonFormulario btn btn-primary">
					<i class="fa fa-eraser fa-fw"></i> Limpiar
				</button>
			</div>
		</form:form>
	</div>
</div>

<c:set var="valor" value="true" />
<c:set var="valor2" value="false" /><%-- 
<input type="text" name="tickets" id="tickets" value="${model5.tickets}"> --%>

<c:forEach items="${model5.tickets}" var="IncidentRegister">
	<c:if test="${IncidentRegister.succes eq valor}">
		<script>
		alert("Incidente registrado exitosamente con numero: "
			+ '<c:out value="${IncidentRegister.refNum}" />');
		location.href = "/portaltpe/home/incidents/register";
	</script>
	</c:if>
	<c:if test="${IncidentRegister.succes eq valor2}">
		<script>
		alert("Se Encontraron Errores, vuelva a intentarlo");
		location.href = "/portaltpe/home/incidents/register";
	</script>
	</c:if>
</c:forEach>
<br />
<br />
<br />
<jsp:include page="footer.jsp"></jsp:include>
<!-- Modal -->
<div class="modal fade" id="myModal-1" role="dialog">
	<p style="text-align: center;">
		<img
			style="max-width: 20%; max-height: 20%; position: absolute; top: 0; left: 0; right: 0; bottom: 0; margin: auto;"
			src="/portaltpe/static/images/loading3.gif" class="logo" />
zz</p>
</div>
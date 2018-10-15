<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- <link href="/portaltpe/static/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/portaltpe/static/vendor/fileInput/css/fileinput.css"
	rel="stylesheet">
<link
	href="/portaltpe/static/vendor/fileInput/themes/explorer/theme.css"
	rel="stylesheet">
<script src="<c:url value='/static/vendor/jquery/jquery.min.js'/>"></script>
<script
	src="<c:url value='/static/vendor/fileInput/js/plugins/sortable.js'/>"></script>
<script src="<c:url value='/static/vendor/fileInput/js/fileinput.js'/>"></script>
<script src="<c:url value='/static/vendor/fileInput/js/locales/es.js'/>"></script>
<script
	src="<c:url value='/static/vendor/fileInput/themes/explorer/theme.js'/>"></script>
<script
	src="<c:url value='/static/vendor/bootstrap/js/bootstrap.min.js'/>"></script>

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
</script> --%><!-- 
<body> -->
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<h3>Seleccionar un documento a cargar</h3>
		<spring:url value="/home/upload?${_csrf.parameterName}=${_csrf.token}"
			var="uploadURL" />

		<c:set var="valor" value="1" />
		<c:set var="valor2" value="0" />
		<c:forEach items="${fileModelSuccess.archivo}" var="fileModel">
			<c:if test="${fileModel.succes eq valor}">
				<script>
					alert("Archivo cargado satisfactoriamente.");
				</script>
			</c:if>
		
			<c:if test="${fileModel.succes eq valor2}">
				<script>
					alert("Se Encontraron Errores, vuelva a intentarlo");
				</script>
			</c:if>
		</c:forEach>
		<c:if test="${fileModelExist > valor2}">
			<script>
				alert("Archivo duplicado, intente con un nombre distinto.");
			</script>
		</c:if>
		<c:if test="${folderError == valor2}">
			<script> alert("Selecciona una carpeta");</script>
		</c:if>
		<c:if test="${folderNum > valor2}">
			<script> alert("Selecciona una carpeta existente, se excedio el limite de carpetas creadas");</script>
		</c:if>
		
		<form:form modelAttribute="formUpload" action="${uploadURL}"
			name="carga" enctype="multipart/form-data" method="post">
				<form:input path="files" type="file" id="archivo" name="file"
					accept="application/pdf" onchange="validar(this.form.archivo.value)" />
			<div>
				<div>
					
					<select name="path" style="width: 300px;" 
							class="carpeta form-control" onChange="mostrar(this.value);">
					<c:forEach items="${carpeta.listaCapeta}" var="folder">	
						<option value="${folder.path}"> ${folder.path} </option>
					</c:forEach>
						<option value="seleccionar" selected="selected"> - Seleccionar carpeta - </option>
						<option value="cnueva"> Nueva Carpeta	</option>
					</select>


				</div>
				<div id="nueva"  style="display: none;">
					<input class="form-control" type="text" name="nuevaCarpeta" 
						   id="nuevaCarpeta" style="width: 300px;"
						   placeholder="Ingresar el nombre de la nueva carpeta" />
					<input type="button" id="boton_append_1" value="Agregar carpeta"/>
				</div>
			</div><br/>
			<button id="btnArchivo" type="submit" onclick="enabled()" disabled="disabled">
				Cargar Documento
			</button>
		</form:form>
	</div>
	
<!-- </body> -->
<br>
<br>
<br>
<br>
<jsp:include page="footer.jsp"></jsp:include>
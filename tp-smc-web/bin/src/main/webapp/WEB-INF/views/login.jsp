<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
 
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html">
<meta charset="utf-8">

<link rel="icon" type="image/png" href="/portaltpe/static/images/iconoETP.ico" />

<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="/portaltpe/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/basestyle.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/cssLogin.css" rel="stylesheet"></link>
<link href="/portaltpe/static/vendor/bootstrap/css/estilos.css" rel="stylesheet"></link>

<title>Totalplay Empresarial</title>

</head>

<body>
  <div id="wrap">
    <div class="row">
	  <div id="content" class="col-md-12 full-page login" style="display: flex; justify-content: center; align-items: center;">
		<div class="col-md-4">
		  <div class="contenedorLogo">
			 <h2 class="">
				<img class="logotipoLogin" src="/portaltpe/static/images/enlace-empresarial-01.png">
			 </h2>			
			
			<br/>
			 
<!-- 			<h5 class="" style="color: #75767c; font-weight: bold;"> -->
<!-- 				<strong>Portal Corporativo</strong> -->
<!-- 			</h5> -->
				
			<br/>
		   </div>

		   <div class="" style="padding-bottom: 0cm" ALIGN=center>
			 <c:url var="loginUrl" value="/login" />
						
		     <form:form role="form" action="${loginUrl}" method="post">
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">
						<p>Credenciales invalidas</p>
					</div>
				 </c:if>
				
				 <c:if test="${param.logout != null}">
					<div class="alert alert-success">
						<p>Cierre de sesión exitoso</p>
					</div>
				  </c:if>

				<fieldset>
				   <div class="contenedorLogin">
						<input class="campoLogin" type="text" name="username" placeholder="Usuario" required>
								
						<input class="campoLogin" type="password" name="password" placeholder="Contraseña" required>
										
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

						<input type="submit" class="botonIngreso" value="Ingresar">
								
					    <div class="contenedorCasilla">
							 <input class="casillaTerminos" name="remember" type="checkbox" required checked>
							
							 <label class="textoCasilla">Al entrar al sitio usted acepta el Aviso de Uso de Datos del Portal. </label>
									
							 <div class="textoDisclaimer1">
								<p>
								 <span class="strongDisclaimer">Total Play Telecomunicaciones, S.A. de C.V.,</span> en adelante "TOTALPLAY". Todos los derechos reservados.
								</p>
								
								<p>
								La totalidad de la información contenida en este portal y sus
								descargas, se debe considerar como secreto comercial o
								industrial. Dicha información por su carácter de propiedad
								industrial o intelectual, financiero o cualquier índole está
								clasificada como confidencial y se prohíbe la divulgación a
								terceras personas y/o utilizar para provecho propio. Para
								propósitos distintos a los indicados. Se requiere previamente
								un permiso y consentimiento escrito de "TOTALPLAY".
								</p>
							
							    <div class="logoPieLogin">
						<!-- 		  <a href="http://www.totalplayempresarial.com.mx/" target="_blank" style="text-decoration: none !important;" style="text-align:center;"> 
									<img src="/portaltpe/static/images/copyright.png" class="logo" />
								  </a> -->
								</div>			
							  </div>
						  </div>
									
						</div>

					</fieldset>			
				</form:form>
				</div>
			   </div>
			</div>
		</div>
	</div>
</body>
</html>

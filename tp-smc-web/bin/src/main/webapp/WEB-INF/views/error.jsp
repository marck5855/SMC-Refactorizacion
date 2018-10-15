<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="es">
	
	<head>
		<meta http-equiv="Content-Type" content="text/html">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">		
		
		<link href="/portaltpe/static/vendor/bootstrap/css/bootstrap.min.css"  rel="stylesheet"></link>
	    
	    <title>Totalplay EmpresarialE</title>
	    
	</head>

	<body style="background:#f7f8f9!important">	
		<div class="container">
	        <div class="row">
	            <div class="col-md-6 col-md-offset-3">
	            	<br />
	                <div class="login-panel panel panel-default">	                
	                    <div class="panel-heading col-md-">
		                    <h2 class="panel-heading text-center h2">
		                    	<strong><font color="red">mi</font>enlace<font color="red">TP</font></strong>
		                    </h2>
		                    <h3 class="panel-title text-center h2">
		                    	<strong>Portal corporativo</strong>
		                    </h3>
		                    <br />	                        
	                    </div>
	                    <div class="panel-body" style="padding-bottom:0cm">
	                    	
		                    <h2 class="panel-heading text-center h1">
		                    	<strong>${model.pagename}</strong>
		                    </h2>
	                    	
							<div class="alert alert-danger">
								<p>${model.message}:</p>
								<p>${model.detail}</p>
							</div>							
	                      	
	                      	<div class="form-actions">	
								<a href="/portaltpe/home">
									<input type="submit" class="btn btn-lg btn-basic btn-block" value="Regresar" style="background-color:black;color:white">
								</a>
							</div>
	                      	
		                    <br />	                        
		                    <div class="row">
				            	<div class="col-md-12">
				            		<div class="well" style="padding:0.1;margin:0.1">
				                        <h6 class="text-center"><font size="1.2">Enlace TPE S.A. de C.V. en adelante ENLACE TP. Todos los derechos reservados.</font></h6>
				                        <h6 class="text-justify"><font size="1.2">
				                        	La totalidad de la información contenida en este portal y sus descargas, 
											se debe considerar como secreto comercial o industrial. Dicha información por su carácter de propiedad industrial o intelectual, 
											financiero o cualquier índole esta clasificada como confidencial y se prohíbe la divulgación a terceras personas y/o utilizar para provecho propio. 
											Para propósitos distintos a los indicados. Se requiere previamente un permiso y consentimiento escrito de Enlace TP.
										</font></h6>	
										<h6 class="text-center"><font size="1.2">Web creada por EnlaceTP &reg</font></h6>									
				                    </div>
				                </div>
				        	</div>
	                    </div>
	                </div>	                
	            </div>
	        </div>
	    </div>

	</body>
</html>

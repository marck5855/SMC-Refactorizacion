<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="headerSinMenu.jsp"/>
<script type="text/javascript">
 function mensajeCaptura(){
	 alert("la captura de pantalla se guardo automaticamente en la unidad ");
 }
</script>
<button name="boton" id="boton"></button>
<form action="/portaltpe/home/">
  <div class="">          
	<c:set var="homeVacio" value="3" />
	  <c:forEach items="${org.origenHome}" var="origen">
		<c:forEach items="${model2.tickets}" var="org">
<%-- 			 <form:form action="/portaltpe/home/capturaPantalla"> --%>
<%-- 					<c:if test="${origen.idHome ne homeVacio}"> --%>
<!-- 					     <div> -->
<!-- 					     	<button type="submit" name="btnImpresionPantalla" >Captura de Pantalla</button> -->
<!-- 					     </div> -->
<%-- 					</c:if> --%>
<%-- 			 </form:form> --%>
			
			<object style="width: 100%; padding-top: 70px" height="800" data="${origen.urlHome}refresh=1m&orgId=3&var-ID_CLIENTE=${org.organizationName}&var-ID_PUNTAS=top&theme=light&var-LAT=50&kiosk"></object>

			
			<c:if test="${origen.idHome ne homeVacio}">
			<div>
				<label style="font-size: 8pt; color: red;">* Para Regresar al estado normal del Gráfico al que se le realizo un zoom , favor de pulsar las teclas Ctrl + Z</label>
			</div>
			</c:if>
		</c:forEach>
	  </c:forEach>
	  
	 
	  
	</div>
</form>
</br>
</br>
<jsp:include page="footer.jsp"/>
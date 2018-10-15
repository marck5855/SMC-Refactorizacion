<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>


<link href="/portaltpe/static/vendor/bootstrap/css/estilos.css"
	rel="stylesheet"></link>


<script>
	$(function() {
		$('.chosen-select').chosen();
		$('.chosen-select-deselect').chosen({
			allow_single_deselect : true
		});
	});
</script>
<button name="boton" id="boton"></button>
<div class="baseDiv" style="padding-left: 0px; padding-right: 0px">
  <div style="margin: 0 auto !important; padding-left: 0px; padding-right: 0px">
	<c:set var="valor2" value="1"/>
	<c:set var="valor3" value="0" />
	
	<form:form name="formularioperformance" action="/portaltpe/home/informs/performanceTop">
	  <c:forEach items="${org.origenHome}" var="origen">
	  <c:forEach items="${model.tickets}" var="org"> 	
		<object style="width: 100%;" height="950" data="${origen.urlDos}refresh=1m&orgId=3&var-ID_CLIENTE=${org.organizationName}&var-LAT=50&var-boton=DISPONIBILIDAD&var-pPRINCIPAL=smc_performance_historico&var-ambiente=QA&kiosk&theme=light&orgId="></object>
	  </c:forEach>
	  </c:forEach>
     </form:form>
	
 </div>
</div>
<br>
<br>
<br>

<jsp:include page="footer.jsp"></jsp:include>
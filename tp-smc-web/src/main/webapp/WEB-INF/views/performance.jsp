<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>

<link href="/portaltpe/static/vendor/bootstrap/css/estilos.css" rel="stylesheet"></link>

<script>
	$(function() {
		$('.chosen-select').chosen();
		$('.chosen-select-deselect').chosen({
			allow_single_deselect : true
		});
	});
</script>

<div class="baseDiv" style="padding-left: 0px; padding-right: 0px">
 <div style="margin: 0 auto !important; padding-left: 0px; padding-right: 0px">
	<c:set var="valor2" value="1"/>
	<c:set var="valor3" value="0" />
		
	<form:form name="formularioperformance" action="/portaltpe/home/informs/performances">
	<label>Punta: </label>
		<select class="chosen-select" id="punta" name="punta" onchange="this.form.submit()">
		 <option value="0" selected="selected">Seleccione Punta...</option>
			<c:set var="controlapunta" value="" />
			
		<c:forEach items="${model2.ticketsCMDB}" var="point">
		  <c:if test="${point.inactive eq valor2}">
			<option class="opcionCritica" style="color:red;"  value="${point.puntaname}"><c:out value="${point.puntaname}"/></option>
		  </c:if>
		  
		  <c:if test="${point.inactive eq valor3}">
			<option class="opcionNormal"  value="${point.puntaname}"><c:out value="${point.puntaname}"/></option>
		  </c:if>				
		</c:forEach>		
		</select>
				
		<c:forEach items="${org.origenHome}" var="origen">
		  <c:forEach items="${model5.tickets}" var="ipdos">
			<object style="width: 100%;" height="950" data="${origen.urlDetalle}refresh=1m&orgId=3&var-ID_PUNTA=${ipdos.ipPunta}&var-ID_CLIENTE=${ipdos.userId}&theme=light"></object>
		  </c:forEach>
		</c:forEach>
	</form:form>
	<br> 
	<br>

	<form:form name="formularioperformance" action="/portaltpe/home/informs/performance">
	
	<c:if test="${modelip.ticketsCMDB != null}">
	  <c:forEach items="${org.origenHome}" var="origen">
		<c:forEach items="${modelip.ticketsCMDB}" var="ip"> 
		   <object style="width: 100%;" height="950" data="${origen.urlDetalle}refresh=1m&orgId=3&var-ID_PUNTA=${ip.ipaddress}&var-ID_CLIENTE=${ip.clienteid}&theme=light"></object>_
		</c:forEach>
	  </c:forEach>
	</c:if>
			
	</form:form>
	
	<br>
</div>
</div>
<br>
<br>
<br>
<jsp:include page="footer.jsp"></jsp:include>
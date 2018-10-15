<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

<script type="text/javascript">
	document
			.write('<style type="text/css">div.cp_oculta{display: block;}</style>');
	function MostrarOcultar(capa, enlace) {
		if (document.getElementById) {
			var aux = document.getElementById(capa).style;
			aux.display = aux.display ? "" : "block";
		}
	}
</script>

<div class="col-md-12" style="padding-left: 0px">
	<div class="baseDiv">
		<form>
			<p>
				<a class="botonFormulario botonAcordeon btn btn-primary" role="button" href="javascript:MostrarOcultar('grafica1');" ><i class="fa fa-calendar-times-o fa-fw"></i>Total de Tickets Mensuales</a>		
			</p>

			<div class="cp_oculta" id="grafica1" >
			<c:forEach items="${model.tickets}" var="organization">
				<object style="width: 100%;" height="480" data="http://${organization.ipPuertoGrafana}/grafana/dashboard-solo/db/portal_clientes?refresh=5m&orgId=3&var-ID_PUNTA=1&var-ID_CLIENTE=${organization.organizationId}&panelId=13&theme=light"></object>
			</c:forEach>
			</div>

			<p>
				<a class="botonFormulario botonAcordeon btn btn-primary" role="button" href="javascript:MostrarOcultar('grafica2');" ><i class="fa fa-code-fork fa-fw"></i>Total de Tickets Mensuales por Punta</a>	
			</p>

			<div class="cp_oculta" id="grafica2" >
			<c:forEach items="${model.tickets}" var="organization">
				<object style="width: 100%;" height="480" data="http://${organization.ipPuertoGrafana}/grafana/dashboard-solo/db/portal_clientes?refresh=5m&orgId=3&var-ID_PUNTA=1&var-ID_CLIENTE=${organization.organizationId}&panelId=8&theme=light"></object>
			</c:forEach>
			</div>

		</form>
	</div>

</div>
<br>
<br>
<br>
<jsp:include page="footer.jsp"></jsp:include>
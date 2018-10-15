<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

<link
	href="/portaltpe/static/vendor/datatables-plugins/dataTables.bootstrap.css"
	rel="stylesheet">

<script src="<c:url value='/static/vendor/jquery/js/jquery.js' />"></script>
<script
	src="<c:url value='/static/vendor/datatables/js/jquery.dataTables.min.js' />"></script>
<script
	src="<c:url value='/static/vendor/datatables-plugins/dataTables.bootstrap.min.js' />"></script>
<script
	src="<c:url value='/static/vendor/datatables-responsive/dataTables.responsive.js' />"></script>



<div class="col-md-12" style="padding-left: 0px">

	<div class="col-md-2" style="padding-left: 0px; padding-right: 0px">
		<form>
			<section class="tile">
				<div class="tile-body" style="padding: 0px">
					<ul class="nav nav-pills nav-stacked"
						style="max-width: 310px; background-color: white;">
						<li><a href="#"> Reporte de incidencias </a></li>
						<li><a href="#"> Reporte de disponibilidad de incidentes </a></li>
						<li><a href="#"> Reporte de disponibilidad total por punta </a></li>
						<li><a href="#"> Reporte de Tickets Imputables a Enlace vs Cliente </a></li>					
					</ul>
				</div>
			</section>
		</form>
	</div>

</div>

<jsp:include page="footer.jsp"></jsp:include>
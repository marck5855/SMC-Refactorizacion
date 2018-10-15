package mx.com.tp.smc.response;

public class AccessUser extends DataUser {
	
	private Graphic home;
	private Incidents incidentes;
	private Graphic reportes;
	private boolean pdfReporte;
	
	
	public Graphic getHome() {
		return home;
	}
	public void setHome(Graphic home) {
		this.home = home;
	}
	public Incidents getIncidentes() {
		return incidentes;
	}
	public void setIncidentes(Incidents incidentes) {
		this.incidentes = incidentes;
	}
	public Graphic getReportes() {
		return reportes;
	}
	public void setReportes(Graphic reportes) {
		this.reportes = reportes;
	}
	public boolean isPdfReporte() {
		return pdfReporte;
	}
	public void setPdfReporte(boolean pdfReporte) {
		this.pdfReporte = pdfReporte;
	}
	
	
			
}

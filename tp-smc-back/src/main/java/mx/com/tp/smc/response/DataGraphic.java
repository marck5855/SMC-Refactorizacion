package mx.com.tp.smc.response;

import org.joda.time.Period;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataGraphic extends ResponseCommon{
	
	private int close;
	private int active;
	private int solved;
	
	public DataGraphic(boolean success, Period timeRes, String error, String mssg, int total, int close, int active, int solved) {
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.total = total;
		this.mssg = mssg;
		this.close = close;
		this.active = active;
		this.solved = solved;
	}
	
	public int getCerrado() {
		return close;
	}
	public void setCerrado(int cerrado) {
		this.close = cerrado;
	}
	public int getEnCurso() {
		return active;
	}
	public void setEnCurso(int enCurso) {
		this.active = enCurso;
	}
	public int getSolucionado() {
		return solved;
	}
	public void setSolucionado(int solucionado) {
		this.solved = solucionado;
	}
	
	
	
}

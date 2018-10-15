package mx.com.tp.smc.request;

import java.math.BigDecimal;
import java.util.Date;

public class File {

	private BigDecimal idarchivo;

	private String archivoAnio;

	private byte[] archivoArchivo;

	private Date archivoFechaactualizacion;

	private Date archivoFechacarga;

	private String archivoMes;

	private String archivoNombre;

	private BigDecimal idoperator;

	private BigDecimal idrol;

	

	public BigDecimal getIdarchivo() {
		return idarchivo;
	}

	public void setIdarchivo(BigDecimal idarchivo) {
		this.idarchivo = idarchivo;
	}

	public String getArchivoAnio() {
		return archivoAnio;
	}

	public void setArchivoAnio(String archivoAnio) {
		this.archivoAnio = archivoAnio;
	}

	public byte[] getArchivoArchivo() {
		return archivoArchivo;
	}

	public void setArchivoArchivo(byte[] archivoArchivo) {
		this.archivoArchivo = archivoArchivo;
	}

	public Date getArchivoFechaactualizacion() {
		return archivoFechaactualizacion;
	}

	public void setArchivoFechaactualizacion(Date archivoFechaactualizacion) {
		this.archivoFechaactualizacion = archivoFechaactualizacion;
	}

	public Date getArchivoFechacarga() {
		return archivoFechacarga;
	}

	public void setArchivoFechacarga(Date archivoFechacarga) {
		this.archivoFechacarga = archivoFechacarga;
	}

	public String getArchivoMes() {
		return archivoMes;
	}

	public void setArchivoMes(String archivoMes) {
		this.archivoMes = archivoMes;
	}

	public String getArchivoNombre() {
		return archivoNombre;
	}

	public void setArchivoNombre(String archivoNombre) {
		this.archivoNombre = archivoNombre;
	}

	public BigDecimal getIdoperator() {
		return idoperator;
	}

	public void setIdoperator(BigDecimal idoperator) {
		this.idoperator = idoperator;
	}

	public BigDecimal getIdrol() {
		return idrol;
	}

	public void setIdrol(BigDecimal idrol) {
		this.idrol = idrol;
	}

}

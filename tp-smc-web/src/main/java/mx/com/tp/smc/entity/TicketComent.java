package mx.com.tp.smc.entity;

import java.security.Timestamp;

public class TicketComent {

	private Integer idComentario;
	private String  comentario;
	private Integer incidente;
	private String  ususario;
	private Timestamp fechaCreado;
	
	public TicketComent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketComent(Integer idComentario, String comentario, Integer incidente, String ususario,
			Timestamp fechaCreado) {
		super();
		this.idComentario = idComentario;
		this.comentario = comentario;
		this.incidente = incidente;
		this.ususario = ususario;
		this.fechaCreado = fechaCreado;
	}

	public Integer getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getIncidente() {
		return incidente;
	}

	public void setIncidente(Integer incidente) {
		this.incidente = incidente;
	}

	public String getUsusario() {
		return ususario;
	}

	public void setUsusario(String ususario) {
		this.ususario = ususario;
	}

	public Timestamp getFechaCreado() {
		return fechaCreado;
	}

	public void setFechaCreado(Timestamp fechaCreado) {
		this.fechaCreado = fechaCreado;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketComent [idComentario=");
		builder.append(idComentario);
		builder.append(", comentario=");
		builder.append(comentario);
		builder.append(", incidente=");
		builder.append(incidente);
		builder.append(", ususario=");
		builder.append(ususario);
		builder.append(", fechaCreado=");
		builder.append(fechaCreado);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}

package mx.com.tp.smc.response;

public class TicketComment {

	public Integer incidente;
	public String comentario;
	public String usuario;
	public Integer getIncidente() {
		return incidente;
	}
	public void setIncidente(Integer incidente) {
		this.incidente = incidente;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketComment [incidente=");
		builder.append(incidente);
		builder.append(", comentario=");
		builder.append(comentario);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append("]");
		return builder.toString();
	}
	
	
}

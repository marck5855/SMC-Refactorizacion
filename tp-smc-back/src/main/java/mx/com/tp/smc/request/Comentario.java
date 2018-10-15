package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Comentario {
	
	@NotNull
	@Size(min=1, max=255)
	private String refNum;
	
	@Size(min=1, max=4000)
	private String type;
	


	public Comentario() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Comentario(String refNum, String type) {
		super();
		this.refNum = refNum;
		this.type = type;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comentario [refNum=");
		builder.append(refNum);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

	
	
}

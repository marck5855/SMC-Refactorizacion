package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddComentario {

	@NotNull
	@Size(min=1, max=255)
	private String refNum;
	
	@Size(min=1, max=4000)
	private String comment;
	
	@Size(min=1, max=4000)
	private String creatorUUID;

	public AddComentario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddComentario(String refNum, String comment, String creatorUUID) {
		super();
		this.refNum = refNum;
		this.comment = comment;
		this.creatorUUID = creatorUUID;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreatorUUID() {
		return creatorUUID;
	}

	public void setCreatorUUID(String creatorUUID) {
		this.creatorUUID = creatorUUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddComentario [refNum=");
		builder.append(refNum);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", creatorUUID=");
		builder.append(creatorUUID);
		builder.append("]");
		return builder.toString();
	}
	
	
}

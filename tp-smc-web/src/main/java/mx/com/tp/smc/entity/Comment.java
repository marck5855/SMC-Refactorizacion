package mx.com.tp.smc.entity;

public class Comment {

	private String persid;
	private String description;
	private String dateInsert;
	private String callReqid;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String persid, String description, String dateInsert, String callReqid) {
		super();
		this.persid = persid;
		this.description = description;
		this.dateInsert = dateInsert;
		this.callReqid = callReqid;
	}

	public String getPersid() {
		return persid;
	}

	public void setPersid(String persid) {
		this.persid = persid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(String dateInsert) {
		this.dateInsert = dateInsert;
	}

	public String getCallReqid() {
		return callReqid;
	}

	public void setCallReqid(String callReqid) {
		this.callReqid = callReqid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [persid=");
		builder.append(persid);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateInsert=");
		builder.append(dateInsert);
		builder.append(", callReqid=");
		builder.append(callReqid);
		builder.append("]");
		return builder.toString();
	}
	
	
}

package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateStatus {


	@NotNull
	@Size(min=1, max=255)
	private String refNum;
	
	@Size(min=1, max=4000)
	private String sym;
	
	@Size(min=1, max=4000)
	private String creator;
	
	@Size(min=1, max=4000)
	private String comment;
	
	private String region;

	public UpdateStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getSym() {
		return sym;
	}

	public void setSym(String sym) {
		this.sym = sym;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	
	
}

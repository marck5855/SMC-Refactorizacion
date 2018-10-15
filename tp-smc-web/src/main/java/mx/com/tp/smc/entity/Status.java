package mx.com.tp.smc.entity;

public class Status {

	private Long idStatus;
	private Long del;
	private String persid;
	private String sym;
	private String description;
	private String code;
	private Long active;
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Status(Long idStatus, Long del, String persid, String sym, String description, String code, Long active) {
		super();
		this.idStatus = idStatus;
		this.del = del;
		this.persid = persid;
		this.sym = sym;
		this.description = description;
		this.code = code;
		this.active = active;
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public Long getDel() {
		return del;
	}

	public void setDel(Long del) {
		this.del = del;
	}

	public String getPersid() {
		return persid;
	}

	public void setPersid(String persid) {
		this.persid = persid;
	}

	public String getSym() {
		return sym;
	}

	public void setSym(String sym) {
		this.sym = sym;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getActive() {
		return active;
	}

	public void setActive(Long active) {
		this.active = active;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Status [idStatus=");
		builder.append(idStatus);
		builder.append(", del=");
		builder.append(del);
		builder.append(", persid=");
		builder.append(persid);
		builder.append(", sym=");
		builder.append(sym);
		builder.append(", description=");
		builder.append(description);
		builder.append(", code=");
		builder.append(code);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

	
}

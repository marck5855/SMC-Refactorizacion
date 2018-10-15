package mx.com.tp.smc.request;

//import javax.validation.constraints.NotNull;

public class Tenant extends Organization{

	
	private String tenant;
	private String type;

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
}

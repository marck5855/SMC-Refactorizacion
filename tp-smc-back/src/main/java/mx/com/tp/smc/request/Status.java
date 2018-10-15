package mx.com.tp.smc.request;

import javax.validation.constraints.Size;

public class Status extends Organization {

	@Size(min = 1, max = 255)
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

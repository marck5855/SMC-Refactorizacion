package mx.com.tp.smc.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRes {
	private String token;

	public String getPalabra() {
		return token;
	}

	public void setPalabra(String token) {
		this.token = token;
	}
	
	
}

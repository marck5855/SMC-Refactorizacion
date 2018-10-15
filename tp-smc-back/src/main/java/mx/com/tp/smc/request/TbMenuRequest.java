package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;

public class TbMenuRequest {

	@NotNull
	private Long idRol;

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

}

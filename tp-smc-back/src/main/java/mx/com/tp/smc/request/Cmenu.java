package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;

public class Cmenu {

	@NotNull
	private Long idMenu;

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

}


package mx.com.tp.smc.request;

import java.util.List;

import javax.validation.constraints.NotNull;

public class MenuRolAdd {

	@NotNull
	private Long idRol;

	
	private List<Long> idMenu;

	
	private List<Long> idMenuSub;

	@NotNull
	private Long idHome;

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public List<Long> getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(List<Long> idMenu) {
		this.idMenu = idMenu;
	}

	public List<Long> getIdMenuSub() {
		return idMenuSub;
	}

	public void setIdMenuSub(List<Long> idMenuSub) {
		this.idMenuSub = idMenuSub;
	}

	public Long getIdHome() {
		return idHome;
	}

	public void setIdHome(Long idHome) {
		this.idHome = idHome;
	}

}

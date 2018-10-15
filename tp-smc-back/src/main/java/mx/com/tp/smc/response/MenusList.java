
package mx.com.tp.smc.response;

import java.util.List;
import org.joda.time.Period;

public class MenusList extends ResponseCommon {

	private List<Menus> listMenuRol;

	public MenusList(boolean success, Period timeRes, String error, String mssg, int total, List<Menus> listMenuRol) {
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listMenuRol = listMenuRol;

	}

	public List<Menus> getListMenuRol() {
		return listMenuRol;
	}

	public void setListMenuRol(List<Menus> listMenuRol) {
		this.listMenuRol = listMenuRol;
	}

}

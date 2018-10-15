
package mx.com.tp.smc.response;

import java.util.List;
import org.joda.time.Period;

public class CatMenuList extends ResponseCommon {

	private List<CatMenu> listMenu;

	public CatMenuList(boolean success, Period timeRes, String error, String mssg, int total, List<CatMenu> listMenu) {
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listMenu = listMenu;

	}

	public List<CatMenu> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<CatMenu> listMenu) {
		this.listMenu = listMenu;
	}

}

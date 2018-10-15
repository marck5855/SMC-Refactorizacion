
package mx.com.tp.smc.response;

import java.util.List;
import org.joda.time.Period;

public class OrigenHomeList extends ResponseCommon {

	private List<CatOrigenHome> listOrigenHome;

	public OrigenHomeList(boolean success, Period timeRes, String error, String mssg, int total,
			List<CatOrigenHome> listOrigenHome) {
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listOrigenHome = listOrigenHome;

	}

	public List<CatOrigenHome> getListOrigenHome() {
		return listOrigenHome;
	}

	public void setListOrigenHome(List<CatOrigenHome> listOrigenHome) {
		this.listOrigenHome = listOrigenHome;
	}

}

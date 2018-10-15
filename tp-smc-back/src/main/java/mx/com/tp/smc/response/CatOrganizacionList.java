
package mx.com.tp.smc.response;

import java.util.List;
import org.joda.time.Period;

public class CatOrganizacionList extends ResponseCommon {

	private List<CatOrganizacion> listOrganization;

	public CatOrganizacionList(boolean success, Period timeRes, String error, String mssg, int total,
			List<CatOrganizacion> listOrganization) {
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listOrganization = listOrganization;

	}

	public List<CatOrganizacion> getListOrganization() {
		return listOrganization;
	}

	public void setListOrganization(List<CatOrganizacion> listOrganization) {
		this.listOrganization = listOrganization;
	}

}

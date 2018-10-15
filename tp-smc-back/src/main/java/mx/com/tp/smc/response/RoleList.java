package mx.com.tp.smc.response;

import java.util.List;
import org.joda.time.Period;

public class RoleList extends ResponseCommon {

	private List<Role> listRole;

	public RoleList(boolean success, Period timeRes, String error, String mssg, int total, List<Role> listRole) {
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listRole = listRole;

	}

	public List<Role> getListRole() {
		return listRole;
	}

	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}

}

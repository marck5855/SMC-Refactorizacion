
package mx.com.tp.smc.response;

import java.util.List;
import org.joda.time.Period;

public class LinkList extends ResponseCommon {

	private List<Link> listLinks;

	public LinkList(boolean success, Period timeRes, String error, String mssg, int total, List<Link> listLinks) {
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listLinks = listLinks;

	}

	public List<Link> getListLinks() {
		return listLinks;
	}

	public void setListLinks(List<Link> listLinks) {
		this.listLinks = listLinks;
	}

}

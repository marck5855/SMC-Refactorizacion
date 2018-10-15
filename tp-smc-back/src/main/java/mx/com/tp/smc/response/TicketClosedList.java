
package mx.com.tp.smc.response;

import java.util.List;

import org.joda.time.Period;

public class TicketClosedList extends ResponseCommon {

	private List<TicketClosed> listTicket;

	public TicketClosedList(boolean success, Period timeRes, String error, String mssg, int total,
			List<TicketClosed> listTicket) {
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listTicket = listTicket;

	}

	public List<TicketClosed> getListTicket() {
		return listTicket;
	}

	public void setListTicket(List<TicketClosed> listTicket) {
		this.listTicket = listTicket;
	}

}


package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.TicketClose;

public class TicketCloseModel extends MainModel {

	private ArrayList<TicketClose> tickets;

	public ArrayList<TicketClose> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<TicketClose> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "TicketCloseModel [tickets=" + tickets + "]";
	}

}

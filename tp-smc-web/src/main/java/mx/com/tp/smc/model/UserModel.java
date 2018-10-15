package mx.com.tp.smc.model;

import java.util.ArrayList;

//import mx.com.tp.smc.entity.Ticket;
import mx.com.tp.smc.entity.User;

public class UserModel extends MainModel {

	private ArrayList<User> tickets;

	public ArrayList<User> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<User> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "TicketModel [tickets=" + tickets + "]";
	}
}

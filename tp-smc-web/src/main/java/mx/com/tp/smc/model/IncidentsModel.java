package mx.com.tp.smc.model;
import java.util.ArrayList;

import mx.com.tp.smc.entity.Ticket;

public class IncidentsModel extends MainModel {

	private ArrayList<Ticket> tickets;

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}	
}

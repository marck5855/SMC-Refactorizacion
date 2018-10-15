package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.Ticket;
import mx.com.tp.smc.entity.TicketComent;

public class TicketModel extends MainModel {

	private ArrayList<Ticket> tickets;
	private ArrayList<TicketComent> ticketsComent;

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	public ArrayList<TicketComent> getTicketsComent() {
		return ticketsComent;
	}

	public void setTicketsComent(ArrayList<TicketComent> ticketsComent) {
		this.ticketsComent = ticketsComent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketModel [tickets=");
		builder.append(tickets);
		builder.append(", ticketsComent=");
		builder.append(ticketsComent);
		builder.append("]");
		return builder.toString();
	}

	
}

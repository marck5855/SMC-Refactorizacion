package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.Categories;
//import mx.com.tp.smc.entity.Ticket;
//import mx.com.tp.smc.entity.TicketConcilied;
//import mx.com.tp.smc.entity.User;

public class CategoriesModel extends MainModel {

	private ArrayList<Categories> tickets;

	public ArrayList<Categories> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Categories> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "TicketModel [tickets=" + tickets + "]";
	}
}

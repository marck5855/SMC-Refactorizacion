package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.OrganizacionCMDB;
import mx.com.tp.smc.entity.Organization;

public class OrganizationModel extends MainModel {

	private ArrayList<Organization> tickets;
	private ArrayList<OrganizacionCMDB> ticketscmdb;
	
	public OrganizationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganizationModel(ArrayList<Organization> tickets, ArrayList<OrganizacionCMDB> ticketscmdb) {
		super();
		this.tickets = tickets;
		this.ticketscmdb = ticketscmdb;
	}

	public ArrayList<Organization> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Organization> tickets) {
		this.tickets = tickets;
	}

	public ArrayList<OrganizacionCMDB> getTicketscmdb() {
		return ticketscmdb;
	}

	public void setTicketscmdb(ArrayList<OrganizacionCMDB> ticketscmdb) {
		this.ticketscmdb = ticketscmdb;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrganizationModel [tickets=");
		builder.append(tickets);
		builder.append(", ticketscmdb=");
		builder.append(ticketscmdb);
		builder.append("]");
		return builder.toString();
	}
	
	

	
}

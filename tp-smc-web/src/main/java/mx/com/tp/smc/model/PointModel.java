package mx.com.tp.smc.model;

import java.util.ArrayList;

//import mx.com.tp.smc.entity.Categories;
import mx.com.tp.smc.entity.Point;
import mx.com.tp.smc.entity.PointCMDB;
//import mx.com.tp.smc.entity.Ticket;
//import mx.com.tp.smc.entity.TicketConcilied;
//import mx.com.tp.smc.entity.User;

public class PointModel extends MainModel {

	private ArrayList<Point> tickets;
	private ArrayList<PointCMDB> ticketsCMDB;
	private String tenant;
	private String userId;
	private String disabled;
	private String tel;
	private String email;
	private String horario;
	private String descripcion;
	private String persona;
	
	public PointModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PointModel(ArrayList<Point> tickets, ArrayList<PointCMDB> ticketsCMDB, String tenant, String userId,
			String disabled) {
		super();
		this.tickets = tickets;
		this.ticketsCMDB = ticketsCMDB;
		this.tenant = tenant;
		this.userId = userId;
		this.disabled = disabled;
	}

	public ArrayList<Point> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Point> tickets) {
		this.tickets = tickets;
	}

	public ArrayList<PointCMDB> getTicketsCMDB() {
		return ticketsCMDB;
	}

	public void setTicketsCMDB(ArrayList<PointCMDB> ticketsCMDB) {
		this.ticketsCMDB = ticketsCMDB;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PointModel [tickets=");
		builder.append(tickets);
		builder.append(", ticketsCMDB=");
		builder.append(ticketsCMDB);
		builder.append(", tenant=");
		builder.append(tenant);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", disabled=");
		builder.append(disabled);
		builder.append("]");
		return builder.toString();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}


	
}

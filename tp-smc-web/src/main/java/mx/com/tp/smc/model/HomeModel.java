package mx.com.tp.smc.model;

public class HomeModel extends MainModel {

	private String month;
	private long closedTickets;
	private long openTickets;
	private long solvedTickets;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public long getClosedTickets() {
		return closedTickets;
	}
	public void setClosedTickets(long closedTickets) {
		this.closedTickets = closedTickets;
	}
	public long getOpenTickets() {
		return openTickets;
	}
	public void setOpenTickets(long openTickets) {
		this.openTickets = openTickets;
	}
	public long getSolvedTickets() {
		return solvedTickets;
	}
	public void setSolvedTickets(long solvedTickets) {
		this.solvedTickets = solvedTickets;
	}
}

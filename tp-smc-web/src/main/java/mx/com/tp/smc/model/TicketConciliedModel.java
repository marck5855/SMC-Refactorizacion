package mx.com.tp.smc.model;

import java.util.ArrayList;
import java.util.Arrays;

import mx.com.tp.smc.entity.TicketConcilied;

public class TicketConciliedModel extends MainModel {

	private ArrayList<TicketConcilied> tickets;
	private byte[] archivoExcel;

	public ArrayList<TicketConcilied> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<TicketConcilied> tickets) {
		this.tickets = tickets;
	}

	

	public byte[] getArchivoExcel() {
		return archivoExcel;
	}

	public void setArchivoExcel(byte[] archivoExcel) {
		this.archivoExcel = archivoExcel;
	}

	@Override
	public String toString() {
		return "TicketConciliedModel [tickets=" + tickets + ", archivoExcel=" + Arrays.toString(archivoExcel) + "]";
	}

	

}

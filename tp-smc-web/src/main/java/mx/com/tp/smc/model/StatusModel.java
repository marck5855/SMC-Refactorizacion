package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.Status;

public class StatusModel extends MainModel{

	private ArrayList<Status> status;

	public StatusModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Status> getStatus() {
		return status;
	}

	public void setStatus(ArrayList<Status> status) {
		this.status = status;
	}
	
	
}

package mx.com.tp.smc.response;

import java.util.ArrayList;

import org.joda.time.Period;

public class StatusList  extends ResponseCommon{

	private ArrayList<Status> listStatus;
	
	public StatusList(boolean success, Period timeRes,String error, String mssg,int total,ArrayList<Status> listStatus){
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listStatus = listStatus;
		
	}

	public ArrayList<Status> getListStatus() {
		return listStatus;
	}

	public void setListStatus(ArrayList<Status> listStatus) {
		this.listStatus = listStatus;
	}
	
	
}

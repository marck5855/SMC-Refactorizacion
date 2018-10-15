package mx.com.tp.smc.response;

import org.joda.time.Period;
import org.json.simple.JSONArray;

public class AddComment extends ResponseCommon{

private JSONArray data;
	
	public AddComment(boolean success, Period timeRes, String mssg, int total, String error, JSONArray data ) {
		this.success = success;
		this.timeRes = timeRes;
		this.total = total;
		this.mssg = mssg;
		this.error = error;
		this.data = data;
	}
	
	public JSONArray getTickets() {
		return data;
	}

	public void setTickets(JSONArray tickets) {
		this.data = tickets;
	}
}

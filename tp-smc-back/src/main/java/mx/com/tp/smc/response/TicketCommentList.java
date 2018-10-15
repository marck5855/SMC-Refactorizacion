package mx.com.tp.smc.response;

import java.util.List;

import org.joda.time.Period;

public class TicketCommentList  extends ResponseCommon{

	private List<TicketComment> listTicketComment;
	
	public TicketCommentList(boolean success, Period timeRes, String error, String mssg, int total, List<TicketComment> listTicketComment) {
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listTicketComment = listTicketComment;
	}

	public List<TicketComment> getListTicketComment() {
		return listTicketComment;
	}

	public void setListTicketComment(List<TicketComment> listTicketComment) {
		this.listTicketComment = listTicketComment;
	}
	
	
}

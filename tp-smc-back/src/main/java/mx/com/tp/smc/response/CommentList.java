package mx.com.tp.smc.response;

import java.util.ArrayList;

import org.joda.time.Period;

public class CommentList extends ResponseCommon{

	private ArrayList<Comment> listComment;
	
	public CommentList(boolean success, Period timeRes,String error, String mssg,int total,ArrayList<Comment> listComment){
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listComment = listComment;
		
	}

	public ArrayList<Comment> getListComment() {
		return listComment;
	}

	public void setListComment(ArrayList<Comment> listComment) {
		this.listComment = listComment;
	}
	
	
}

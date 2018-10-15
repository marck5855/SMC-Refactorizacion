package mx.com.tp.smc.response;

import java.util.List;

import org.joda.time.Period;

public class TicketConciliedList extends ResponseCommon {

		private List<TicketConcilied> listTicket;
		
		public TicketConciliedList(boolean success, Period timeRes,String error, String mssg,int total,List<TicketConcilied> listTicket){
			this.success = success;
			this.timeRes = timeRes;
			this.error = error;
			this.mssg = mssg;
			this.total = total;
			this.listTicket = listTicket;
			
		}

		public List<TicketConcilied> getListTicket() {
			return listTicket;
		}

		public void setListTicket(List<TicketConcilied> listTicket) {
			this.listTicket = listTicket;
		}
		

		
		
		
		
}

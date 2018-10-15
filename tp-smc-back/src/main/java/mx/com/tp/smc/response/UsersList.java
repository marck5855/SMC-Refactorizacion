package mx.com.tp.smc.response;

import java.util.List;

import org.joda.time.Period;

public class UsersList extends ResponseCommon {

		private List<Users> listPoint;
		
		public UsersList(boolean success, Period timeRes,String error, String mssg,int total,List<Users> listPoint){
			this.success = success;
			this.timeRes = timeRes;
			this.error = error;
			this.mssg = mssg;
			this.total = total;
			this.listPoint = listPoint;
			
		}
		

		public List<Users> getListPoint() {
			return listPoint;
		}

		public void setListPoint(List<Users> listPoint) {
			this.listPoint = listPoint;
		}
		
		
		
		
}

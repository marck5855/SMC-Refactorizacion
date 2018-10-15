package mx.com.tp.smc.response;

import java.util.List;

import org.joda.time.Period;

public class PointList extends ResponseCommon {

		private List<Point> listPoint;
		
		public PointList(boolean success, Period timeRes,String error, String mssg,int total,List<Point> listPoint){
			this.success = success;
			this.timeRes = timeRes;
			this.error = error;
			this.mssg = mssg;
			this.total = total;
			this.listPoint = listPoint;
			
		}
		

		public List<Point> getListPoint() {
			return listPoint;
		}

		public void setListPoint(List<Point> listPoint) {
			this.listPoint = listPoint;
		}
		
		
		
		
}

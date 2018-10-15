package mx.com.tp.smc.response;

import java.util.List;

import org.joda.time.Period;

public class PointListCMDB extends ResponseCommon{

	private List<PointCMDB> listPoint;
	
	public PointListCMDB(boolean success, Period timeRes,String error, String mssg,int total,List<PointCMDB> listPoint){
		this.success = success;
		this.timeRes = timeRes;
		this.error = error;
		this.mssg = mssg;
		this.total = total;
		this.listPoint = listPoint;
		
	}
	

	public List<PointCMDB> getListPoint() {
		return listPoint;
	}

	public void setListPoint(List<PointCMDB> listPoint) {
		this.listPoint = listPoint;
	}
}

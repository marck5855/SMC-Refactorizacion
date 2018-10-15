package mx.com.tp.smc.response;

import java.util.List;
import org.joda.time.Period;

public class OrganizationList extends ResponseCommon {

		private List<Organization> listOrganization;
		
		public OrganizationList(boolean success, Period timeRes,String error, String mssg,int total,List<Organization> listOrganization){
			this.success = success;
			this.timeRes = timeRes;
			this.error = error;
			this.mssg = mssg;
			this.total = total;
			this.listOrganization = listOrganization;
			
		}
		

		public List<Organization> getListOrganization() {
			return listOrganization;
		}

		public void setListOrganization(List<Organization> listOrganization) {
			this.listOrganization = listOrganization;
		}
		
		
		
		
}

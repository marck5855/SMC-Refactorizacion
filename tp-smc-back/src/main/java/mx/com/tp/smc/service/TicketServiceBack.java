package mx.com.tp.smc.service;

import java.math.BigDecimal;
import java.util.List;

import org.json.simple.JSONObject;

import mx.com.tp.smc.entity.DesService;

public interface TicketServiceBack {
	
	public JSONObject getTicketByStatus(String token, String statusId, int year, int month, String organization) throws Exception;
	public JSONObject getTicketsByOrganization(String token, int year, int month, String organization, String type)throws Exception ;
	public JSONObject getPointByOrganization(String token, String organization, String tenant)throws Exception;
	public JSONObject insertTicket(String token,String organizacion, String applicant, String category, String[] description,String regCiuedad, String insertar) throws Exception;
	public JSONObject getCategorys(String token, String bandeja) throws Exception;
	public List<DesService> getAllTicketNotConcilied(String folioStatus, String organizacion)throws Exception;
	public DesService getTicketNotConcilied(BigDecimal folioIncidencia);
	public void updateTicketNotConcilied(DesService ue);
	public List<DesService> getAllTicketClosed(String folioStatus, String organizacion);
	public JSONObject getAllTicketsClosed(String token,int month, String statusId,String type,int year) throws Exception;
	public List<DesService> getAllTickets();
	public void saveAllTicketsClosed(DesService ue);
	public List<DesService> getAllTicketNotConciliedStatusCloseAndValidate(String folioStatus1,String folioStatus2);
	public List<DesService> getByStatusCloseAndValidation(String[] status, String organization);
	public JSONObject getAddComentario(String token, String refNum, String comment, String creatorUUID)throws Exception ;
	public JSONObject getUpdateStatus(String token, String refNum, String sym , String region)throws Exception;
	
}

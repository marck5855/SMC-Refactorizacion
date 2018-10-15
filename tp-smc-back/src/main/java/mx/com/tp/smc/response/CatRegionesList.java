package mx.com.tp.smc.response;

import java.util.ArrayList;

import org.joda.time.Period;

import mx.com.tp.smc.request.Regiones;

public class CatRegionesList extends ResponseCommon{

	private ArrayList<Regiones> listRegion;

	
public CatRegionesList(boolean success, Period timeRes,String error, String mssg,int total,ArrayList<Regiones> listRegion) {
	this.success = success;
	this.timeRes = timeRes;
	this.error = error;
	this.mssg = mssg;
	this.total = total;
	this.listRegion = listRegion;
}


public ArrayList<Regiones> getListRegion() {
	return listRegion;
}


public void setListRegion(ArrayList<Regiones> listRegion) {
	this.listRegion = listRegion;
}
	
	
}


package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.CatOrganizacion;

public class CatOrganizacionModel extends MainModel {

	private ArrayList<CatOrganizacion> organizaciones;

	public ArrayList<CatOrganizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(ArrayList<CatOrganizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	@Override
	public String toString() {
		return "CatOrganizacionModel [organizaciones=" + organizaciones + "]";
	}

}

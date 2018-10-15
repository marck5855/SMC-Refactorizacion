package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.CatRegiones;

public class CatRegionesModel {

	private ArrayList<CatRegiones> regiones;

	public ArrayList<CatRegiones> getRegiones() {
		return regiones;
	}

	public void setRegiones(ArrayList<CatRegiones> regiones) {
		this.regiones = regiones;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatRegionesModel [regiones=");
		builder.append(regiones);
		builder.append("]");
		return builder.toString();
	}
	
	
}

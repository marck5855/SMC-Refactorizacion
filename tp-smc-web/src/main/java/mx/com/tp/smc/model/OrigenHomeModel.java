
package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.CatOrigenHome;

public class OrigenHomeModel extends MainModel {

	private ArrayList<CatOrigenHome> origenHome;

	public ArrayList<CatOrigenHome> getOrigenHome() {
		return origenHome;
	}

	public void setOrigenHome(ArrayList<CatOrigenHome> origenHome) {
		this.origenHome = origenHome;
	}

	@Override
	public String toString() {
		return "OrigenHomeModel [origenHome=" + origenHome + "]";
	}

}

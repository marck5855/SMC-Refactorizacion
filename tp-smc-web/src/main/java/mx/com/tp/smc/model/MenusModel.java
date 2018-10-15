
package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.Menus;

public class MenusModel extends MainModel {

	private ArrayList<Menus> menus;

	public ArrayList<Menus> getMenus() {
		return menus;
	}

	public void setMenus(ArrayList<Menus> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "MenusModel [menus=" + menus + "]";
	}

}

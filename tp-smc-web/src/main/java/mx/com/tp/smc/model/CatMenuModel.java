package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.CatMenu;

public class CatMenuModel extends MainModel {

	private ArrayList<CatMenu> menus;

	public ArrayList<CatMenu> getMenus() {
		return menus;
	}

	public void setMenus(ArrayList<CatMenu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "CatMenuModel [menus=" + menus + "]";
	}

}

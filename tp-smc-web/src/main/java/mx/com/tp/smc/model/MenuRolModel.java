
package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.TbMenuRol;

public class MenuRolModel extends MainModel {

	private ArrayList<TbMenuRol> menuRol;

	public ArrayList<TbMenuRol> getMenuRol() {
		return menuRol;
	}

	public void setMenuRol(ArrayList<TbMenuRol> menuRol) {
		this.menuRol = menuRol;
	}

	@Override
	public String toString() {
		return "MenuRolModel [menuRol=" + menuRol + "]";
	}

}

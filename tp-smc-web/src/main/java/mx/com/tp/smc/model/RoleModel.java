package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.Role;
//import mx.com.tp.smc.entity.Sla;

public class RoleModel extends MainModel {

	private ArrayList<Role> roles;
//	private ArrayList<Sla> slas;

	public ArrayList<Role> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<Role> roles) {
		this.roles = roles;
	}
	
//	public ArrayList<Sla> getSlas() {
//		return slas;
//	}
//
//	public void setSlas(ArrayList<Sla> slas) {
//		this.slas = slas;
//	}

	@Override
	public String toString() {
		return "RoleModel [roles=" + roles + "]";
	}

}

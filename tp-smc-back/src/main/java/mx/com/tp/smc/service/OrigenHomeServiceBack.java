package mx.com.tp.smc.service;

import java.util.List;

import mx.com.tp.smc.entity.CatOrigenHomeEntity;


public interface OrigenHomeServiceBack {

	List<CatOrigenHomeEntity> getAllOrigenHome();
	
	public CatOrigenHomeEntity getHome(Long idHome);

}

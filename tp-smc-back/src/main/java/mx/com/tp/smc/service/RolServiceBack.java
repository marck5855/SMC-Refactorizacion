package mx.com.tp.smc.service;

import java.math.BigDecimal;
import java.util.List;

import mx.com.tp.smc.entity.CMenusEntity;
import mx.com.tp.smc.entity.CorganizacionEntity;
import mx.com.tp.smc.entity.TrolEntity;

public interface RolServiceBack {

	public void saveRol(TrolEntity ue);

	public List<TrolEntity> getAllRol();

	List<CorganizacionEntity> getAllOrganization();

	CorganizacionEntity getRole(Long idOrganizacion);
	
	List<CMenusEntity> getPrincipalMenus();
	
	List<CMenusEntity> getSubMenu(Long idMenu);
	
	TrolEntity getRole(BigDecimal idRol);
	
	void deleteRol(TrolEntity te);

	TrolEntity getIdRole(String rolRole);
	
	CMenusEntity getMenu(Long idMenu);
}

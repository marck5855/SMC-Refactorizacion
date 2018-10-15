package mx.com.tp.smc.service;

import java.util.List;

import mx.com.tp.smc.entity.TbMenuRolEntity;

public interface TbMenuRolService {

	public List<TbMenuRolEntity> getMenusByRol(Long idRol);
	public void savePermisos(TbMenuRolEntity mr);
	public void deletePermisos(TbMenuRolEntity tmr);

}

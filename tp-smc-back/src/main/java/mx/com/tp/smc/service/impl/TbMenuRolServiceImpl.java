
package mx.com.tp.smc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.tp.smc.entity.TbMenuRolEntity;
import mx.com.tp.smc.repository.TbMenuRolRepository;
import mx.com.tp.smc.service.TbMenuRolService;

@Service
public class TbMenuRolServiceImpl implements TbMenuRolService {

	@Autowired
	private TbMenuRolRepository tbMenuRolRepository;

//	@Override
	public List<TbMenuRolEntity> getMenusByRol(Long idRol) {
		return tbMenuRolRepository.findByIdRol(idRol);
	}

//	@Override
	public void savePermisos(TbMenuRolEntity mr) {
		tbMenuRolRepository.save(mr);
	}

//	@Override
	public void deletePermisos(TbMenuRolEntity tmr) {
		tbMenuRolRepository.delete(tmr);
	}

}

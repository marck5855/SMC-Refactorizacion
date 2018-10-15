package mx.com.tp.smc.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.tp.smc.entity.ToperatorRolEntity;
//import mx.com.tp.smc.entity.TrolEntity;
import mx.com.tp.smc.repository.ToperatorRolRepository;
import mx.com.tp.smc.service.ToperatorRolService;

@Service
public class ToperatorRolServiceImpl implements ToperatorRolService {

	@Autowired
	private ToperatorRolRepository toperatorrolRepository;

//	@Override
	public void saveToperatorRol(ToperatorRolEntity to) {
		toperatorrolRepository.save(to);
	}

//	@Override
	public ToperatorRolEntity getOperatorRole(String username) {
		return toperatorrolRepository.findByUserName(username);
	}

//	@Override
	public List<ToperatorRolEntity> getIdRol(BigDecimal idRol) {
		return toperatorrolRepository.findByIdRol(idRol);
	}
	
	
//	@Override
	public void deleteRol(ToperatorRolEntity to) {
		toperatorrolRepository.delete(to);
	}

}

package mx.com.tp.smc.service;

import java.math.BigDecimal;
import java.util.List;

import mx.com.tp.smc.entity.ToperatorRolEntity;

public interface ToperatorRolService {
	
	public void saveToperatorRol(ToperatorRolEntity to);
	public ToperatorRolEntity getOperatorRole(String username);
	public List<ToperatorRolEntity> getIdRol(BigDecimal idRol);
	public void deleteRol(ToperatorRolEntity to);
}

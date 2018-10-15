package mx.com.tp.smc.service;

import java.math.BigDecimal;
import java.util.List;

import mx.com.tp.smc.entity.TrolEntity;

public interface TrolService {

	public List<TrolEntity> getAllRole();

	public TrolEntity getRole(BigDecimal idRol);

	public List<TrolEntity> getRoleByOrganizacion(String organizacion);

}

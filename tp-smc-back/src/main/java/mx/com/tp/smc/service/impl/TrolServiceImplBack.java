package mx.com.tp.smc.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.tp.smc.entity.TrolEntity;

import mx.com.tp.smc.repository.TrolRepositoryBack;
import mx.com.tp.smc.service.TrolService;

@Service
public class TrolServiceImplBack implements TrolService {

	@Autowired
	private TrolRepositoryBack trolRepositoryBack;

	@Override
	public List<TrolEntity> getAllRole() {
		return trolRepositoryBack.findAll();
	}

	@Override
	public TrolEntity getRole(BigDecimal idRol) {
		return trolRepositoryBack.findByIdRol(idRol);
	}

	@Override
	public List<TrolEntity> getRoleByOrganizacion(String organizacion) {
		return trolRepositoryBack.findByOrganizacion(organizacion);
	}

}

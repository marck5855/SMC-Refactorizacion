package mx.com.tp.smc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.tp.smc.entity.CatOrigenHomeEntity;
import mx.com.tp.smc.repository.CorigenHomeRepository;
import mx.com.tp.smc.service.OrigenHomeServiceBack;

@Service
public class OrigenHomeServiceBackImpl implements OrigenHomeServiceBack {

	@Autowired
	private CorigenHomeRepository origenHomeRepository;

//	@Override
	public List<CatOrigenHomeEntity> getAllOrigenHome() {
		return origenHomeRepository.findAll();
	}

//	@Override
	public CatOrigenHomeEntity getHome(Long idHome) {
		return origenHomeRepository.findByIdHome(idHome);
	}

}

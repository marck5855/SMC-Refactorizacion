package mx.com.tp.smc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.tp.smc.entity.CAOrganizationEntity;
import mx.com.tp.smc.repository.CAOrganizationRepository;
import mx.com.tp.smc.service.CAOrganizationService;

@Service
public class CAOrganizationServiceImpl implements CAOrganizationService {

	@Autowired
	private CAOrganizationRepository caOrganizationRepository;

	
//	@Override
	public List<CAOrganizationEntity> getAllOrganization() {
		return caOrganizationRepository.findAll();
	}

//	@Override
	public List<CAOrganizationEntity> getOrganization(String organization) {
		return caOrganizationRepository.findByOrganizationId(organization);
	}

}

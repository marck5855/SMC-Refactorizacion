package mx.com.tp.smc.service;

import java.util.List;

import mx.com.tp.smc.entity.CAOrganizationEntity;

public interface CAOrganizationService {
	
	public List<CAOrganizationEntity> getAllOrganization();
	public List<CAOrganizationEntity> getOrganization(String organizacion);
	
}

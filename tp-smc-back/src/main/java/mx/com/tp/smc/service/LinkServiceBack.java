package mx.com.tp.smc.service;

import java.util.Collection;
import java.util.List;

import mx.com.tp.smc.entity.CatLinksEntity;

public interface LinkServiceBack {

	/**
	 * @return
	 */
	public List<CatLinksEntity> getAllLink();
	
	/**
	 * @param organization
	 * @return
	 */
	public Collection<CatLinksEntity> getOrganizacionLink(String organization);
	
	/**
	 * @param cl
	 */
	void saveLink(CatLinksEntity cl);
	
	/**
	 * @param idLink
	 */
	public void deleteLink(Long idLink);
}

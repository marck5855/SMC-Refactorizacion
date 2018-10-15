package mx.com.tp.smc.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.tp.smc.entity.CatLinksEntity;
import mx.com.tp.smc.repository.CLinkRepository;
import mx.com.tp.smc.service.LinkServiceBack;

@Service
public class LinkServiceImpl implements LinkServiceBack {

	@Autowired
	private CLinkRepository cLinkRepository;

	/* (non-Javadoc)
	 * @see com.enlacetp.service.LinkService#getAllLink()
	 */
//	@override
	public List<CatLinksEntity> getAllLink() {
		return cLinkRepository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.enlacetp.service.LinkService#getOrganizacionLink(java.lang.String)
	 */
//	@Override
	public Collection<CatLinksEntity> getOrganizacionLink(String organization) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.enlacetp.service.LinkService#saveLink(com.enlacetp.persistence.model.CatLinksEntity)
	 */
//	@Override
	public void saveLink(CatLinksEntity cl) {
		cLinkRepository.save(cl);
	}
	
	/* (non-Javadoc)
	 * @see com.enlacetp.service.LinkService#deleteLink(java.lang.Long)
	 */
//	@Override
	public void deleteLink(Long idLink) {
		cLinkRepository.deleteById(idLink);
	}
}
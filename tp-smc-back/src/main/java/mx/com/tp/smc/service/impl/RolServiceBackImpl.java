package mx.com.tp.smc.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.tp.smc.entity.CMenusEntity;
import mx.com.tp.smc.entity.CorganizacionEntity;
import mx.com.tp.smc.entity.TrolEntity;
import mx.com.tp.smc.repository.CmenuRepository;
import mx.com.tp.smc.repository.CorganizacionRepository;
import mx.com.tp.smc.repository.TrolRepositoryBack;
import mx.com.tp.smc.service.RolServiceBack;

@Service
public class RolServiceBackImpl implements RolServiceBack {

	@Autowired
	private TrolRepositoryBack trolRepositoryBack;

	@Autowired
	private CorganizacionRepository corganizacionRepository;

	@Autowired
	private CmenuRepository cmenuRepository;

	@Override
	public void saveRol(TrolEntity ue) {
		trolRepositoryBack.save(ue);
	}

	@Override
	public List<TrolEntity> getAllRol() {
		return trolRepositoryBack.findAll();
	}

	@Override
	public List<CorganizacionEntity> getAllOrganization() {
		return corganizacionRepository.findAll();
	}

	@Override
	public CorganizacionEntity getRole(Long idOrganizacion) {
		return corganizacionRepository.findByIdOrganizacion(idOrganizacion);
	}

	@Override
	public List<CMenusEntity> getPrincipalMenus() {
		return cmenuRepository.findAll();
	}

	@Override
	public List<CMenusEntity> getSubMenu(Long idMenu) {
		return cmenuRepository.findByIdSubMenu(idMenu);
	}

	@Override
	public TrolEntity getRole(BigDecimal idrole) {
		return trolRepositoryBack.findByIdRol(idrole);
	}

	@Override
	public void deleteRol(TrolEntity te) {
		trolRepositoryBack.delete(te);
	}

	@Override
	public TrolEntity getIdRole(String rolRole) {
		return trolRepositoryBack.findByRolRole(rolRole);
	}

	@Override
	public CMenusEntity getMenu(Long idMenu) {
		return cmenuRepository.findByIdMenu(idMenu);
	}

}

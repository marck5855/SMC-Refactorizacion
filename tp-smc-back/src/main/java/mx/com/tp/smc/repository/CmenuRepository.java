
package mx.com.tp.smc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.tp.smc.entity.CMenusEntity;

@Repository
@Transactional
public interface CmenuRepository extends JpaRepository<CMenusEntity, Long> {

	public List<CMenusEntity> findByIdSubMenuIsNull();

	public List<CMenusEntity> findByIdSubMenu(Long idMenu);
	
	public CMenusEntity findByIdMenu(Long idMenu);

}

package mx.com.tp.smc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.tp.smc.entity.CatOrigenHomeEntity;

@Repository
@Transactional
public interface CorigenHomeRepository extends JpaRepository<CatOrigenHomeEntity, Long> {

	public CatOrigenHomeEntity findByIdHome(Long idHome);

}


package mx.com.tp.smc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.tp.smc.entity.TbMenuRolEntity;

@Repository
@Transactional
public interface TbMenuRolRepository extends JpaRepository<TbMenuRolEntity, Long> {

	public List<TbMenuRolEntity> findByIdRol(Long idrol);

}

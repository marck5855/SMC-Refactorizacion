package mx.com.tp.smc.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.tp.smc.entity.ToperatorRolEntity;

@Repository
@Transactional
public interface ToperatorRolRepository extends JpaRepository<ToperatorRolEntity, Long> {
	
	public ToperatorRolEntity findByUserName(String username);
	public List<ToperatorRolEntity> findByIdRol(BigDecimal idRol);
	
}

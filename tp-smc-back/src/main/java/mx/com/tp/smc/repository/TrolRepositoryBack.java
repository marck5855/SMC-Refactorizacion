package mx.com.tp.smc.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.tp.smc.entity.TrolEntity;

@Repository
@Transactional
public interface TrolRepositoryBack extends JpaRepository<TrolEntity, Long> {

	public TrolEntity findByIdRol(BigDecimal idrol);

	public TrolEntity findByRolRole(String role);

	List<TrolEntity> findByOrganizacion(String organizacion);

}


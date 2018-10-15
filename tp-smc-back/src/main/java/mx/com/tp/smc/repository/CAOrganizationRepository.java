package mx.com.tp.smc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.tp.smc.entity.CAOrganizationEntity;

@Repository
@Transactional
public interface CAOrganizationRepository extends JpaRepository<CAOrganizationEntity, Long> {

	List<CAOrganizationEntity> findByOrganizationId(String organization);
}

package mx.com.tp.smc.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import mx.com.tp.smc.entity.DesService;
//import mx.com.tp.smc.entity.UserEntity;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<DesService, Long> {
	
	public List<DesService> findByFolioStatusAndOrganizacion(String folioStatus,String organization);
	public List<DesService> findByFolioStatusAndFolioStatus(String folioStatus1,String folioStatus2);
	public List<DesService> findByFolioStatusInAndOrganizacion(String[] status, String organization);	
	public DesService findByFolioIncidencia(BigDecimal folioIncidencia);
	
	public List<DesService> findAll();
	
	
}

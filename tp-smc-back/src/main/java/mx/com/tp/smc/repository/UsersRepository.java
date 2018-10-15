package mx.com.tp.smc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.tp.smc.entity.UserEntity;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByUserName(String userName);

	List<UserEntity> findByOrganization(String organizacion);

	public List<UserEntity> findAllFilter(String createuser);

}

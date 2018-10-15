package mx.com.tp.smc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.tp.smc.entity.UserEntity;
import mx.com.tp.smc.repository.UsersRepository;
import mx.com.tp.smc.service.UserServiceBack;

@Service
public class UserServiceImpl implements UserServiceBack {

	@Autowired
	private UsersRepository userRepository;

//	@Override
	public void saveUser(UserEntity ue) {
		userRepository.save(ue);
	}

//	@Override
	public void deleteUser(UserEntity ue) {
		userRepository.delete(ue);
	}

//	@Override
	public UserEntity getUser(String userName) {
		return userRepository.findByUserName(userName);
	}

//	@Override
	public void updateUser(UserEntity ue) {
		userRepository.save(ue);
	}

//	@Override
	public List<UserEntity> getAllUser() {
		return userRepository.findAll();
	}
	
//	@Override
	public List<UserEntity> getAllUserFilter(String createuser) {
		return userRepository.findAllFilter(createuser);
	}

//	@Override
	public List<UserEntity> getUserByOrganizacion(String organizacion) {
		return userRepository.findByOrganization(organizacion);
	}
}

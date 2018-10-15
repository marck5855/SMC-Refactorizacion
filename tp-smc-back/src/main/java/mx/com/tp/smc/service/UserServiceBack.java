package mx.com.tp.smc.service;

import java.util.List;

import mx.com.tp.smc.entity.UserEntity;

public interface UserServiceBack {

	public void saveUser(UserEntity ue);

	public void deleteUser(UserEntity ue);

	public UserEntity getUser(String userName);

	public void updateUser(UserEntity ue);

	public List<UserEntity> getAllUser();
	
	public List<UserEntity> getUserByOrganizacion(String organizacion);

	public List<UserEntity> getAllUserFilter(String createuser);

}

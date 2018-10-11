package ers.service;

import java.util.List;

import ers.dao.Dao;
import ers.dao.UserRolesDao;
import ers.pojos.UserRoles;


public class UserRolesService {
	static Dao<UserRoles, Integer> urDao = new UserRolesDao();
	
	public static void main(String[] args) {
		String uRole = "TestRole2";
		UserRoles input = new UserRoles();
		input.setUser_role(uRole);
		urDao.save(input);
	}
	
	public UserRoles getOne(int id) {
		return urDao.getOne(id);
	}
	
	public List<UserRoles> getAll(){
		return urDao.getAll();
	}
	
	public UserRoles save(UserRoles ur) {
		return urDao.save(ur);
	}
	
	public void update(UserRoles ur) {
		urDao.update(ur);
	}
	
	public void delete(int id) {
		urDao.delete(id);
	}
}

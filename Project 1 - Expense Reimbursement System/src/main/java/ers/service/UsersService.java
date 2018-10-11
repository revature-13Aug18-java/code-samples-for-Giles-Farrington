package ers.service;

import java.util.List;

import ers.dao.Dao;
import ers.dao.UsersDao;
import ers.pojos.Users;


public class UsersService {
	static Dao<Users, Integer> uDao = new UsersDao();
	
	public static void main(String[] args) {
		String username = "testUser2";
		String password = "password";
		String firstName = "Billy";
		String lastName = "Bob";
		String email = "test2@gmail.com";
		int uRoleId = 2;
		Users input = new Users(0, username, password, firstName, lastName, email, uRoleId);
		uDao.save(input);
	}
	public Users getOne(int id) {
		return uDao.getOne(id);
	}
	
	public List<Users> getAll(){
		return uDao.getAll();
	}
	
	public Users save(Users u) {
		return uDao.save(u);
	}
	
	public void update(Users u) {
		uDao.update(u);
	}
	
	public void delete(int id) {
		uDao.delete(id);
	}
}

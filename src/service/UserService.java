package service;

import dao.UserDao;
import model.User;

public interface UserService {
	
	public UserDao getUserDao();
	
	public void setUserDao(UserDao userDao);
	
	public User validateUser(String userid);
	
	public String registerUser(User user);
	
	public String validateUpdateUser(User user);

}

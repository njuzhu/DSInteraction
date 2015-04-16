package service;

import java.util.List;

import dao.UserDao;
import model.User;

public interface UserService {
	
	public UserDao getUserDao();
	
	public void setUserDao(UserDao userDao);
	
	public User validateUser(String userid);
	
	public String registerUser(User user);
	
	public String validateUpdateUser(User user);
	
	public List<User> searchUsers(String keyword);
	
	public boolean updateUserPoint(int usr_id,int new_point);
	
	public User getUserInfo(int uid);

}

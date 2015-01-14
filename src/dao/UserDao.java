package dao;

import model.User;

public interface UserDao {
	public void save(User user);
	
	public User find(String column,String value);
	
	public void updateByUserid(User user);
}

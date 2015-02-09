package dao;

import java.util.List;

import model.User;

public interface UserDao {
	public void save(User user);
	
	public User find(String column,String value);
	
	public boolean updateByUserid(User user);
	
	public List<User> find(String keyword);
	
	public boolean updateUserPoint(int usr_id,int new_point);
}

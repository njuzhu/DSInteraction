package service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;
import service.UserService;


public class UserServiceImpl implements UserService{
	private UserDao userDao;
	
	private UserServiceImpl(){
		
	}
	public UserDao getUserDao(){
		System.out.println("getUserdao");
		return userDao;
	}
	
	@Override
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
		System.out.println("setUserdao");
		
	}
	
	public User validateUser(String name){
		User user=userDao.find("name",name);
		return user;
	}

	
	public String validateUpdateUser(User user){
		String message=null;
				
		if(user.getName().equals("")||user.getPassword().equals("")||user.getName()==null||user.getPassword()==null){
			message="All the content must be filled!<br>";
		}
		return message;
	}
	
	@Override
	public String registerUser(User user) {
		String message=null;
		if(validateUser(user.getName())!=null){
			System.out.println("username exist");
			message="username exist";
			return message;
		}
		else if(validateUpdateUser(user)!=null){
			System.out.println("All the content must be filled!");
			message="All the content must be filled!";
			return message;
		}
		else{
			userDao.save(user);
			
			return message;
		}
	}
	
}

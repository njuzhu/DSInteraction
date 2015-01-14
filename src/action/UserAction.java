package action;

import model.User;
import service.UserService;

public class UserAction extends BaseAction {

	private UserService userService;
	private User user; 
	
  
    public String execute() throws Exception{
    	String message="";
    	
		if((message=userService.registerUser(user))!= null){
			return INPUT;
		}
		else{
			this.request().getSession().setAttribute("name",user.getName());
			return SUCCESS;
		}
		
    }

	public void setUserService(UserService userService) {
		this.userService = userService;
		System.out.println("setUserService");
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUser(User user) {
		this.user = user;
		System.out.println(user.getName()+"user");
	}

	public User getUser() {
		return user;
	}
  
   

}

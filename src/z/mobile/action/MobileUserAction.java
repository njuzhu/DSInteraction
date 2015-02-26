package z.mobile.action;

import java.io.IOException;

import com.google.gson.Gson;

import z.mobile.model.MobileUser;
import z.mobile.service.MobileUserService;
import z.mobile.service.impl.MobileUserServiceImpl;

public class MobileUserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MobileUserService userService;
	
	public void register() {
		System.out.println("register.action");
		userService = new MobileUserServiceImpl();
		Gson gson = new Gson();
		String email = request().getParameter("email").toString();
		String password = request().getParameter("password").toString();
		String name = request().getParameter("name").toString();
		MobileUser mobileUser = new MobileUser(email, password, name);
		boolean result = userService.addUser(mobileUser);
		if(result) {
			mobileUser = userService.getUserByEmail(mobileUser.getEmail());
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<MobileUser>() {  
	        }.getType();  
			String mobileUserToJson = gson.toJson(mobileUser, type);
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            System.out.println(mobileUserToJson);
	            this.response().getWriter().write(mobileUserToJson);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
		} else {
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            String jsonResult = "{\"result\":\"error\"}";
	            System.out.println(jsonResult);
	            this.response().getWriter().write(jsonResult);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
		}
	}

	public void login() {
		System.out.println("login.action");
		userService = new MobileUserServiceImpl();
		Gson gson = new Gson();
		String email = request().getParameter("email").toString();
		String password = request().getParameter("password").toString();
		boolean result = userService.checkAccount(email, password);
		if(result) {   
			MobileUser mobileUser = userService.getUserByEmail(email);
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<MobileUser>() {  
	        }.getType();  
			String mobileUserToJson = gson.toJson(mobileUser, type);
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            System.out.println(mobileUserToJson);
	            this.response().getWriter().write(mobileUserToJson);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        } 
		} else {
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            String jsonResult = "{\"result\":\"error\"}";
	            System.out.println(jsonResult);
	            this.response().getWriter().write(jsonResult);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
		}
	}
	
	public void updateByName() {
		System.out.println("updateByName.action");
		userService = new MobileUserServiceImpl();
		int id = Integer.parseInt(request().getParameter("id").toString());
		String name = request().getParameter("name").toString();
		MobileUser mobileUser = userService.getUserById(id);
		boolean result = false;
		if(mobileUser != null) {
			mobileUser.setName(name);
			result = userService.updateUser(mobileUser);
		}
		if(result) {   
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            String jsonResult = "{\"result\":\"success\"}";
	            System.out.println(jsonResult);
	            this.response().getWriter().write(jsonResult);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
		} else {
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            String jsonResult = "{\"result\":\"error\"}";
	            System.out.println(jsonResult);
	            this.response().getWriter().write(jsonResult);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
		}
	}
	
	
	
}

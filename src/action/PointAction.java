package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import model.User;
import service.UserService;

public class PointAction extends BaseAction{
	private UserService userService;
	private String keyword;
	private int id;
	private int point;
	
	//根据关键字搜索用户
	public String searchUsers(){		
		List<User> users = userService.searchUsers(keyword);		
		
		this.request().getSession().setAttribute("keyword", keyword);
		this.request().getSession().setAttribute("users", users);
		
		return SUCCESS;
	}
	
	//更新用户积分
	public void updateUserPoint(){
		boolean isUpdated = userService.updateUserPoint(id, point);
		JSONArray jArray = new JSONArray();
		
		jArray.add(isUpdated);
		
		try {
			this.response().getWriter().write(jArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}

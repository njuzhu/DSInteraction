package action;

import model.Administrator;
import service.AdministratorService;

public class LoginAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdministratorService administratorService;
	private Administrator administrator;
	
	public String execute(){
	
		if(administratorService.loginAdmin(administrator)){
			return "exist";
		}
		System.out.println("用户名或密码不正确！");
		return ERROR;
	}
	public AdministratorService getAdministratorService() {
		return administratorService;
	}

	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}


}

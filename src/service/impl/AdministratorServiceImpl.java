package service.impl;

import dao.AdministratorDao;
import model.Administrator;
import service.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService{
    private AdministratorDao administratorDao;
    
	@Override
	public boolean loginAdmin(Administrator administrator) {
		// TODO Auto-generated method stub
		if(administratorDao.isExistAdmin(administrator)){
			return true;
		}
		return false;
	}

	public AdministratorDao getAdministratorDao() {
		return administratorDao;
	}

	public void setAdministratorDao(AdministratorDao administratorDao) {
		this.administratorDao = administratorDao;
	}

}

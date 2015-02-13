package z.mobile.service.impl;

import z.mobile.dao.MobileUserDao;
import z.mobile.dao.impl.MobileUserDaoImpl;
import z.mobile.model.MobileUser;
import z.mobile.service.MobileUserService;

public class MobileUserServiceImpl implements MobileUserService {
	private MobileUserDao mobileUserDao;
	
	@Override
	public boolean addUser(MobileUser mobileUser) {
		if(this.getUserByEmail(mobileUser.getEmail()) != null)
			return false;
		mobileUserDao.addMobileUser(mobileUser);
		return true;
	}

	@Override
	public boolean updateUser(MobileUser mobileUser) {
		mobileUserDao.updateMobileUser(mobileUser);
		return true;
	}

	@Override
	public MobileUser getUserById(int id) {
		mobileUserDao = new MobileUserDaoImpl();
		MobileUser user = mobileUserDao.getMobileUser("id", ""+id);
		return user;
	}

	@Override
	public MobileUser getUserByEmail(String email) {
		mobileUserDao = new MobileUserDaoImpl();
		MobileUser user = mobileUserDao.getMobileUser("email", email);
		if(user == null)
			return null;
		System.out.println(user.toString());
		return user;
	}
	
	@Override
	public boolean checkAccount(String email, String password) {
		mobileUserDao = new MobileUserDaoImpl();
		return mobileUserDao.checkAccountByEmail(email, password);
	}
}

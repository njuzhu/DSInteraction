package z.mobile.dao;

import z.mobile.model.MobileUser;

public interface MobileUserDao {
	public void addMobileUser(MobileUser mobileUser);
	
	public void updateMobileUser(MobileUser mobileUser);
	
	public MobileUser getMobileUser(String column,String value);
	
	public boolean checkAccountByEmail(String email, String password);
}

package z.mobile.service;

import z.mobile.model.MobileUser;

public interface MobileUserService {

	public boolean addUser(MobileUser mobileUser);
	public boolean updateUser(MobileUser mobileUser);
	public MobileUser getUserById(int id);
	public MobileUser getUserByEmail(String email);
	public boolean checkAccount(String email, String password);
}

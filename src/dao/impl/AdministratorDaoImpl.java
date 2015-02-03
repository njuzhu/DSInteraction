package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Administrator;
import model.User;
import dao.AdministratorDao;

public class AdministratorDaoImpl implements AdministratorDao{

	@Override
	public boolean isExistAdmin(Administrator admin) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
            String name = admin.getName();
            String password = admin.getPassword();
			String hql = "from model.Administrator as us where us.name='"+name
					+"' and us.password='"+password+"'";
			Query query = session.createQuery(hql);
			List list = query.list();

			if ((list.size()) == 0){
				System.out.println("用户名或密码不正确！");
				return false;
			}else
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

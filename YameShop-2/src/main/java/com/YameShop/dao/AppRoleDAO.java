package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.AppRole;
import com.YameShop.domain.AppUser;
import com.YameShop.domain.UserRole;
import com.YameShop.imp.AppRoleImp;

@Repository
public class AppRoleDAO implements AppRoleImp {
	 @Autowired
	 SessionFactory sessionFactory;
	@Override
	@Transactional
	public List<AppRole> findAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from App_Role";
		List<AppRole> listbrand = session.createQuery(sql).getResultList();
		return listbrand;
	}
	@Override
	public boolean Save(AppRole appRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Update(AppRole appRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Delete(AppRole appRole) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Transactional
	public List<UserRole> findByAppUser(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from  User_Role where appUser.userid ='" + id +"'" ;
		List<UserRole> list = session.createQuery(sql).getResultList();
		for (UserRole userRole : list) {
			System.err.println(userRole.getAppRole().getRoleName()+ "clm");
		}
		return list;
	}

}

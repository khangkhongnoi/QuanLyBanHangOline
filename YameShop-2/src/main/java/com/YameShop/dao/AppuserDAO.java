package com.YameShop.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.AppUser;
import com.YameShop.domain.Brand;
import com.YameShop.imp.AppuserImp;
@Repository
public class AppuserDAO implements AppuserImp {
	 @Autowired
	 SessionFactory sessionFactory;
	@Override
	@Transactional
	public List<AppUser> findAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from App_User";
		List<AppUser> listbrand = session.createQuery(sql).getResultList();
		return listbrand;
	}

	@Override
	@Transactional
	public boolean Save(AppUser appUser) {
		Session session = sessionFactory.getCurrentSession();
		session.save(appUser);
		return false;
	}

	@Override
	@Transactional
	public boolean Update(AppUser appUser) {
		Session session = sessionFactory.getCurrentSession();
		session.update(appUser);
		return false;
	}

	@Override
	@Transactional
	public List<AppUser> findByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from App_User where userName ='" + name +"'" ;
		List<AppUser> list = session.createQuery(sql).getResultList();
		return list;
	}

	@Override
	@Transactional
	public AppUser findByUserName(String name) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from App_User where userName ='" + name +"'" ;
		AppUser appUser = (AppUser) session.createQuery(sql).getSingleResult();
		if(appUser ==  null) {
			return null;
		}
		return appUser;
	}

	@Override
	@Transactional
	public AppUser finbyname(String name , String mk) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from App_User where userName ='" + name +"'" ;
		AppUser list = (AppUser) session.createQuery(sql).getSingleResult();
		return list;
	}

	@Override
	@Transactional
	public List<AppUser> findByPhone(String phone) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from App_User where Sdt ='" + phone +"'" ;
		List<AppUser> list = session.createQuery(sql).getResultList();
		return list;
	}

	@Override
	@Transactional
	public AppUser findByID(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from App_User where userid =" + id ;
		AppUser appUser = (AppUser) session.createQuery(sql).getSingleResult();
		return appUser;
	}

	@Override
	@Transactional
	public List<AppUser> findAllNotIn(String name) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM App_User where userName NOT IN ('" + name + "')";
		List<AppUser> list = session.createQuery(sql).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<AppUser> findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from App_User where userid =" + id ;
		List<AppUser> list = session.createQuery(sql).getResultList();
		return list;
	}

}

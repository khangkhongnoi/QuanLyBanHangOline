package com.YameShop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Brand;
import com.YameShop.domain.MyCounter;
import com.YameShop.imp.LuuLuongTruyCap;
@Repository
public class LuuLuongTryCaoDao implements LuuLuongTruyCap {
	 @Autowired
	 SessionFactory sessionFactory;
	@Override
	@Transactional
	public boolean Update(MyCounter myCounter) {
		Session session = sessionFactory.getCurrentSession();
		session.update(myCounter);
		return false;
	}
	@Override
	@Transactional
	public MyCounter findbyid(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from LuuLuongTruyCap where id = " + id;
		MyCounter brand = (MyCounter) session.createQuery(sql).getSingleResult();
		return brand;
	}

}

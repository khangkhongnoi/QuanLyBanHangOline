package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Import_price;
import com.YameShop.domain.Price;
import com.YameShop.domain.Promotional_price;
import com.YameShop.imp.PriceImp;

@Repository
public class PriceDAO implements PriceImp {
	@Autowired
	 SessionFactory sessionFactory;
	@Override
	public List<Price> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Price> finAllLimirDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean Save(Price price) {
		Session session = sessionFactory.getCurrentSession();
		session.save(price);
		return false;
	}

	@Override
	public boolean Update(Price price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean SavePriceKM(Promotional_price promotional_price) {
		Session session = sessionFactory.getCurrentSession();
		session.save(promotional_price);
		return false;
	}

	@Override
	@Transactional
	public boolean SaveImport(Import_price import_price) {
		Session session = sessionFactory.getCurrentSession();
		session.save(import_price);
		return false;
	}

}

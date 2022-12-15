package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Coupon_Details;
import com.YameShop.domain.Import_coupon;
import com.YameShop.domain.Product_details;
import com.YameShop.imp.Import_couponImp;
@Repository
public class Import_couponDAO implements Import_couponImp {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public boolean saves(Coupon_Details coupon_Details) {
		Session session = sessionFactory.getCurrentSession();
		session.save(coupon_Details);
		return false;
	}
	@Override
	@Transactional
	public boolean saveokr(Product_details product_details) {
		Session session = sessionFactory.getCurrentSession();
		session.save(product_details);
		return false;
	}
	@Override
	@Transactional
	public boolean save(Import_coupon import_coupon) {
		Session session = sessionFactory.getCurrentSession();
		session.save(import_coupon);
		return false;
	}
	@Override
	@Transactional
	public List<Import_coupon> finall() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from import_coupon ORDER BY Id_Coupon DESC";
		List<Import_coupon> list = session.createQuery(sql).getResultList();
		return list;
	}
	@Override
	@Transactional
	public Import_coupon findbyid(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from import_coupon where Id_Coupon = '" + id +"'";
		Import_coupon import_coupon = (Import_coupon) session.createQuery(sql).getSingleResult();
		return import_coupon;
	}
	@Override
	@Transactional
	public boolean update(Import_coupon import_coupon) {
		Session session = sessionFactory.getCurrentSession();
		session.update(import_coupon);
		return false;
	}
	@Override
	@Transactional
	public boolean updatecoupondetails(Coupon_Details coupon_Details) {
		Session session = sessionFactory.getCurrentSession();
		session.update(coupon_Details);
		return false;
	}
	@Override
	@Transactional
	public List<Coupon_Details> finbyIDcoupondetail(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from coupon_details where import_coupon.Id_Coupon = " + id ;
		List<Coupon_Details> list = session.createQuery(sql).getResultList();
		return list;
	}
	@Override
	@Transactional
	public boolean deletelist(Coupon_Details coupon_Details) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(coupon_Details);
		return false;
	}
	@Override
	@Transactional
	public Coupon_Details findbyidCT(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from coupon_details where Id_Coupon_Details = '" + id +"'";
		Coupon_Details import_coupon = (Coupon_Details) session.createQuery(sql).getSingleResult();
		return import_coupon;
	}
	@Override
	public boolean deleteimport(Import_coupon import_coupon) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(import_coupon);
		return false;
	}
	@Override
	@Transactional
	public List<Import_coupon> finallStatus(Integer status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from import_coupon where status =" + status;
		List<Import_coupon> list = session.createQuery(sql).getResultList();
		return list;
	}

}

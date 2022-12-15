package com.YameShop.dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Category;
import com.YameShop.domain.KhacHang;

import com.YameShop.imp.KhangHangimp;
@Repository
public class KhacHangDTO implements KhangHangimp {
	@Autowired
	 SessionFactory sessionFactory;
	@Override
	@Transactional
	public boolean save(KhacHang khacHang) {
		Session session = sessionFactory.getCurrentSession();
		session.save(khacHang);
		return false;
	}

	@Override
	@Transactional
	public boolean update(KhacHang khacHang) {
		Session session = sessionFactory.getCurrentSession();
		session.update(khacHang);
		return false;
	}

	@Override
	@Transactional
	public KhacHang finById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from khachang where Id_KH =" + id ;
		KhacHang khacHang = (KhacHang) session.createQuery(sql).getSingleResult();
		return khacHang;
	}

	@Override
	@Transactional
	public List<KhacHang> finAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from khachang ORDER BY Id_KH DESC";
		List<KhacHang> list = session.createQuery(sql).setMaxResults(15).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<KhacHang> sum() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from khachang ORDER BY Id_KH DESC";
		List<KhacHang> list = session.createQuery(sql).getResultList();
		return list;
	}

	@Override
	public List<KhacHang> findByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<KhacHang> findAllLimitstatus(int startingnumber, int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from khachang where ORDER BY Id_KH DESC";
		List<KhacHang> listcategory = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listcategory;
	}

	@Override
	public List<KhacHang> findAllLimitDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KhacHang> findAllLimitDongStatus(int startingnumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<KhacHang> Phone(String phone) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from khachang where phone = '" + phone +"'" ;
		List<KhacHang> list = session.createQuery(sql).getResultList();
		return list;
	}

}

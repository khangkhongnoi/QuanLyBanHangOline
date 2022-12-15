package com.YameShop.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Staff;
import com.YameShop.imp.StaffImp;

@Repository
public class StaffDAO implements StaffImp {

	@Autowired
	 SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Staff> findAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from staff where status=1 or status=0 ORDER BY Idsaff DESC";
		List<Staff> liststaff = session.createQuery(sql).setMaxResults(15).getResultList();
		return liststaff;
	}

	@Override
	@Transactional
	public List<Staff> findAllLimitDong(int startingnumber, int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from staff where status=1 or status=0 ORDER BY id DESC";
		List<Staff> liststaff = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return liststaff;
	}

	@Override
	@Transactional
	public List<Staff> findByBrandName(String brandName) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from staff where SDT ='" + brandName +"'" ;
		List<Staff> list = session.createQuery(sql).getResultList();

		return list;
	}

	@Override
	@Transactional
	public boolean Save(Staff staff) {
		Session session = sessionFactory.getCurrentSession();
		session.save(staff);
		return false;
	}

	@Override
	@Transactional
	public List<Staff> findAllLimitDongStatus(int startingnumber, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from staff sp where  sp.status=" + status + "order by sp.id desc";
		List<Staff> liststatus = session.createQuery(sql).setMaxResults(startingnumber).getResultList();
		return liststatus;
	}

	@Override
	@Transactional
	public Staff findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from staff where id = '" + id +"'";
		Staff brand = (Staff) session.createQuery(sql).getSingleResult();
		return brand;
	}

	@Override
	@Transactional
	public boolean UpdateBrand(Staff staff) {
		Session session = sessionFactory.getCurrentSession();
		session.update(staff);
		return false;
	}

	@Override
	@Transactional
	public List<Staff> findAllLimitstatus(int startingnumber, int endstart, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from staff where status=" + status + "ORDER BY id DESC";
		List<Staff> listbrand = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listbrand;
	}

	@Override
	@Transactional
	public boolean DeleteId(Staff staff) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session);
		return false;
	}

	@Override
	@Transactional
	public Optional<Staff> finbyidstaff(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	@Transactional
	public List<Staff> seach(String name) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from staff where FullName like '" + name +"%' ORDER BY Idsaff DESC";
		List<Staff> list = session.createQuery(sql).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Staff> demsumstaff() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from staff";
		List<Staff> liststaff = session.createQuery(sql).getResultList();
		return liststaff;
	}

}

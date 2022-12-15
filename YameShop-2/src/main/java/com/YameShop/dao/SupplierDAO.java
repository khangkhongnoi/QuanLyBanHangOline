package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Product;
import com.YameShop.domain.Supplier;
import com.YameShop.imp.SupplierImp;

@Repository
public class SupplierDAO implements SupplierImp {
@Autowired
SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Supplier> findAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from supplier ORDER BY id DESC";
		List<Supplier> listsupplier = session.createQuery(sql).getResultList();
		return listsupplier;
	}

	@Override
	@Transactional
	public List<Supplier> indAllLimitDong(int startingnumber, int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from supplier ORDER BY id DESC";
		List<Supplier> suppliers = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		
		return suppliers;
	}

	@Override
	@Transactional
	public boolean Save(Supplier supplier) {
		Session session = sessionFactory.getCurrentSession();
		session.save(supplier);
		return false;
	}

	@Override
	@Transactional
	public boolean Update(Supplier supplier) {
		Session session = sessionFactory.getCurrentSession();
		session.update(supplier);
		return false;
	}

	@Override
	@Transactional
	public Supplier findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from supplier where id = '" + id + "'";
		Supplier supplier = (Supplier) session.createQuery(sql).getSingleResult();
		return supplier;
	}

	@Override
	@Transactional
	public boolean Delete(Supplier supplier) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(supplier);
		return false;
	}

	@Override
	@Transactional
	public List<Supplier> findAllLimitDongStatus(int startingnumber, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from supplier sp where sp.status = " + status + "ORDER BY id DESC";
		List<Supplier> suppliers = session.createQuery(sql).setMaxResults(startingnumber).getResultList();
		return suppliers;
	}

	@Override
	@Transactional
	public List<Supplier> findBySupplierName(String suppliername) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from supplier where supplier_name = '" + suppliername + "'";
		List<Supplier> supplier = session.createQuery(sql).getResultList();
		return supplier;
	}

	@Override
	@Transactional
	public List<Supplier> findBySupplierPhone(String phone) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from supplier where phone = '" + phone + "'";
		List<Supplier> supplier = session.createQuery(sql).getResultList();
		return supplier;
	}

	@Override
	@Transactional
	public List<Supplier> search(String name, int startingnumbe, int status) {
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "from supplier where Supplier_Name like '" + name +"%' and status = " + status + "ORDER BY Id_Supplier DESC";
		List<Supplier> list = session.createQuery(sql).setMaxResults(startingnumbe).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Supplier> searchlimit(String name, int startingnumbe) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from supplier where Supplier_Name like '" + name +"%' ORDER BY Id_Supplier DESC";
		List<Supplier> list = session.createQuery(sql).setMaxResults(startingnumbe).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Supplier> findAllLimitstatus(Integer startingnumber, Integer endstart, Integer status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from supplier where status =" + status + "order by Id_Supplier desc";
		List<Supplier> listprProducts = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listprProducts;
	}

}

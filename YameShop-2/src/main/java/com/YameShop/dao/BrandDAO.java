package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Category;
import com.YameShop.imp.BrandImp;
@Repository
public class BrandDAO implements BrandImp {
 @Autowired
 SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Brand> findAllLimitDong(int startingnumber, int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from brand ORDER BY id DESC";
		List<Brand> listbrand = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listbrand;
	}

	@Override
	@Transactional
	public List<Brand> findAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from brand";
		List<Brand> listbrand = session.createQuery(sql).getResultList();
		return listbrand;
	}

	@Override
	@Transactional
	public List<Brand> findByBrandName(String brandName) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from brand where brand_name ='" + brandName +"'" ;
		List<Brand> list = session.createQuery(sql).getResultList();

		return list;
	}

	@Override
	@Transactional
	public boolean Save(Brand brand) {
		Session session = sessionFactory.getCurrentSession();
		session.save(brand);
		return false;
	}

	@Override
	@Transactional
	public List<Brand> findAllLimitDongStatus(int startingnumber, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from brand sp where  sp.status=" + status + "order by sp.id desc";
		List<Brand> liststatus = session.createQuery(sql).setMaxResults(startingnumber).getResultList();
		return liststatus;
	}

	@Override
	@Transactional
	public Brand findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from brand where id = '" + id +"'";
		Brand brand = (Brand) session.createQuery(sql).getSingleResult();
		return brand;
	}

	@Override
	@Transactional
	public boolean UpdateBrand(Brand brand) {
		Session session = sessionFactory.getCurrentSession();
		session.update(brand);
		return false;
	}

	@Override
	@Transactional
	public List<Brand> findAllLimitstatus(int startingnumber, int endstart, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from brand where status=" + status + "ORDER BY id DESC";
		List<Brand> listbrand = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listbrand;
	}

	@Override
	public boolean DeleteId(Brand brand) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(brand);
		return false;
	}

}

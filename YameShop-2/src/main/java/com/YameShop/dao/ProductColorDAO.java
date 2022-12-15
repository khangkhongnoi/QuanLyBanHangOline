package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Product_Color;
import com.YameShop.imp.ProductColorImp;

@Repository
public class ProductColorDAO implements ProductColorImp{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Product_Color> findAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product_color ORDER BY id DESC";
		List<Product_Color> listproductcolor = session.createQuery(sql).getResultList();
	
		return listproductcolor;
	}

	@Override
	@Transactional
	public List<Product_Color> findByProductColorName(String productcolorName) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product_color where color_name = '" + productcolorName +"'";
		List<Product_Color> listproductColors = session.createQuery(sql).getResultList();
		return listproductColors;
	}

	@Override
	@Transactional
	public boolean Save(Product_Color product_Color) {
		Session session = sessionFactory.getCurrentSession();
		session.save(product_Color);
		return false;
	}

	@Override
	@Transactional
	public Product_Color findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "from product_color where id = '" + id + "'";
		Product_Color product_Color = (Product_Color) session.createQuery(sqlString).getSingleResult();
		return product_Color;
	}

	@Override
	@Transactional
	public boolean UpdateProductColor(Product_Color product_Color) {
		Session session = sessionFactory.getCurrentSession();
		session.update(product_Color);
		return false;
	}

	@Override
	@Transactional
	public boolean DeleteId(Product_Color product_Color) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product_Color);
		return false;
	}

	@Override
	@Transactional
	public List<Product_Color> findAllLimitDong(int startingnumber, int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product_color ORDER BY id DESC";
		List<Product_Color> listproductColors = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		
		return listproductColors;
	}

	@Override
	@Transactional
	public List<Product_Color> findAllLimitDongStatus(int startingnumber, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "from product_color sp where sp.status = " + status + "order by id desc";
		List<Product_Color> listproductColors = session.createQuery(sqlString).setMaxResults(startingnumber).getResultList();
		
		return listproductColors;
	}

	@Override
	@Transactional
	public List<Product_Color> findAllLimitstatus(Integer startingnumber, Integer endstart, Integer status) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "from product_color where status= " + status + "ORDER BY id DESC";
		List<Product_Color> listproductColors = session.createQuery(sqlString).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		
		return listproductColors;
	}
	
}

package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Product_details;
import com.YameShop.domain.Size;
import com.YameShop.imp.ProductDetails;

@Repository
public class ProductDetailsDAO implements ProductDetails {
	 @Autowired
	 SessionFactory sessionFactory;
	@Override
	@Transactional
	public List<Product_details> findAll() {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "from product_details order by Id_Product_Details desc";
		List<Product_details> details = session.createQuery(sqlString).getResultList();
		return details;
	}
	@Override
	@Transactional
	public List<Product_details> findByAll(String idproductcolor, String idsize) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "from product_details where id_product_color= '" + idproductcolor + "' and size.Id_Size = '" + idsize + "'";
		List<Product_details> listproductDetails = session.createQuery(sqlString).getResultList();
		return listproductDetails;
	}
	@Override
	@Transactional
	public List<Product_details> finbyid(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product_details where Id_Poroduct = '" + id +"'";
		List<Product_details> product_details =  session.createQuery(sql).getResultList();
		return product_details;
	}
	@Override
	@Transactional
	public boolean Updates(Product_details product_details) {
		Session session = sessionFactory.getCurrentSession();
		session.update(product_details);
		return false;
	}
	@Override
	@Transactional
	public List<Product_details> finbuMau(String id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product_details where product_Color.Id_Product_Color = '" + id +"'";
		List<Product_details> product_details =  session.createQuery(sql).getResultList();
		return product_details;
	}


}

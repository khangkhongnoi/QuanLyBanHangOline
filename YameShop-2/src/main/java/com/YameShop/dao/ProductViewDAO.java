//package com.YameShop.dao;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.YameShop.domain.Brand;
//import com.YameShop.domain.Product;
//import com.YameShop.domain.Product_Images;
//import com.YameShop.imp.ProductViewImp;
//
//@Repository
//public class ProductViewDAO implements ProductViewImp {
//	@Autowired
//	 SessionFactory sessionFactory;
//	@Override
//	@Transactional
//	public List<Product> listproduct() {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = " from product ORDER BY Id_Poroduct DESC";
//		List<Product> listproduct = session.createQuery(sql).setMaxResults(9).getResultList();
//		return listproduct;
//	}
//	@Override
//	@Transactional
//	public List<String> listcolorprudutid(String id) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "select distinct product_Color.Id_Product_Color from product_images  where product.Id_Poroduct = '" + id +"'" ;
//		List<String> list = session.createQuery(sql).getResultList();
//		
//		return list;
//	}
//
//
//	
//}

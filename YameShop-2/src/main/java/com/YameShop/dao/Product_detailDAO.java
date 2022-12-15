package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Product_details;
import com.YameShop.imp.Product_detailsImp;
@Repository
public class Product_detailDAO implements Product_detailsImp {
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
	public List<Object> finAllTop(String idcategory) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT product_details.Id_Product_Details , product_details.product.Id_Poroduct,product_details.product.Product_Name, product_details.Image,product_details.Image1,  sum(Amount)  FROM invoice_details where product_details.product.category.Id_Category = '" + idcategory +"' AND product_details.Id_Product_Details = product_details.Id_Product_Details AND product_details.product.Id_Poroduct = product_details.product.Id_Poroduct group by product_details.Id_Product_Details order by sum(Amount) desc ";
				List<Object> list = session.createQuery(sql).getResultList();
		
		return list;
	}

	@Override
	@Transactional
	public List<Product_details> finByNewProduct(String idcategory , int limit) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " FROM product_details  where product.category.Id_Category = '" + idcategory +"' order by Id_Product_Details desc";
		List<Product_details> list = session.createQuery(sql).setMaxResults(limit).getResultList();
		return list;
	}

	@Override
	@Transactional
	public Product_details findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM product_details  where Id_Product_Details = '" + id +"'";
		Product_details product_details = (Product_details) session.createQuery(sql).getSingleResult();
		return product_details;
	}

	@Override
	@Transactional
	public List<Product_details> findbylist(Long id , String idcolor) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM product_details  where Id_Product_Details = '" + id +"' and product_Color.Id_Product_Color = '" + idcolor +"'";
		List<Product_details> list = session.createQuery(sql).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Product_details> finbylistColor(String id ,Long idproductd) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM product_details  where product.Id_Poroduct = '" + id +"' and Id_Product_Details NOT IN (" + idproductd + ")";
		List<Product_details> list = session.createQuery(sql).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Object> loandproductdeltais(String idproduct) {
		Session session = sessionFactory.getCurrentSession();
		//String sql = "SELECT DISTINCT  product_details.product_Color.Color_Name ,product_details.product_Color.Id_Product_Color  FROM product_details  where product_details.product_Color.Id_Product_Color = product_details.product_Color.Id_Product_Color AND product_details.product.Id_Poroduct = '" + idproduct+"'";
		
		String sql1 = "FROM product_details where product.Id_Poroduct = '" + idproduct+"'";
		List<Object> list = session.createQuery(sql1).getResultList();
		System.err.println(list);
		return list;
	}

	@Override
	@Transactional
	public Product_details finbyidspissizeidcolor(String idsp, String idcolor , String idsize) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM product_details  where product.Id_Poroduct = '" + idsp +"' and product_Color.Id_Product_Color = '" + idcolor +"' and size.Id_Size = '" + idsize +"'";
		System.err.println(sql);
		Product_details product_details = (Product_details) session.createQuery(sql).getSingleResult();
		return product_details;
	}

	@Override
	public boolean save(Product_details product_details) {
		Session session = sessionFactory.getCurrentSession();
		session.save(product_details);
		
		return false;
	}

	@Override
	@Transactional
	public List<Product_details> loadnhaphang(Long idctsp, String idproduct) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM product_details  where Id_Product_Details = " + idctsp +" and product.Id_Poroduct = '" + idproduct +"'";
		List<Product_details> list = session.createQuery(sql).getResultList();
		return list;
	}

	@Override
	@Transactional
	public boolean update(Product_details product_details) {
		Session session = sessionFactory.getCurrentSession();
		session.update(product_details);
		return false;
	}

	@Override
	@Transactional
	public Product_details FindbyIDProductdetails(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM product_details  where Id_Product_Details = " + id;
		System.err.println(sql);
		Product_details product_details = (Product_details) session.createQuery(sql).getSingleResult();
		return product_details;
	}

	

}

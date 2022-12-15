package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Product;
import com.YameShop.domain.Product_details;
import com.YameShop.domain.Staff;
import com.YameShop.imp.ProductImp;

@Repository
public class ProductDAO implements ProductImp {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Product> findall() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product where status = 0 or status = 1 order by id desc";
		List<Product> listproduct = session.createQuery(sql).setMaxResults(15).getResultList();
		return listproduct;
	}

	@Override
	@Transactional
	public List<Product> findByProductName(String productName) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product where product_name = '" + productName +"'";
		List<Product> listproduct = session.createQuery(sql).getResultList();
		return listproduct;
	}

	@Override
	@Transactional
	public boolean Save(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.save(product);
		return false;
	}

	@Override
	@Transactional
	public Product findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product where id = '" + id + "'";
		Product product = (Product) session.createQuery(sql).getSingleResult();
		return product;
	}

	@Override
	@Transactional
	public boolean UpdateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.update(product);
		return false;
	}

	@Override
	@Transactional
	public boolean DeleteId(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		return false;
	}

	@Override
	@Transactional
	public List<Product> findAllLimitDong(int startingnumber, int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product where status = 0 or status = 1 order by id desc";
		List<Product> listproduct = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listproduct;
	}

	@Override
	@Transactional
	public List<Product> findAllLimitDongStatus(int startingnumber, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product sp where sp.status = " + status + "order by id desc";
		List<Product> listproduct = session.createQuery(sql).setMaxResults(startingnumber).getResultList();
		return listproduct;
	}

	@Override
	@Transactional
	public List<Product> findAllLimitstatus(Integer startingnumber, Integer endstart, Integer status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product where status =" + status + "order by id desc";
		List<Product> listprProducts = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listprProducts;
	}

	@Override
	@Transactional
	public boolean savechitiet(Product_details product_details) {
		Session session = sessionFactory.getCurrentSession();
		session.save(product_details);
		return false;
	}

	@Override
	@Transactional
	public List<Product> findbyProductWhereIdcategory(String idcategory ,int limit) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product where Id_Category = '" + idcategory +"' order by id desc";
		List<Product> listproduct = session.createQuery(sql).setMaxResults(limit).getResultList();
		return listproduct;
	}

	@Override
	@Transactional
	public List<Product> sum() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product";
		List<Product> listproduct = session.createQuery(sql).getResultList();
		return listproduct;
	}

	@Override
	@Transactional
	public List<Product> search(String name ,int startingnumber ,int status) {
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "from product where Product_Name like '" + name +"%' and status = " + status + "ORDER BY Id_Poroduct DESC";
		List<Product> list = session.createQuery(sql).setMaxResults(startingnumber).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Product> searchlimit(String name, int startingnumbe) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product where Product_Name like '" + name +"%' and  status = 0 or status = 1 ORDER BY Id_Poroduct DESC";
		List<Product> list = session.createQuery(sql).setMaxResults(startingnumbe).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Product> fidbyCategory(String idcategory, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " FROM product where status = 0 and category.Id_Category = '" + idcategory +"' order by Id_Poroduct desc";
		List<Product> list = session.createQuery(sql).setMaxResults(limit).getResultList();
		return list;
	} 

	@Override
	public List<Product> listupdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Product> findByViewTop() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product order by wiewtruycap desc";
		List<Product> listproduct = session.createQuery(sql).setMaxResults(10).getResultList();
		return listproduct;
	}

	@Override
	@Transactional
	public Product loadnhaphang( String idproduct ,Long idctsp) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product where Id_Poroduct = '" + idproduct + "' and product_details.Id_Product_Details = '" + idctsp + "'";
		System.err.println(sql);
		Product product = (Product) session.createQuery(sql).getResultList();
		return product;
	}

	@Override
	@Transactional
	public List<Product> searchview(String name, int startingnumber) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product where Product_Name like '" + name +"%' and  status = 0 ORDER BY Id_Poroduct DESC";
		List<Product> list = session.createQuery(sql).setMaxResults(startingnumber).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Product> fidbyCategorybtkt(String idcategory, int startingnumber, int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " FROM product where status = 0 and category.Id_Category = '" + idcategory +"' order by Id_Poroduct desc";
		List<Product> listprProducts = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listprProducts;
	}

	@Override
	@Transactional
	public List<Product> fidbyCategoryand(String idcategory, String cate, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " FROM product where status = 0 and category.Id_Category = '" + idcategory +"' or category.Id_Category = '" + cate +"'  order by Id_Poroduct desc";
		List<Product> list = session.createQuery(sql).setMaxResults(limit).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Product> sanphamlienquan(String id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from product where Id_Poroduct NOT IN ('" + id + "')";
		List<Product> listproduct = session.createQuery(sql).setMaxResults(4).getResultList();
		return listproduct;
	}

	
	
}

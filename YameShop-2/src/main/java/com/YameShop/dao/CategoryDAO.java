package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Category;
import com.YameShop.imp.CategoryImp;

@Repository
public class CategoryDAO implements CategoryImp {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Category> findall() {
		Session session = sessionFactory.getCurrentSession();

			String sql = "from category ORDER BY id DESC";
			List<Category> listcategory = session.createQuery(sql).getResultList();

		return listcategory;
	}

	@Override
	@Transactional
	public boolean Save(Category category) {
		Session session = sessionFactory.getCurrentSession();
		   session.save(category);
		return false;
	}

	@Override
	@Transactional
	public List<Category> findByCategoName(String categoryName) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from category where category_name ='" + categoryName +"'" ;
		List<Category> list = session.createQuery(sql).getResultList();
		
		for (Category category : list) {
			System.err.println(category.getCategory_Name() + category.getId_Category());
		}
		return list;
	}

	@Override
	@Transactional
	public Category findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from category where id = '" + id +"'";
		
		Category category = (Category) session.createQuery(sql).getSingleResult();
		
		return category;
	}

	@Override
	@Transactional
	public boolean UpdateCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.update(category);
		return false;
	}

	@Override
	@Transactional  
	public List<Category> findAllLimit(int startingnumber) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from category ORDER BY id DESC";
		List<Category> listcategory = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(15).getResultList();
		return listcategory;
	}

	@Override
	public Category UpdateName(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional 
	public boolean DeleteId(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(category);
		return false;
	}

	@Override
	@Transactional
	public List<Category> findByStatus(int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from category sp where  sp.status=" + status + "order by sp.id desc";
		List<Category> liststatus = session.createQuery(sql).getResultList();
		return liststatus;
	}

	@Override
	@Transactional
	public List<Category> findAllLimitstatus(int startingnumber, int endstart, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from category where status=" + status + "ORDER BY id DESC";
		List<Category> listcategory = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listcategory;
	}

	@Override
	@Transactional
	public List<Category> findAllLimitDong(int startingnumber , int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from category ORDER BY id DESC " ;
		List<Category> listcategory = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listcategory;
	}

	@Override
	@Transactional
	public List<Category> findAllLimitDongStatus( int startingnumber, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from category sp where  sp.status=" + status + "order by sp.id desc";
		List<Category> liststatus = session.createQuery(sql).setMaxResults(startingnumber).getResultList();
		return liststatus;
	}

	@Override
	@Transactional
	public List<Category> findviewlimitstatus() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from category where status= 0";
		List<Category> listcategory = session.createQuery(sql).setMaxResults(4).getResultList();
		return listcategory;
	}



	

	
	
	
}

package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Images;
import com.YameShop.imp.ImagesImp;

@Repository
public class ImagesDAO implements ImagesImp {
	@Autowired
	 SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Images> listAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from images ORDER BY id DESC";
		List<Images> listbrand = session.createQuery(sql).setMaxResults(15).getResultList();
		return listbrand;
	}

	@Override
	public List<Images> findBySattus(Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean Save(Images images) {
		Session session = sessionFactory.getCurrentSession();
		session.save(images);
		return false;
	}

	@Override
	@Transactional
	public boolean Update(Images images) {
		Session session = sessionFactory.getCurrentSession();
		session.update(images);
		return false;
	}

	@Override
	@Transactional
	public boolean Delete(Images images) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(images);
		return false;
	}

	@Override
	@Transactional
	public List<Images>  images(String id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from images where product.Id_Poroduct = '"+ id +"'";
		List<Images>  brand = session.createQuery(sql).getResultList();
		return brand;
	}

	@Override
	@Transactional
	public Images finbuid(String id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from images where product.Id_Poroduct = '"+ id +"'";
		Images  brand = (Images) session.createQuery(sql).getResultList();
		return brand;
	}

	
}

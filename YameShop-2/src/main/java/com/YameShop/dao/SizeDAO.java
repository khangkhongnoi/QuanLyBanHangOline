package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.YameShop.domain.Size;
import com.YameShop.imp.SizeImp;

@Repository
public class SizeDAO implements SizeImp {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Size> findAllLimitDong(int startingnumber, int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from size ORDER BY id DESC";
		List<Size> listsize = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listsize;
	}

	@Override
	@Transactional
	public List<Size> findBySizeName(String sizeName) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from size where size_name ='" + sizeName +"'" ;
		List<Size> list = session.createQuery(sql).getResultList();
		return list;
	}

	@Override
	@Transactional
	public boolean Save(Size size) {
		Session session = sessionFactory.getCurrentSession();
		 session.save(size);
		return false;
	}

	@Override
	@Transactional
	public Size findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from size where id = '" + id +"'";
		Size size = (Size) session.createQuery(sql).getSingleResult();
		return size;
	}

	@Override
	@Transactional
	public boolean UpdateSize(Size size) {
		Session session = sessionFactory.getCurrentSession();
		session.update(size);
		return false;
	}

	@Override
	@Transactional
	public List<Size> findByAllSize(Integer StartHieght, Integer EndHieght, Integer StartWigh, Integer EndWigh) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from size where start_hieght = " + StartHieght + " and end_hieght = " + EndHieght + " and start_weigh = " + StartWigh + " and end_weigh =" + EndWigh;
		List<Size> listsize = session.createQuery(sql).getResultList();
		return listsize;
	}

	@Override
	@Transactional
	public List<Size> findAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from size";
		List<Size> listSizes = session.createQuery(sql).getResultList();
		return listSizes;
	}

	@Override
	@Transactional
	public boolean DeleteId(Size size) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(size);
		return false;
	}

	@Override
	@Transactional
	public List<Size> findAllLimitDongStatus(int startingnumber, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "from size sp where sp.status = " + status + "order by sp.id desc";
		List<Size> listsize = session.createQuery(sqlString).setMaxResults(startingnumber).getResultList();
		return listsize;
	}

	@Override
	@Transactional
	public List<Size> TimSize(int chieucao, int cannang) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from size where start_hieght <= " + chieucao + " and end_hieght >= " + chieucao + " and start_weigh <= " + cannang + " and end_weigh >=" + cannang;
		List<Size> listsize = session.createQuery(sql).getResultList();
		return listsize;
	}
	
}

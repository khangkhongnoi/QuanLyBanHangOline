package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Promontion;
import com.YameShop.imp.PromontionImp;
@Repository
public class PromontionDAO implements PromontionImp {
@Autowired
SessionFactory sessionFactory;
	@Override
	@Transactional
	public boolean save(Promontion promontion) {
		Session session = sessionFactory.getCurrentSession();
		session.save(promontion);
		return false;
	}
	@Override
	@Transactional
	public List<Promontion> findAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from promontion order by Id_Promontion desc";
		List<Promontion> list = session.createQuery(sql).getResultList();
		
		return list;
	}
	@Override
	@Transactional
	public List<Promontion> findAllEndTime(String date , Integer vitri) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from promontion where status = 0 and End_Time >= '" + date +"' and vitri =" + vitri ;
		
		List<Promontion> list = session.createQuery(sql).getResultList();
		
		return list;	
	}
	@Override
	@Transactional
	public Promontion finbyid(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from promontion where Id_Promontion = " + id ;
		Promontion promontion = (Promontion) session.createQuery(sql).getSingleResult();
		return promontion;
	}
	@Override
	@Transactional
	public boolean update(Promontion promontion) {
		Session session = sessionFactory.getCurrentSession();
		session.update(promontion);
		return false;
	}
	@Override
	@Transactional
	public List<Promontion> findAllLimitDong(int startingnumber, int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from promontion ORDER BY Id_Promontion DESC";
		List<Promontion> listbrand = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listbrand;
	}
	@Override
	@Transactional
	public List<Promontion> findAllLimitDongStatus(int startingnumber, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from promontion where status=" + status + "order by Id_Promontion desc";
		List<Promontion> liststatus = session.createQuery(sql).setMaxResults(startingnumber).getResultList();
		return liststatus;
	}
	@Override
	@Transactional
	public List<Promontion> findAllLimitstatus(int startingnumber, int endstart, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from promontion where status=" + status + "ORDER BY Id_Promontion DESC";
		List<Promontion> listbrand = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listbrand;
	}
	@Override
	@Transactional
	public boolean DeleteId(Promontion promontion) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(promontion);
		return false;
	}

}

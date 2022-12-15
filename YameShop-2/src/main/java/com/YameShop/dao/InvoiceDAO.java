package com.YameShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.domain.Product;
import com.YameShop.imp.InvoiceImp;
@Repository
public class InvoiceDAO implements InvoiceImp {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public boolean save(Invoice invoice) {
		Session session = sessionFactory.getCurrentSession();
		   session.save(invoice);
		return false;
	}

	@Override
	@Transactional
	public List<Invoice> findAll() {
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "from invoice order by id desc";
		List<Invoice> listinvoi = session.createQuery(sql).setMaxResults(15).getResultList();
		return listinvoi;
	}

	@Override
	@Transactional
	public List<Invoice> findAllLimitDong(int startingnumber, int endstart) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from invoice ORDER BY id DESC";
		List<Invoice> listinvoi = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listinvoi;
	}

	@Override
	@Transactional
	public List<Invoice> findAllLimitDongStatus(int startingnumber, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from invoice where  Status =" + status + "order by Id_Invoice desc";
		List<Invoice> liststatus = session.createQuery(sql).setMaxResults(startingnumber).getResultList();
		return liststatus;
	}

	@Override
	@Transactional
	public List<Invoice> findAllLimitstatus(int startingnumber, int endstart, int status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from invoice where status=" + status + "ORDER BY id DESC";
		List<Invoice> listbrand = session.createQuery(sql).setFirstResult(startingnumber).setMaxResults(endstart).getResultList();
		return listbrand;
	}

	@Override
	@Transactional
	public Invoice findbyid(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from invoice where id = " + id ;
		Invoice brand = (Invoice) session.createQuery(sql).getSingleResult();
		return brand;
	}

	@Override
	@Transactional
	public boolean update(Invoice invoice) {
		Session session = sessionFactory.getCurrentSession();
		session.update(invoice);
		return false;
	}

	@Override
	@Transactional
	public List<Invoice_details> listinvoicedetails(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from invoice_details where invoice.Id_Invoice = " + id ;
		List<Invoice_details> details = session.createQuery(sql).getResultList();
		return details;
	}



	@Override
	@Transactional
	public boolean delete(Invoice_details invoice_details) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(invoice_details);
		return false;
	}

	@Override
	@Transactional
	public Invoice_details finbyid(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from invoice_details where Id_Invoice_Details=" + id;
		Invoice_details details = (Invoice_details) session.createQuery(sql).getSingleResult();
		return details;
	}

	@Override
	@Transactional
	public List<Invoice> sum() {
Session session = sessionFactory.getCurrentSession();
		
		String sql = "from invoice";
		List<Invoice> listinvoi = session.createQuery(sql).setMaxResults(15).getResultList();
		return listinvoi;
	}

	@Override
	@Transactional
	public List<Invoice> listallstatus(Integer status) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from invoice where status = " + status ;
		List<Invoice> details = session.createQuery(sql).getResultList();
		
		return details;
	}

	@Override
	@Transactional
	public List<Invoice> search(String ten) {
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "from invoice where Customer_Name like '" + ten +"%' ORDER BY Id_Invoice DESC";
		List<Invoice> list = session.createQuery(sql).setMaxResults(15).getResultList();
		return list;

	}

	@Override
	@Transactional
	public List<Object> SumTotalManyCustone() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT sum(total_money),khacHang.Id_KH,khacHang.TenKH from invoice where khacHang.Id_KH=khacHang.Id_KH GROUP BY khacHang.Id_KH";
		List<Object> list = session.createQuery(sql).getResultList();
		System.err.println(list);
		return list;
	}

	@Override
	@Transactional
	public List<Invoice> findAlltop1() {
Session session = sessionFactory.getCurrentSession();
		
		String sql = "from invoice order by id desc";
		List<Invoice> listinvoi = session.createQuery(sql).setMaxResults(1).getResultList();
		return listinvoi;
	}

	@Override
	@Transactional
	public List<Invoice> finByidKH(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from invoice where khacHang.Id_KH = " + id ;
		List<Invoice> brand = session.createQuery(sql).getResultList();
		return brand;
	}

}

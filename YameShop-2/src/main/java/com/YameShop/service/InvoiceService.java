package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.InvoiceDAO;
import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.imp.InvoiceImp;

@Service
public class InvoiceService implements InvoiceImp {
@Autowired
InvoiceDAO invoiceDAO;
	@Override
	public boolean save(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoiceDAO.save(invoice);
	}
	@Override
	public List<Invoice> findAll() {
		// TODO Auto-generated method stub
		return invoiceDAO.findAll();
	}
	@Override
	public List<Invoice> findAllLimitDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return invoiceDAO.findAllLimitDong(startingnumber, endstart);
	}
	@Override
	public List<Invoice> findAllLimitDongStatus(int startingnumber, int status) {
		// TODO Auto-generated method stub
		return invoiceDAO.findAllLimitDongStatus(startingnumber, status);
	}
	@Override
	public List<Invoice> findAllLimitstatus(int startingnumber, int endstart, int status) {
		// TODO Auto-generated method stub
		return invoiceDAO.findAllLimitstatus(startingnumber, endstart, status);
	}
	@Override
	public Invoice findbyid(Long id) {
		// TODO Auto-generated method stub
		return invoiceDAO.findbyid(id);
	}
	@Override
	public boolean update(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoiceDAO.update(invoice);
	}
	@Override
	public List<Invoice_details> listinvoicedetails(Long id) {
		// TODO Auto-generated method stub
		return invoiceDAO.listinvoicedetails(id);
	}
	@Override
	public boolean delete(Invoice_details invoice_details) {
		// TODO Auto-generated method stub
		return invoiceDAO.delete(invoice_details);
	}
	@Override
	public Invoice_details finbyid(Long id) {
		// TODO Auto-generated method stub
		return invoiceDAO.finbyid(id);
	}
	@Override
	public List<Invoice> sum() {
		// TODO Auto-generated method stub
		return invoiceDAO.sum();
	}
	@Override
	public List<Invoice> listallstatus(Integer status) {
		// TODO Auto-generated method stub
		return invoiceDAO.listallstatus(status);
	}
	@Override
	public List<Invoice> search(String ten) {
		// TODO Auto-generated method stub
		return invoiceDAO.search(ten);
	}
	@Override
	public List<Object> SumTotalManyCustone() {
		// TODO Auto-generated method stub
		return invoiceDAO.SumTotalManyCustone();
	}
	@Override
	public List<Invoice> findAlltop1() {
		// TODO Auto-generated method stub
		return invoiceDAO.findAlltop1();
	}
	@Override
	public List<Invoice> finByidKH(Integer id) {
		// TODO Auto-generated method stub
		return invoiceDAO.finByidKH(id);
	}

}

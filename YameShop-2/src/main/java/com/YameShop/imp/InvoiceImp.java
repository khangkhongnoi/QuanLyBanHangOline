package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;

public interface InvoiceImp {
	boolean save(Invoice invoice);
	List<Invoice> findAll();
	List<Invoice> findAllLimitDong(int startingnumber, int endstart);
	List<Invoice> findAllLimitDongStatus(int startingnumber, int status);
	List<Invoice> findAllLimitstatus(int startingnumber, int endstart,int status);
	Invoice findbyid(Long id);
	boolean update(Invoice invoice);
	
	List<Invoice_details> listinvoicedetails(Long id);
	boolean delete(Invoice_details invoice_details);
	Invoice_details finbyid(Long id);
	List<Invoice> sum();
	List<Invoice> listallstatus(Integer status);
	List<Invoice> search(String ten);
	List<Object> SumTotalManyCustone();
	List<Invoice> findAlltop1();
	List<Invoice> finByidKH(Integer id);
}

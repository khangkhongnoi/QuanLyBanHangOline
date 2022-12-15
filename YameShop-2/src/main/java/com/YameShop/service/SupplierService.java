package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.SupplierDAO;
import com.YameShop.domain.Supplier;
import com.YameShop.imp.SupplierImp;
@Service
public class SupplierService implements SupplierImp {
@Autowired
SupplierDAO supplierDAO;
	@Override
	public List<Supplier> findAll() {
		// TODO Auto-generated method stub
		return supplierDAO.findAll();
	}

	@Override
	public List<Supplier> indAllLimitDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return supplierDAO.indAllLimitDong(startingnumber, endstart);
	}

	@Override
	public List<Supplier> findBySupplierName(String suppliername) {
		// TODO Auto-generated method stub
		return supplierDAO.findBySupplierName(suppliername);
	}

	@Override
	public boolean Save(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDAO.Save(supplier);
	}

	@Override
	public boolean Update(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDAO.Update(supplier);
	}

	@Override
	public Supplier findById(String id) {
		// TODO Auto-generated method stub
		return supplierDAO.findById(id);
	}

	@Override
	public boolean Delete(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDAO.Delete(supplier);
	}

	@Override
	public List<Supplier> findAllLimitDongStatus(int startingnumber, int status) {
		// TODO Auto-generated method stub
		return supplierDAO.findAllLimitDongStatus(startingnumber, status);
	}

	@Override
	public List<Supplier> findBySupplierPhone(String phone) {
		// TODO Auto-generated method stub
		return supplierDAO.findBySupplierPhone(phone);
	}

	@Override
	public List<Supplier> search(String name, int startingnumbe, int status) {
		// TODO Auto-generated method stub
		return supplierDAO.search(name, startingnumbe, status)
				;
	}

	@Override
	public List<Supplier> searchlimit(String name, int startingnumbe) {
		// TODO Auto-generated method stub
		return supplierDAO.searchlimit(name, startingnumbe)
				;
	}

	@Override
	public List<Supplier> findAllLimitstatus(Integer startingnumber, Integer endstart, Integer status) {
		// TODO Auto-generated method stub
		return supplierDAO.findAllLimitstatus(startingnumber, endstart, status);
	}

}

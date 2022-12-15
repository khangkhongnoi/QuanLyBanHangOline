package com.YameShop.imp;


import java.util.List;

import com.YameShop.domain.Product;
import com.YameShop.domain.Supplier;
public interface SupplierImp {
	List<Supplier> findAll();
	List<Supplier> indAllLimitDong(int startingnumber, int endstart);
	List<Supplier>  findBySupplierName(String suppliername);
	List<Supplier>  findBySupplierPhone(String phone);
	boolean Save(Supplier supplier);
	boolean Update(Supplier supplier);
	Supplier findById(String id);
	boolean Delete(Supplier supplier);
	List<Supplier> findAllLimitDongStatus(int startingnumber, int status);
	List<Supplier> search(String name , int startingnumbe ,int status);
	List<Supplier> searchlimit(String name , int startingnumbe);
	List<Supplier> findAllLimitstatus(Integer startingnumber, Integer endstart,Integer status);
}

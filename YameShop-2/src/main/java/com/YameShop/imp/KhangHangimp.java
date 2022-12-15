package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Category;
import com.YameShop.domain.KhacHang;

public interface KhangHangimp {
	boolean save(KhacHang khacHang);
	boolean update(KhacHang khacHang);
	KhacHang finById(Integer id);
	List<KhacHang> finAll();
	List<KhacHang> sum();
	List<KhacHang> findByStatus(int status );
	List<KhacHang> findAllLimitstatus(int startingnumber, int endstart);
	List<KhacHang> findAllLimitDong(int startingnumber, int endstart);
	List<KhacHang> findAllLimitDongStatus(int startingnumber);
	List<KhacHang> Phone(String phone);
}

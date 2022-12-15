package com.YameShop.imp;

import java.util.List;
import java.util.Optional;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Staff;

public interface StaffImp {
	List<Staff> findAll();
	List<Staff> findAllLimitDong(int startingnumber, int endstart);
	List<Staff> findByBrandName(String brandName);
	boolean Save(Staff staff);
	List<Staff> findAllLimitDongStatus(int startingnumber, int status);
	Staff findById(Long id);
	boolean UpdateBrand(Staff staff);
	List<Staff> findAllLimitstatus(int startingnumber, int endstart,int status);
	boolean DeleteId(Staff staff);
	Optional<Staff> finbyidstaff(Long id);
	List<Staff> seach (String name);
	List<Staff> demsumstaff();
}

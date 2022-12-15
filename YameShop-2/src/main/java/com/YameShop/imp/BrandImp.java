package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Category;

public interface BrandImp {
	List<Brand> findAll();
	List<Brand> findAllLimitDong(int startingnumber, int endstart);
	List<Brand> findByBrandName(String brandName);
	boolean Save(Brand brand);
	List<Brand> findAllLimitDongStatus(int startingnumber, int status);
	Brand findById(String id);
	boolean UpdateBrand(Brand brand);
	List<Brand> findAllLimitstatus(int startingnumber, int endstart,int status);
	boolean DeleteId(Brand brand);
}

package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Coupon_Details;
import com.YameShop.domain.Import_coupon;
import com.YameShop.domain.Product_details;

public interface Import_couponImp {
	boolean save(Import_coupon import_coupon);
	boolean saves(Coupon_Details coupon_Details);
	boolean saveokr(Product_details product_details);
	List<Import_coupon> finall();
	Import_coupon findbyid(Integer id);
	boolean update(Import_coupon import_coupon);
	boolean updatecoupondetails(Coupon_Details coupon_Details);
	List<Coupon_Details> finbyIDcoupondetail(Integer id);
	boolean deletelist(Coupon_Details coupon_Details);
	Coupon_Details findbyidCT(Integer id);
	boolean deleteimport(Import_coupon import_coupon);
	
	List<Import_coupon> finallStatus(Integer status);
}

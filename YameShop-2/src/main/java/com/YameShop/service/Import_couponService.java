package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.YameShop.dao.Import_couponDAO;
import com.YameShop.domain.Coupon_Details;
import com.YameShop.domain.Import_coupon;
import com.YameShop.domain.Product_details;
import com.YameShop.imp.Import_couponImp;
@Service
public class Import_couponService implements Import_couponImp {
@Autowired
Import_couponDAO import_couponDAO;



@Override

public boolean saves(Coupon_Details coupon_Details) {
	// TODO Auto-generated method stub
	return import_couponDAO.saves(coupon_Details);
}

@Override
public boolean saveokr(Product_details product_details) {
	// TODO Auto-generated method stub
	return import_couponDAO.saveokr(product_details);
}

@Override
public boolean save(Import_coupon import_coupon) {
	// TODO Auto-generated method stub
	return import_couponDAO.save(import_coupon);
}

@Override
public List<Import_coupon> finall() {
	// TODO Auto-generated method stub
	return import_couponDAO.finall();
}

@Override
public Import_coupon findbyid(Integer id) {
	// TODO Auto-generated method stub
	return import_couponDAO.findbyid(id);
}

@Override
public boolean update(Import_coupon import_coupon) {
	// TODO Auto-generated method stub
	return import_couponDAO.update(import_coupon);
}

@Override
public boolean updatecoupondetails(Coupon_Details coupon_Details) {
	// TODO Auto-generated method stub
	return import_couponDAO.updatecoupondetails(coupon_Details);
}

@Override
public List<Coupon_Details> finbyIDcoupondetail(Integer id) {
	// TODO Auto-generated method stub
	return import_couponDAO.finbyIDcoupondetail(id);
}

@Override
public boolean deletelist(Coupon_Details coupon_Details) {
	// TODO Auto-generated method stub
	return import_couponDAO.deletelist(coupon_Details);
}

@Override
public Coupon_Details findbyidCT(Integer id) {
	// TODO Auto-generated method stub
	return import_couponDAO.findbyidCT(id);
}

@Override
public boolean deleteimport(Import_coupon import_coupon) {
	// TODO Auto-generated method stub
	return import_couponDAO.deleteimport(import_coupon);
}

@Override
public List<Import_coupon> finallStatus(Integer status) {
	// TODO Auto-generated method stub
	return import_couponDAO.finallStatus(status);
	
}
	

}

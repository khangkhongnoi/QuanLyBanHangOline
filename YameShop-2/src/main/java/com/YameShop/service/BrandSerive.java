package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.BrandDAO;
import com.YameShop.domain.Brand;
import com.YameShop.imp.BrandImp;

@Service
public class BrandSerive implements BrandImp {
@Autowired
BrandDAO brandDAO;
	
	@Override
	public List<Brand> findAllLimitDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return brandDAO.findAllLimitDong( startingnumber,  endstart);
	}

	@Override
	public List<Brand> findAll() {
		// TODO Auto-generated method stub
		return brandDAO.findAll();
	}

	@Override
	public List<Brand> findByBrandName(String brandName) {
		// TODO Auto-generated method stub
		return brandDAO.findByBrandName(brandName);
	}

	@Override
	public boolean Save(Brand brand) {
		// TODO Auto-generated method stub
		return brandDAO.Save(brand);
	}

	@Override
	public List<Brand> findAllLimitDongStatus(int startingnumber, int status) {
		// TODO Auto-generated method stub
		return brandDAO.findAllLimitDongStatus(startingnumber, status);
	}

	@Override
	public Brand findById(String id) {
		// TODO Auto-generated method stub
		return brandDAO.findById(id);
	}

	@Override
	public boolean UpdateBrand(Brand brand) {
		// TODO Auto-generated method stub
		return brandDAO.UpdateBrand(brand);
	}

	@Override
	public List<Brand> findAllLimitstatus(int startingnumber, int endstart, int status) {
		// TODO Auto-generated method stub
		return brandDAO.findAllLimitstatus(startingnumber, endstart, status);
	}

	@Override
	public boolean DeleteId(Brand brand) {
		// TODO Auto-generated method stub
		return brandDAO.DeleteId(brand);
	}

}

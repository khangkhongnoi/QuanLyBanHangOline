package com.YameShop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.StaffDAO;
import com.YameShop.domain.Staff;
import com.YameShop.imp.StaffImp;

@Service
public class StaffService implements StaffImp {
@Autowired
StaffDAO staffDAO;
	@Override
	public List<Staff> findAll() {
		// TODO Auto-generated method stub
		return staffDAO.findAll();
	}

	@Override
	public List<Staff> findAllLimitDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return staffDAO.findAllLimitDong(startingnumber, endstart);
	}

	@Override
	public List<Staff> findByBrandName(String brandName) {
		// TODO Auto-generated method stub
		return staffDAO.findByBrandName(brandName);
	}

	@Override
	public boolean Save(Staff staff) {
		// TODO Auto-generated method stub
		return staffDAO.Save(staff);
	}

	@Override
	public List<Staff> findAllLimitDongStatus(int startingnumber, int status) {
		// TODO Auto-generated method stub
		return staffDAO.findAllLimitDongStatus(startingnumber, status);
	}

	@Override
	public Staff findById(Long id) {
		// TODO Auto-generated method stub
		return staffDAO.findById(id);
	}

	@Override
	public boolean UpdateBrand(Staff staff) {
		// TODO Auto-generated method stub
		return staffDAO.UpdateBrand(staff);
	}

	@Override
	public List<Staff> findAllLimitstatus(int startingnumber, int endstart, int status) {
		// TODO Auto-generated method stub
		return staffDAO.findAllLimitstatus(startingnumber, endstart, status);
	}

	@Override
	public boolean DeleteId(Staff staff) {
		// TODO Auto-generated method stub
		return staffDAO.DeleteId(staff);
	}

	@Override
	public Optional<Staff> finbyidstaff(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Staff> seach(String name) {
		// TODO Auto-generated method stub
		return staffDAO.seach(name);
	}

	@Override
	public List<Staff> demsumstaff() {
		// TODO Auto-generated method stub
		return staffDAO.demsumstaff();
	}

}

package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.SizeDAO;
import com.YameShop.domain.Size;
import com.YameShop.imp.SizeImp;

@Service
public class SizeService implements SizeImp {
	@Autowired
	SizeDAO sizeDAO;
	@Override
	public List<Size> findAllLimitDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return sizeDAO.findAllLimitDong(startingnumber, endstart);
	}
	@Override
	public List<Size> findBySizeName(String sizeName) {
		// TODO Auto-generated method stub
		return sizeDAO.findBySizeName(sizeName);
	}
	@Override
	public boolean Save(Size size) {
		// TODO Auto-generated method stub
		return sizeDAO.Save(size);
	}
	@Override
	public Size findById(String id) {
		// TODO Auto-generated method stub
		return sizeDAO.findById(id);
	}
	@Override
	public boolean UpdateSize(Size size) {
		// TODO Auto-generated method stub
		return sizeDAO.UpdateSize(size);
	}
	@Override
	public List<Size> findByAllSize(Integer StartHieght, Integer EndHieght, Integer StartWigh, Integer EndWigh) {
		// TODO Auto-generated method stub
		return sizeDAO.findByAllSize(StartHieght, EndHieght, StartWigh, EndWigh);
	}
	@Override
	public List<Size> findAll() {
		// TODO Auto-generated method stub
		return sizeDAO.findAll();
	}
	@Override
	public boolean DeleteId(Size size) {
		// TODO Auto-generated method stub
		return sizeDAO.DeleteId(size);
	}
	@Override
	public List<Size> findAllLimitDongStatus(int startingnumber, int status) {
		// TODO Auto-generated method stub
		return sizeDAO.findAllLimitDongStatus(startingnumber, status);
	}
	@Override
	public List<Size> TimSize(int chieucao, int cannang) {
		// TODO Auto-generated method stub
		return sizeDAO.TimSize(chieucao, cannang);
	}
	
	
}

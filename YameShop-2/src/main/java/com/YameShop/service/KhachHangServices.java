package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.KhacHangDTO;
import com.YameShop.domain.KhacHang;
import com.YameShop.imp.KhangHangimp;
@Service
public class KhachHangServices implements KhangHangimp {
@Autowired
KhacHangDTO khacHangDTO;
	@Override
	public boolean save(KhacHang khacHang) {
		// TODO Auto-generated method stub
		return khacHangDTO.save(khacHang);
	}

	@Override
	public boolean update(KhacHang khacHang) {
		// TODO Auto-generated method stub
		return khacHangDTO.update(khacHang);
	}

	@Override
	public KhacHang finById(Integer id) {
		// TODO Auto-generated method stub
		return khacHangDTO.finById(id);
	}

	@Override
	public List<KhacHang> finAll() {
		// TODO Auto-generated method stub
		return khacHangDTO.finAll();
	}

	@Override
	public List<KhacHang> sum() {
		// TODO Auto-generated method stub
		return khacHangDTO.sum();
	}

	@Override
	public List<KhacHang> findByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KhacHang> findAllLimitstatus(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return khacHangDTO.findAllLimitDong(startingnumber, endstart);
	}

	@Override
	public List<KhacHang> findAllLimitDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KhacHang> findAllLimitDongStatus(int startingnumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KhacHang> Phone(String phone) {
		// TODO Auto-generated method stub
		return khacHangDTO.Phone(phone);
	}

}

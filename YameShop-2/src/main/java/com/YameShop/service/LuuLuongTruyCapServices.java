package com.YameShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.YameShop.dao.LuuLuongTryCaoDao;
import com.YameShop.domain.MyCounter;
import com.YameShop.imp.LuuLuongTruyCap;
@Service
public class LuuLuongTruyCapServices implements LuuLuongTruyCap {
@Autowired
LuuLuongTryCaoDao luongTryCaoDao;
	@Override
	public boolean Update(MyCounter myCounter) {
		// TODO Auto-generated method stub
		return luongTryCaoDao.Update(myCounter);
	}
	@Override
	public MyCounter findbyid(Integer id) {
		// TODO Auto-generated method stub
		return luongTryCaoDao.findbyid(id);
	}

}

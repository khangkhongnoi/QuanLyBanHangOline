package com.YameShop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.YameShop.dao.AppuserDAO;
import com.YameShop.domain.AppUser;
import com.YameShop.imp.AppuserImp;
@Service
public class AccountSerive implements AppuserImp{
@Autowired
AppuserDAO appuserDAO;

	@Override
	public List<AppUser> findAll() {
		// TODO Auto-generated method stub
		return appuserDAO.findAll();
	}

	@Override
	public boolean Save(AppUser appUser) {
		// TODO Auto-generated method stub
		return appuserDAO.Save(appUser);
	}

	@Override
	public boolean Update(AppUser appUser) {
		// TODO Auto-generated method stub
		return appuserDAO.Update(appUser);
	}

	@Override
	public List<AppUser> findByName(String name) {
		// TODO Auto-generated method stub
		return appuserDAO.findByName(name);
	}

	@Override
	public AppUser findByUserName(String name) {
		// TODO Auto-generated method stub
		return appuserDAO.findByUserName(name);
	}

	@Override
	public AppUser finbyname(String name, String mk) {
//		AppUser list = finbyname(name, mk);
//		if( bCryptPasswordEncoder.matches(list.getPassword(), mk) && list.getUserName() != "" ) {
//			list.setPassword("");
//			return list;
//		}
		return appuserDAO.finbyname(name, mk);
	}

	@Override
	public List<AppUser> findByPhone(String phone) {
		// TODO Auto-generated method stub
		return appuserDAO.findByPhone(phone);
	}

	@Override
	public AppUser findByID(Long id) {
		// TODO Auto-generated method stub
		return appuserDAO.findByID(id);
	}

	@Override
	public List<AppUser> findAllNotIn(String name) {
		// TODO Auto-generated method stub
		return appuserDAO.findAllNotIn(name);
	}

	@Override
	public List<AppUser> findById(Long id) {
		// TODO Auto-generated method stub
		return appuserDAO.findById(id);
	}



}

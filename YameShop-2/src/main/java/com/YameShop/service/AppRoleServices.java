package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.AppRoleDAO;
import com.YameShop.domain.AppRole;
import com.YameShop.domain.AppUser;
import com.YameShop.domain.UserRole;
import com.YameShop.imp.AppRoleImp;

@Service
public class AppRoleServices implements AppRoleImp {
@Autowired
AppRoleDAO appRoleDAO;
	@Override
	public List<AppRole> findAll() {
		// TODO Auto-generated method stub
		return appRoleDAO.findAll();
	}

	@Override
	public boolean Save(AppRole appRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Update(AppRole appRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Delete(AppRole appRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserRole> findByAppUser(Long id) {
		// TODO Auto-generated method stub
		return appRoleDAO.findByAppUser(id);
	}
	
}

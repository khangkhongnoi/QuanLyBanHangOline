package com.YameShop.imp;

import java.util.List;
import java.util.Optional;

import com.YameShop.domain.AppUser;

public interface AppuserImp {
	List<AppUser> findAll();
	boolean Save(AppUser appUser);
	boolean Update(AppUser appUser);
	List<AppUser> findByName(String name);
	AppUser findByUserName(String name);
	AppUser finbyname (String name, String mk);
	List<AppUser> findByPhone(String phone);
	AppUser findByID(Long id);
	List<AppUser> findById(Long id);
	List<AppUser> findAllNotIn(String name);
}

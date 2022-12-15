package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.AppRole;
import com.YameShop.domain.AppUser;
import com.YameShop.domain.UserRole;

public interface AppRoleImp {
	List<AppRole> findAll();
	boolean Save(AppRole appRole);
	boolean Update(AppRole appRole);
	boolean Delete(AppRole appRole);
	
	List<UserRole> findByAppUser(Long id);
}

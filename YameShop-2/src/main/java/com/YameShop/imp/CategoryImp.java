package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Category;

public interface CategoryImp {
	List<Category> findall();
	boolean Save(Category category);
	List<Category> findByCategoName(String categoryName);
	Category findById(String id);
	boolean UpdateCategory(Category category);
	List<Category> findAllLimit(int startingnumber);
	Category UpdateName(Integer id);
	boolean DeleteId(Category category);
	List<Category> findByStatus(int status );
	List<Category> findAllLimitstatus(int startingnumber, int endstart,int status);
	List<Category> findAllLimitDong(int startingnumber, int endstart);
	List<Category> findAllLimitDongStatus(int startingnumber, int status);
	List<Category> findviewlimitstatus();
}

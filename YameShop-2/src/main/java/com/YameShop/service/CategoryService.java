package com.YameShop.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.CategoryDAO;
import com.YameShop.domain.Category;
import com.YameShop.imp.CategoryImp;

@Service
public class CategoryService implements CategoryImp{

	@Autowired
	CategoryDAO categoryDAO;

	@Override
	public List<Category> findall() {
	

		return categoryDAO.findall();
	}

	@Override
	public boolean Save(Category category) {
		categoryDAO.Save(category);
		return false;
	}

	@Override
	public List<Category> findByCategoName(String categoryName) {
		// TODO Auto-generated method stub
		return categoryDAO.findByCategoName(categoryName);
	}

	@Override
	public Category findById(String id) {
		
		return categoryDAO.findById(id);
	}

	@Override
	public boolean UpdateCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDAO.UpdateCategory(category);
	}

	@Override
	public List<Category> findAllLimit(int startingnumber) {
		// TODO Auto-generated method stub
		return categoryDAO.findAllLimit(startingnumber);
	}

	@Override
	public Category UpdateName(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean DeleteId(Category category) {
		// TODO Auto-generated method stub
		return categoryDAO.DeleteId(category);
	}

	@Override
	public List<Category> findByStatus(int status) {
		// TODO Auto-generated method stub
		return categoryDAO.findByStatus(status);
	}

	@Override
	public List<Category> findAllLimitstatus(int startingnumber, int endstart,int status) {
		// TODO Auto-generated method stub
		return categoryDAO.findAllLimitstatus(startingnumber,endstart, status);
	}

	@Override
	public List<Category> findAllLimitDong(int startingnumber , int endstart) {
		// TODO Auto-generated method stub
		return categoryDAO.findAllLimitDong(startingnumber, endstart);
	}

	@Override
	public List<Category> findAllLimitDongStatus( int startingnumber, int status) {
		// TODO Auto-generated method stub
		return categoryDAO.findAllLimitDongStatus(startingnumber, status);
	}

	@Override
	public List<Category> findviewlimitstatus() {
		// TODO Auto-generated method stub
		return categoryDAO.findviewlimitstatus();
	}

	
	
	
	
}

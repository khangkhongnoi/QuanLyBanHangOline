package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.YameShop.dao.ImagesDAO;
import com.YameShop.domain.Images;

import com.YameShop.imp.ImagesImp;
@Service
public class ImagesServices implements ImagesImp {
@Autowired
ImagesDAO imagesDAO;

@Override
public List<Images> listAll() {
	// TODO Auto-generated method stub
	return imagesDAO.listAll();
}

@Override
public List<Images> findBySattus(Integer status) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean Save(Images images) {
	// TODO Auto-generated method stub
	return imagesDAO.Save(images);
}

@Override
public boolean Update(Images images) {
	// TODO Auto-generated method stub
	return imagesDAO.Update(images);
}

@Override
public boolean Delete(Images images) {
	// TODO Auto-generated method stub
	return imagesDAO.Delete(images);
}

@Override
public List<Images>  images(String id) {
	// TODO Auto-generated method stub
	return imagesDAO.images(id);
}

@Override
public Images finbuid(String id) {
	// TODO Auto-generated method stub
	return imagesDAO.finbuid(id);
}


	

}

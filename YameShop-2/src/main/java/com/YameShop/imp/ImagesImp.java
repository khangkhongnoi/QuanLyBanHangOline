package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Images;

public interface ImagesImp {
	List<Images> listAll();
	List<Images> findBySattus(Integer status);
	boolean Save(Images images);
	boolean Update(Images images);
	boolean Delete(Images images);
	List<Images>  images(String id);
	Images finbuid (String id);
}

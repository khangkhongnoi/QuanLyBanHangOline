package com.YameShop.imp;

import java.util.List;


import com.YameShop.domain.Size;

public interface SizeImp {
		List<Size> findAll();
		List<Size> findAllLimitDong(int startingnumber, int endstart);
		List<Size> findBySizeName(String sizeName);
		boolean Save(Size size);
		Size findById(String id);
		boolean UpdateSize(Size size);
		List<Size> findByAllSize(Integer StartHieght , Integer EndHieght, Integer StartWigh, Integer EndWigh);
		boolean DeleteId(Size size);
		List<Size> findAllLimitDongStatus(int startingnumber, int status);
		
		List<Size> TimSize ( int chieucao, int cannang);
}

package com.YameShop.imp;

import java.util.List;


import com.YameShop.domain.Promontion;

public interface PromontionImp {
	boolean save(Promontion promontion);
	List<Promontion> findAll();
	List<Promontion> findAllEndTime(String date , Integer vitri);
	Promontion finbyid(Long id);
	boolean update(Promontion promontion);
	List<Promontion> findAllLimitDong(int startingnumber, int endstart);
	List<Promontion> findAllLimitDongStatus(int startingnumber, int status);
	List<Promontion> findAllLimitstatus(int startingnumber, int endstart,int status);
	boolean DeleteId(Promontion promontion);
}

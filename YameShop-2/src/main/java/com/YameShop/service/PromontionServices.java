package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.PromontionDAO;
import com.YameShop.domain.Promontion;
import com.YameShop.imp.PromontionImp;
@Service
public class PromontionServices  implements PromontionImp{
@Autowired
PromontionDAO promontionDAO;
	@Override
	public boolean save(Promontion promontion) {
		// TODO Auto-generated method stub
		return promontionDAO.save(promontion);
	}
	@Override
	public List<Promontion> findAll() {
		// TODO Auto-generated method stub
		return promontionDAO.findAll();
	}
	@Override
	public List<Promontion> findAllEndTime(String date , Integer vitri) {
		// TODO Auto-generated method stub
		return promontionDAO.findAllEndTime(date,vitri);
	}
	@Override
	public Promontion finbyid(Long id) {
		// TODO Auto-generated method stub
		return promontionDAO.finbyid(id);
	}
	@Override
	public boolean update(Promontion promontion) {
		// TODO Auto-generated method stub
		return promontionDAO.update(promontion);
	}
	@Override
	public List<Promontion> findAllLimitDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return promontionDAO.findAllLimitDong(startingnumber, endstart);
	}
	@Override
	public List<Promontion> findAllLimitDongStatus(int startingnumber, int status) {
		// TODO Auto-generated method stub
		return promontionDAO.findAllLimitDongStatus(startingnumber, status);
	}
	@Override
	public List<Promontion> findAllLimitstatus(int startingnumber, int endstart, int status) {
		// TODO Auto-generated method stub
		return promontionDAO.findAllLimitstatus(startingnumber, endstart, status);
	}
	@Override
	public boolean DeleteId(Promontion promontion) {
		// TODO Auto-generated method stub
		return promontionDAO.DeleteId(promontion);
	}

}

package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.PriceDAO;
import com.YameShop.domain.Import_price;
import com.YameShop.domain.Price;
import com.YameShop.domain.Promotional_price;
import com.YameShop.imp.PriceImp;

@Service
public class PriceServices implements PriceImp {
@Autowired
PriceDAO priceDAO;
	@Override
	public List<Price> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Price> finAllLimirDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Save(Price price) {
		// TODO Auto-generated method stub
		return priceDAO.Save(price);
	}

	@Override
	public boolean Update(Price price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean SavePriceKM(Promotional_price promotional_price) {
		// TODO Auto-generated method stub
		return priceDAO.SavePriceKM(promotional_price);
	}

	@Override
	public boolean SaveImport(Import_price import_price) {
		// TODO Auto-generated method stub
		return priceDAO.SaveImport(import_price);
	}

}

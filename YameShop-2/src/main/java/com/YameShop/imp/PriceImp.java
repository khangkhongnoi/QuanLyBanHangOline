package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Import_price;
import com.YameShop.domain.Price;
import com.YameShop.domain.Promotional_price;

public interface PriceImp {
	List<Price> findAll();
	List<Price> finAllLimirDong(int startingnumber , int endstart);
	boolean Save(Price price);
	boolean Update(Price price);
	boolean SavePriceKM(Promotional_price promotional_price);
	boolean SaveImport(Import_price import_price);
}

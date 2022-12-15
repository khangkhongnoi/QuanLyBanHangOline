package com.YameShop.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.ReportNgayDao;
import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.domain.MyItem;
import com.YameShop.imp.RepoertNgay;

@Service
public class ReportService implements RepoertNgay {
	@Autowired
	ReportNgayDao reportNgayDao;
	@Override
	public List<MyItem> reportNgay(Date data, int limit) {
		// TODO Auto-generated method stub
		return reportNgayDao.reportNgay(data, limit);
	}
	@Override
	public List<Invoice> finall() {
		// TODO Auto-generated method stub
		return reportNgayDao.finall();
	}
	@Override
	public int finbyngay(String date) {
		// TODO Auto-generated method stub
		return reportNgayDao.finbyngay(date);
	}
	@Override
	public List<Object> finbyMonth(int sonam) {
		// TODO Auto-generated method stub
		return reportNgayDao.finbyMonth(sonam);
	}
	@Override
	public List<Object> finbytop10() {
		// TODO Auto-generated method stub
		return reportNgayDao.finbytop10();
	}
	@Override
	public List<Invoice> finbystartTimea(LocalDate ngayhomnay) {
		// TODO Auto-generated method stub
		return reportNgayDao.finbystartTimea(ngayhomnay);
	}
	@Override
	public List<Invoice> finbyYesterday(String homqua) {
		// TODO Auto-generated method stub
		return reportNgayDao.finbyYesterday(homqua);
	}
	@Override
	public List<Object> fiadAllTTKT(String ngaybt, String ngaykt) {
		// TODO Auto-generated method stub
		return reportNgayDao.fiadAllTTKT(ngaybt, ngaykt);
	}


}

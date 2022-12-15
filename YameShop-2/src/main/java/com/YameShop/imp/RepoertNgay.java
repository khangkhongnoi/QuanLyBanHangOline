package com.YameShop.imp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.domain.MyItem;

public interface RepoertNgay {
	List<MyItem> reportNgay(Date data, int limit);
	List<Invoice > finall();
	int finbyngay(String date);
	
	List<Object> finbyMonth(int sonam);
	List<Object> finbytop10();
	List<Invoice> finbystartTimea( LocalDate ngayhomnay);
	List<Invoice> finbyYesterday( String homqua);
	
	List<Object> fiadAllTTKT(String ngaybt , String ngaykt);
}

package com.YameShop.dao;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.domain.MyItem;
import com.YameShop.domain.Size;
import com.YameShop.imp.RepoertNgay;

@Repository
public class ReportNgayDao implements RepoertNgay {
	 @Autowired
	 SessionFactory sessionFactory;
	@Override
	@Transactional
	public List<MyItem> reportNgay(Date date, int limit) {
		
		List<MyItem> list = new ArrayList<>();
		//Date date = new Date();
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String data = simpleDateFormat.format(new Date());
        for (int i = limit - 1; i >= 0; i--) {
            Date d = subDay(date, i);
         
    		
            MyItem myItem = new MyItem();
            myItem.setTime(converrD2s(d));
            
        	Calendar calendar = Calendar.getInstance();
			calendar.add(calendar.DATE, -i);
			Date t = calendar.getTime();
			String d1 = simpleDateFormat.format(t);
			System.err.println(d1);
            myItem.setValue(countItemByDate(d1));
           
            list.add(myItem);
        }
        return list;
	}
	
	
	private int countItemByDate(String date) {
		Session session = sessionFactory.getCurrentSession();
       
		String sql = "SELECT sum(total_money) FROM invoice WHERE booking_date  = '" + date +"'" ;
           Long invoice = (Long) session.createQuery(sql).getSingleResult();
           Long obj = (long) invoice;
          
          return obj.intValue();
        
        
    }

	public static Date subDay(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}
	
	public static Date addDays (Date date , int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	private String converrD2s(Date date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyy");
		return df.format(date);
	}


	@Override
	@Transactional
	public List<Invoice> finall() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM invoice" ;
		List<Invoice> listsize = session.createQuery(sql).getResultList();
		return listsize;
	}


	@Override
	@Transactional
	public int finbyngay(String date) {
		Session session = sessionFactory.getCurrentSession();
	       
		String sql = "SELECT sum(total_money) FROM invoice WHERE receiptDate  = '" + date +"'" ;
           Long invoice = (Long) session.createQuery(sql).getSingleResult();
           if(invoice != null) {
        	   Long obj = (long) invoice;
        	   return obj.intValue();
           }
           else {
        	   return 0;
           }
           
         
          
	}


	@Override
	@Transactional
	public List<Object> finbyMonth(int sonam) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT sum(total_money) ,(MONTH(receiptDate)) as month FROM invoice   where YEAR(receiptDate) = "+ sonam + " GROUP BY month order by month asc";
		List<Object> list = session.createQuery(sql).getResultList();
	
//		for (Object invoice : list) {
//			System.err.println(invoice.getReceiptDate());
//			System.err.println(invoice.getTotal_money());
//		}
		return list;
	}


	@Override
	@Transactional
	public List<Object> finbytop10() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT product_details.Id_Product_Details , product_details.product.Product_Name, product_details.product_Color.Color_Name , product_details.size.Size_Name, sum(Amount)  FROM invoice_details where product_details.Id_Product_Details = product_details.Id_Product_Details AND product_details.product.Id_Poroduct = product_details.product.Id_Poroduct AND product_details.product_Color.Id_Product_Color = product_details.product_Color.Id_Product_Color AND product_details.size.Id_Size = product_details.size.Id_Size group by product_details.Id_Product_Details order by sum(Amount) DESC";
				
		List<Object> list = session.createQuery(sql).getResultList();
	
		return list;
	}


	


	@Override
	@Transactional
	public List<Invoice> finbystartTimea(LocalDate ngayhomnay) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " from invoice where receiptDate = '" + ngayhomnay+ "'" ;
		System.err.println(sql);
		List<Invoice> list = session.createQuery(sql).getResultList();
		
		return list;
	}


	@Override
	@Transactional
	public List<Invoice> finbyYesterday(String homqua) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " from invoice where receiptDate = '" + homqua+ "'" ;
		
		List<Invoice> list = session.createQuery(sql).getResultList();
		
		return list;
	}


	@Override
	@Transactional
	public List<Object> fiadAllTTKT(String ngaybt, String ngaykt) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT sum(total_money), receiptDate  FROM invoice where receiptDate BETWEEN '" + ngaybt+ "' AND '" + ngaykt+ "' GROUP BY receiptDate order by receiptDate desc";
		List<Object> list = session.createQuery(sql).getResultList();
		
		return list;
	}


	
}

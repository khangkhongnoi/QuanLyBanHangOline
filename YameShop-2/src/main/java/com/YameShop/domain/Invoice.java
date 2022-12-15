package com.YameShop.domain;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "invoice")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
			private Long Id_Invoice;
	@Column(name = "customer_name")
			private String Customer_Name;
	@Column(name = "phone" , length = 15)
			private String Phone;
	@Column(name = "delivery_address")
			private String Delivery_Address;
	@Column
	private String email;
	@Column(name = "booking_date")
			private String Booking_Date;
	@Column(name = "gio")
		private Time gio;
	@Column(name = "delivery_formality")
			private String Delivery_Formality;
	@Column(name = "total_money")
	private Integer total_money;
	@Column(name = "receiptDate")

	private Timestamp receiptDate;
	@Column
	private String ghichu;
	@Column
	private Integer Tongsoluong;
	public Integer getTongsoluong() {
		return Tongsoluong;
	}

	public void setTongsoluong(Integer tongsoluong) {
		Tongsoluong = tongsoluong;
	}

	@Column(name = "status")
			private Integer Status;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL , mappedBy = "invoice")
	@JsonIgnore
	 Set<Invoice_details> invoice_details;
	 
	 
	 
	 @ManyToOne(cascade = CascadeType.MERGE)
	 @JsonIgnore
	    @JoinColumn(name = "Id_KH")
	 private KhacHang khacHang;

	 

	public KhacHang getKhacHang() {
		return khacHang;
	}

	public void setKhacHang(KhacHang khacHang) {
		this.khacHang = khacHang;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	

	public Time getGio() {
		return gio;
	}

	public void setGio(Time gio) {
		this.gio = gio;
	}

	public Timestamp getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Timestamp receiptDate) {
		this.receiptDate = receiptDate;
	}

	public Integer getTotal_money() {
		return total_money;
	}

	public void setTotal_money(Integer total_money) {
		this.total_money = total_money;
	}

	public Long getId_Invoice() {
		return Id_Invoice;
	}

	public String getDelivery_Formality() {
		return Delivery_Formality;
	}

	public void setDelivery_Formality(String delivery_Formality) {
		Delivery_Formality = delivery_Formality;
	}

	public Set<Invoice_details> getInvoice_details() {
		return invoice_details;
	}

	public void setInvoice_details(Set<Invoice_details> invoice_details) {
		this.invoice_details = invoice_details;
	}

	public void setId_Invoice(Long id_Invoice) {
		Id_Invoice = id_Invoice;
	}

	public String getCustomer_Name() {
		return Customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getDelivery_Address() {
		return Delivery_Address;
	}

	public void setDelivery_Address(String delivery_Address) {
		Delivery_Address = delivery_Address;
	}

	public String getBooking_Date() {
		return Booking_Date;
	}

	public void setBooking_Date(String booking_Date) {
		Booking_Date = booking_Date;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}


	
	
	
}

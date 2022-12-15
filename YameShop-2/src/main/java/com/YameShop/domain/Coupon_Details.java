package com.YameShop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity(name = "coupon_details")
public class Coupon_Details {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id_Coupon_Details;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Id_Product_Details")
	private	Product_details product_details;
	
	@ManyToOne
	@JoinColumn(name = "Id_Coupon")
	private Import_coupon import_coupon;
	@Column(name = "Don_Gia")
	private Integer Don_Gia;
	@Column(name = "thanhtien")
	private Integer thanhtien;
	@Column(name = "quanlity")
	private Integer Quanlity;
	
	

	public Integer getId_Coupon_Details() {
		return Id_Coupon_Details;
	}

	public void setId_Coupon_Details(Integer id_Coupon_Details) {
		Id_Coupon_Details = id_Coupon_Details;
	}

	public Product_details getProduct_details() {
		return product_details;
	}

	public void setProduct_details(Product_details product_details) {
		this.product_details = product_details;
	}

	

	public Integer getDon_Gia() {
		return Don_Gia;
	}

	public void setDon_Gia(Integer don_Gia) {
		Don_Gia = don_Gia;
	}



	public Import_coupon getImport_coupon() {
		return import_coupon;
	}
	public void setImport_coupon(Import_coupon import_coupon) {
		this.import_coupon = import_coupon;
	}
	
	
	public Integer getThanhtien() {
		return thanhtien;
	}

	public void setThanhtien(Integer thanhtien) {
		this.thanhtien = thanhtien;
	}

	public Integer getQuanlity() {
		return Quanlity;
	}
	public void setQuanlity(Integer quanlity) {
		Quanlity = quanlity;
	}
	
	
}

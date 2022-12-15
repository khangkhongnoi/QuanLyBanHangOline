package com.YameShop.domain;

import java.sql.Timestamp;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "import_coupon")
public class Import_coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id_Coupon;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "Id_Supplier")
		private Supplier supplier;
	
	@Column(name = "date_founded")
	private Timestamp Data_Founded;
	@Column(name = "total_money")
	private Integer total_MoneyInteger;
	@Column
	private Integer giamgia;
	@Column
	private String ghichu;
	
	@Column(name = "sumquantity")
	private Integer sumquantity;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name =  "Id_Coupon")
//	 Set<Coupon_Details> coupon_Details;
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JsonIgnore
//	@JoinColumn(name = "User_Id")
//	private AppUser appUser;
	 
	 
	 @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL ,mappedBy = "import_coupon")
		@JsonIgnore
		 Set<Coupon_Details> coupon_Details;
	 
	 @Column(name = "status")
		
		private Integer status;
	 
	 
	 
	 
	public Integer getGiamgia() {
		return giamgia;
	}

	public void setGiamgia(Integer giamgia) {
		this.giamgia = giamgia;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

//	public AppUser getAppUser() {
//		return appUser;
//	}
//
//	public void setAppUser(AppUser appUser) {
//		this.appUser = appUser;
//	}

	public Integer getSumquantity() {
		return sumquantity;
	}

	public void setSumquantity(Integer sumquantity) {
		this.sumquantity = sumquantity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId_Coupon() {
		return Id_Coupon;
	}

	public void setId_Coupon(Integer id_Coupon) {
		Id_Coupon = id_Coupon;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Timestamp getData_Founded() {
		return Data_Founded;
	}

	public void setData_Founded(Timestamp data_Founded) {
		Data_Founded = data_Founded;
	}

	public Integer getTotal_MoneyInteger() {
		return total_MoneyInteger;
	}

	public void setTotal_MoneyInteger(Integer total_MoneyInteger) {
		this.total_MoneyInteger = total_MoneyInteger;
	}

	public Set<Coupon_Details> getCoupon_Details() {
		return coupon_Details;
	}

	public void setCoupon_Details(Set<Coupon_Details> coupon_Details) {
		this.coupon_Details = coupon_Details;
	}
	
	
}

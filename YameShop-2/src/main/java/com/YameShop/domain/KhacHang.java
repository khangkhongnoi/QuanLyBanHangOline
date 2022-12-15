package com.YameShop.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity(name = "khachang")
public class KhacHang {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
		private Integer Id_KH; 
	@Column(name = "fullname" ,length = 50)
		private String TenKH;
	@Column(name = "date_of_birth")
		private String Date_Of_Birth;
	@Column
	private String phone;
	@Column String emali;
	public String getEmali() {
		return emali;
	}
	public void setEmali(String emali) {
		this.emali = emali;
	}
	@Column(name = "address" , length = 200)
		private String Address;
	@Column(name = "gender")
		private String Gender;
	public Integer getId_KH() {
		return Id_KH;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
    @JoinColumn(name = "userid", referencedColumnName = "User_id")
  private AppUser appUser;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL ,mappedBy = "khacHang")
	@JsonIgnore
	Set<Invoice> invoices;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Set<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	public void setId_KH(Integer id_KH) {
		Id_KH = id_KH;
	}
	public String getTenKH() {
		return TenKH;
	}
	public void setTenKH(String tenKH) {
		TenKH = tenKH;
	}
	public String getDate_Of_Birth() {
		return Date_Of_Birth;
	}
	public void setDate_Of_Birth(String date_Of_Birth) {
		Date_Of_Birth = date_Of_Birth;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	
	
}

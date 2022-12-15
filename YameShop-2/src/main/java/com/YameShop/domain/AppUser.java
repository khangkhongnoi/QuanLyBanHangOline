package com.YameShop.domain;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "App_User")
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_id")
	private Long userid;
	@Column(name = "User_Name" , length = 36)
	private String userName;
	@Column
	private String tenngguoidung;
	@Column(name = "Password", length = 128)
	private String Password;
	@Column(name = "email")
	private String Email;
	@Column(name = "sdt" , length = 12)
	private String Sdt;
	@Transient
	private String confirmPassword;
	@Column
	private Integer status;

	  @OneToOne(mappedBy = "appUser")
	  @JsonIgnore
    private Staff staff;
	  @OneToOne(mappedBy = "appUser" ,cascade = CascadeType.ALL)
	    private KhacHang khacHang;
    
	  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL , mappedBy = "appUser")
		@JsonIgnore
				private Set<UserRole> userRoles ;
	
//	  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL ,mappedBy = "appUser")
//		@JsonIgnore
//		Set<Import_coupon> import_coupons ;
//		
		
		
	  
	public String getTenngguoidung() {
		return tenngguoidung;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setTenngguoidung(String tenngguoidung) {
		this.tenngguoidung = tenngguoidung;
	}
	public KhacHang getKhacHang() {
		return khacHang;
	}
	public void setKhacHang(KhacHang khacHang) {
		this.khacHang = khacHang;
	}
//	public Set<Import_coupon> getImport_coupons() {
//		return import_coupons;
//	}
//	public void setImport_coupons(Set<Import_coupon> import_coupons) {
//		this.import_coupons = import_coupons;
//	}
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSdt() {
		return Sdt;
	}
	public void setSdt(String sdt) {
		Sdt = sdt;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}

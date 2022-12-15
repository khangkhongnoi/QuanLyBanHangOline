package com.YameShop.domain;

import java.security.Timestamp;
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

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "staff")
public class Staff  {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
		private Long Idsaff; 
	@Column(name = "fullname" ,length = 50)
		private String FullName;
	@Column(name = "date_of_birth")
		private String Date_Of_Birth;
	@Column(name = "address" , length = 200)
		private String Address;
	@Column(name = "gender")
		private String Gender;
	@Column(name = "cmnd" ,length = 20)
		private String CMND;
	@Column(name = "sdt" ,length = 10)
		private String SDT;
	@Column(name = "email" , length = 50)
		private String Email;
	@Column(name = "image")
	private String Image;
	@Column(name = "status")
	private Integer status;
	
	@OneToMany(mappedBy = "staff" , fetch = FetchType.EAGER)
	Set<Position> position;
	

	
	  public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "userid", referencedColumnName = "User_id")
	  private AppUser appUser;
		
	  
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public Long getIdsaff() {
		return Idsaff;
	}
	public void setIdsaff(Long idsaff) {
		Idsaff = idsaff;
	}
	public String getDate_Of_Birth() {
		return Date_Of_Birth;
	}
	public void setDate_Of_Birth(String date_Of_Birth) {
		Date_Of_Birth = date_Of_Birth;
	}
	public Set<Position> getPosition() {
		return position;
	}
	public void setPosition(Set<Position> position) {
		this.position = position;
	}
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	public Long getId() {
		return Idsaff;
	}
	public void setId(Long id) {
		Idsaff = id;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
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
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cMND) {
		CMND = cMND;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	
	
}

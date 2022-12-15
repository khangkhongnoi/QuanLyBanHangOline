//package com.YameShop.domain;
//
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name = "dtgh")
//public class Doitacgiaohang {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "Id_DTGH", nullable = false)
//	private Integer Id_DTGH;
//	@Column
//	private String TenDT;
//	@Column
//	private String Phone;
//	@Column(name = "address")
//	private String Address;
//	@Column(name = "email")
//	private String Email;
//@Column(name = "descriptions")
//private String descriptions;
//	@Column(name = "status")
//	private Integer status;
//	@OneToMany(mappedBy = "doitacgiaohang" ,fetch = FetchType.EAGER )
//	private Set<Invoice> invoices;
//
//	
//
//	public Integer getId_DTGH() {
//		return Id_DTGH;
//	}
//
//	public void setId_DTGH(Integer id_DTGH) {
//		Id_DTGH = id_DTGH;
//	}
//
//	public Set<Invoice> getInvoices() {
//		return invoices;
//	}
//
//	public void setInvoices(Set<Invoice> invoices) {
//		this.invoices = invoices;
//	}
//
//	public String getTenDT() {
//		return TenDT;
//	}
//
//	public void setTenDT(String tenDT) {
//		TenDT = tenDT;
//	}
//
////	public Set<Invoice> getInvoices() {
////		return invoices;
////	}
////
////	public void setInvoices(Set<Invoice> invoices) {
////		this.invoices = invoices;
////	}
//
//	public String getDescriptions() {
//		return descriptions;
//	}
//
//	public void setDescriptions(String descriptions) {
//		this.descriptions = descriptions;
//	}
//
//	public String getEmail() {
//		return Email;
//	}
//
//	public void setEmail(String email) {
//		Email = email;
//	}
//
//	public Integer getStatus() {
//		return status;
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
//
//
//	public String getPhone() {
//		return Phone;
//	}
//
//	public void setPhone(String phone) {
//		Phone = phone;
//	}
//
//	public String getAddress() {
//		return Address;
//	}
//
//	public void setAddress(String address) {
//		Address = address;
//	}
//
//	
//	
//}

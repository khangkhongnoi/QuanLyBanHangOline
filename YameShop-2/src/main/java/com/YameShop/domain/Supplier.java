package com.YameShop.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "supplier")
public class Supplier {
	@Id
	// Khai báo phương thức sinh id, sử dụng generator có tên generator_id
	@GeneratedValue(generator = "generator_id")
	// Khai báo generator có tên generator_id định nghĩa trong class StringGeneratorId
	@GenericGenerator(name = "generator_id", strategy = "com.YameShop.StringId.Supplier_Id")
	private String Id_Supplier;
	@Column(name = "supplier_name")
	private String Supplier_Name;
	@Column(name = "phone")
	private String Phone;
	@Column(name = "address")
	private String Address;
	@Column(name = "email")
	private String Email;
@Column(name = "descriptions")
private String descriptions;
	@Column(name = "status")
	private Integer status;
	@OneToMany(mappedBy = "supplier" ,fetch = FetchType.EAGER )
	private Set<Import_coupon> import_coupon;

	
	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getId_Supplier() {
		return Id_Supplier;
	}

	public void setId_Supplier(String id_Supplier) {
		Id_Supplier = id_Supplier;
	}

	public String getSupplier_Name() {
		return Supplier_Name;
	}

	public void setSupplier_Name(String supplier_Name) {
		Supplier_Name = supplier_Name;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Set<Import_coupon> getImport_coupon() {
		return import_coupon;
	}

	public void setImport_coupon(Set<Import_coupon> import_coupon) {
		this.import_coupon = import_coupon;
	}
	
	
}

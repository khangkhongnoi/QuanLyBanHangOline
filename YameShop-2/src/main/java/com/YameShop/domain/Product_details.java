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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product_details")
public class Product_details {
	@Id
	// Khai báo phương thức sinh id, sử dụng generator có tên generator_id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long Id_Product_Details;
	
	@Column(name = "quantity")
			private Integer Quantity;
	@Column(name = "date_added")
			private Timestamp Date_Added;
	@Column(name = "status")
	
	private Integer status;
	@Column
	private String HDSD;

	//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "Id_Poroduct")
//		Product product;
//	@OneToOne(fetch = FetchType.EAGER )
//	@JoinColumn(name = "Id_Poroduct")
//	private Product product;
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "Id_Size")
//		Size size;
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonIgnore
	@JoinTable(
	  name = "promontion_productdetais", 
	  joinColumns = @JoinColumn(name = "Id_Product_Details"),
	  inverseJoinColumns = @JoinColumn(name = "Id_Promontion"))
	Set<Promontion> promontions;

	
	
	public Set<Promontion> getPromontions() {
		return promontions;
	}

	public void setPromontions(Set<Promontion> promontions) {
		this.promontions = promontions;
	}

	public String getHDSD() {
		return HDSD;
	}

	public void setHDSD(String hDSD) {
		HDSD = hDSD;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Id_Product_Color")
	
	private Product_Color product_Color;
	
	@ManyToOne(fetch = FetchType.EAGER)
	 @JsonIgnore
	    @JoinColumn(name = "Id_Poroduct")
	private Product product;

	 
	@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "Id_Size")
	 private Size size;

	 @OneToMany(fetch = FetchType.EAGER,  mappedBy = "product_details")
		@JsonIgnore
			 Set<Coupon_Details> coupon_Details;
	 @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL , mappedBy = "product_details")
		@JsonIgnore
		Set<Invoice_details> invoice_details ;
	

		
	public Set<Coupon_Details> getCoupon_Details() {
		return coupon_Details;
	}

	public void setCoupon_Details(Set<Coupon_Details> coupon_Details) {
		this.coupon_Details = coupon_Details;
	}

	public Set<Invoice_details> getInvoice_details() {
		return invoice_details;
	}

	public void setInvoice_details(Set<Invoice_details> invoice_details) {
		this.invoice_details = invoice_details;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}


	

	

	public Long getId_Product_Details() {
		return Id_Product_Details;
	}

	public void setId_Product_Details(Long id_Product_Details) {
		Id_Product_Details = id_Product_Details;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public Timestamp getDate_Added() {
		return Date_Added;
	}

	public void setDate_Added(Timestamp date_Added) {
		Date_Added = date_Added;
	}



	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

//	public Size getSize() {
//		return size;
//	}
//
//	public void setSize(Size size) {
//		this.size = size;
	//}

	public Product_Color getProduct_Color() {
		return product_Color;
	}

	public void setProduct_Color(Product_Color product_Color) {
		this.product_Color = product_Color;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
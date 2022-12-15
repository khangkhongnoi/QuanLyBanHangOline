package com.YameShop.domain;

import java.sql.Timestamp;

public class jsondetails {
	private Long Id_Product_Details;
	private Integer Quantity;
	private Timestamp Date_Added;
	
	private Product_Color product_Color;
	private Product product;
	private Size size;
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
	public Product_Color getProduct_Color() {
		return product_Color;
	}
	public void setProduct_Color(Product_Color product_Color) {
		this.product_Color = product_Color;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	
	
	
}

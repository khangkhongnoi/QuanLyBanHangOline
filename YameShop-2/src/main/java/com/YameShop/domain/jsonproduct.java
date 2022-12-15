package com.YameShop.domain;

import java.util.Set;

import javax.persistence.Column;

public class jsonproduct {
	private String Id_Poroduct;
	
			private String Product_Name;
	
			private Integer Price;
			private Integer gianha;
	
			private String Fabric_Material;
	
			private String Descriptions;
	
			private Integer Warranty;
	
			private Integer Wiew;
	
			private String Image;
			private String Image1;
	private Integer status;
	private Set<Images> images;
	Set<Coupon_Details> coupon_Details;
	private Category category;
	private	Brand brand;
	Set<Product_details>product_details;
	Set<Promontion> promontion;
	
	
	
	public Integer getGianha() {
		return gianha;
	}
	public void setGianha(Integer gianha) {
		this.gianha = gianha;
	}
	public String getImage1() {
		return Image1;
	}
	public void setImage1(String image1) {
		Image1 = image1;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Set<Product_details> getProduct_details() {
		return product_details;
	}
	public void setProduct_details(Set<Product_details> product_details) {
		this.product_details = product_details;
	}
	public Set<Promontion> getPromontion() {
		return promontion;
	}
	public void setPromontion(Set<Promontion> promontion) {
		this.promontion = promontion;
	}
	public String getId_Poroduct() {
		return Id_Poroduct;
	}
	public void setId_Poroduct(String id_Poroduct) {
		Id_Poroduct = id_Poroduct;
	}
	public String getProduct_Name() {
		return Product_Name;
	}
	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}
	public Integer getPrice() {
		return Price;
	}
	public void setPrice(Integer price) {
		Price = price;
	}
	public String getFabric_Material() {
		return Fabric_Material;
	}
	public void setFabric_Material(String fabric_Material) {
		Fabric_Material = fabric_Material;
	}
	public String getDescriptions() {
		return Descriptions;
	}
	public void setDescriptions(String descriptions) {
		Descriptions = descriptions;
	}
	public Integer getWarranty() {
		return Warranty;
	}
	public void setWarranty(Integer warranty) {
		Warranty = warranty;
	}
	public Integer getWiew() {
		return Wiew;
	}
	public void setWiew(Integer wiew) {
		Wiew = wiew;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Set<Images> getImages() {
		return images;
	}
	public void setImages(Set<Images> images) {
		this.images = images;
	}
	public Set<Coupon_Details> getCoupon_Details() {
		return coupon_Details;
	}
	public void setCoupon_Details(Set<Coupon_Details> coupon_Details) {
		this.coupon_Details = coupon_Details;
	}
	
	
}

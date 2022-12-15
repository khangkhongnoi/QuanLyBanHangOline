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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "promontion")
public class Promontion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
			private Long Id_Promontion;
	@Column(name = "promontion_name")
			private String Promontion_Name;
	@Column(name = "start_time")
			private String Start_Time;
	@Column(name = "end_time")
			private String End_Time;
	@Column(name = "descriptions")
			private String Descriptions;
	@Column(name = "promontion_image")
			private String Promontion_Image;
	@Column(name = "reduced_price")
		private Integer Reduced_Price;
	
	@Column(name = "vitri")
		private Integer vitri;
	@Column(name = "status")
		private Integer status;
		@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
	  name = "promontion_product", 
	  joinColumns = @JoinColumn(name = "Id_Promontion"),
	  inverseJoinColumns = @JoinColumn(name = "Id_Poroduct"))
	Set<Product> products;
		
		@ManyToMany(fetch=FetchType.EAGER)
		@JoinTable(
		  name = "promontion_productdetais", 
		  joinColumns = @JoinColumn(name = "Id_Promontion"),
		  inverseJoinColumns = @JoinColumn(name = "Id_Product_Details"))
		Set<Product_details> product_details;
		
		
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL ,mappedBy = "promontion")
	@JsonIgnore
	Set<ImagesPromontion>imagesPromontions;


	
	public Set<Product_details> getProduct_details() {
		return product_details;
	}


	public void setProduct_details(Set<Product_details> product_details) {
		this.product_details = product_details;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Set<ImagesPromontion> getImagesPromontions() {
		return imagesPromontions;
	}


	public void setImagesPromontions(Set<ImagesPromontion> imagesPromontions) {
		this.imagesPromontions = imagesPromontions;
	}


	public Integer getVitri() {
		return vitri;
	}


	public void setVitri(Integer vitri) {
		this.vitri = vitri;
	}


	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}


	public Long getId_Promontion() {
		return Id_Promontion;
	}


	public void setId_Promontion(Long id_Promontion) {
		Id_Promontion = id_Promontion;
	}


	public String getPromontion_Name() {
		return Promontion_Name;
	}


	public void setPromontion_Name(String promontion_Name) {
		Promontion_Name = promontion_Name;
	}


	public String getStart_Time() {
		return Start_Time;
	}


	public void setStart_Time(String start_Time) {
		Start_Time = start_Time;
	}


	public String getEnd_Time() {
		return End_Time;
	}


	public void setEnd_Time(String end_Time) {
		End_Time = end_Time;
	}


	public String getDescriptions() {
		return Descriptions;
	}


	public void setDescriptions(String descriptions) {
		Descriptions = descriptions;
	}


	public String getPromontion_Image() {
		return Promontion_Image;
	}


	public void setPromontion_Image(String promontion_Image) {
		Promontion_Image = promontion_Image;
	}


	public Integer getReduced_Price() {
		return Reduced_Price;
	}


	public void setReduced_Price(Integer reduced_Price) {
		Reduced_Price = reduced_Price;
	}



	
}

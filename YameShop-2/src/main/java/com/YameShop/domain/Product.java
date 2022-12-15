package com.YameShop.domain;

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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "product")
public class Product {
	@Id
	// Khai báo phương thức sinh id, sử dụng generator có tên generator_id
	@GeneratedValue(generator = "generator_id")
	// Khai báo generator có tên generator_id định nghĩa trong class StringGeneratorId
	@GenericGenerator(name = "generator_id", strategy = "com.YameShop.StringId.Product_Id")
			private String Id_Poroduct;
	@Column(name = "product_name")
			private String Product_Name;
	@Column(name = "impoet_price")
	private Integer import_Price;
	@Column(name = "retail_Price")
			private Integer retail_Price;
	@Column(name = "promotional_price")
			private Integer promotional_price;
	@Column(name = "fabric_material")
			private String Fabric_Material;
	@Column(name = "descriptions" , length = 500)
			private String Descriptions;
	
	@Column(name = "wiewtruycap")
			private Integer wiewtruycap;
	@Column(name = "image")
			private String Image;
	@Column(name = "image1")
	private String Image1;
	@Column(name = "status")
	
	private Integer status;
	

 
	public Integer getPromotional_price() {
		return promotional_price;
	}

	public void setPromotional_price(Integer promotional_price) {
		this.promotional_price = promotional_price;
	}

	

	public String getImage1() {
		return Image1;
	}

	public void setImage1(String image1) {
		Image1 = image1;
	}

			
		public Set<Price> getPrices() {
		return prices;
	}

	public void setPrices(Set<Price> prices) {
		this.prices = prices;
	}

		@ManyToOne(fetch = FetchType.EAGER)
		@JsonIgnore
		@JoinColumn(name = "Id_Category")
		private Category category;
	
		@ManyToOne(fetch = FetchType.EAGER)
		@JsonIgnore
		@JoinColumn(name = "Id_Brand")
		private	Brand brand;
		
		@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL ,mappedBy = "product")
		
		@JsonIgnore
		private Set<Product_details>product_details;
	
		@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL ,mappedBy = "product")
		@JsonIgnore
		Set<Price> prices;
		@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL ,mappedBy = "product")
		@JsonIgnore
		Set<Promotional_price> promotional_prices;

		@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL ,mappedBy = "product")
		@JsonIgnore
		Set<Import_price> import_prices;
		@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL ,mappedBy = "product")
		@JsonIgnore
		Set<Images> images ;
		
		
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonIgnore
	@JoinTable(
	  name = "promontion_product", 
	  joinColumns = @JoinColumn(name = "Id_Poroduct"),
	  inverseJoinColumns = @JoinColumn(name = "Id_Promontion"))
	Set<Promontion> promontions;
	
	
	
	
	
	
	public Set<Images> getImages() {
		return images;
	}

	public void setImages(Set<Images> images) {
		this.images = images;
	}

	public Set<Import_price> getImport_prices() {
		return import_prices;
	}

	public void setImport_prices(Set<Import_price> import_prices) {
		this.import_prices = import_prices;
	}

	public Set<Promotional_price> getPromotional_prices() {
		return promotional_prices;
	}

	public void setPromotional_prices(Set<Promotional_price> promotional_prices) {
		this.promotional_prices = promotional_prices;
	}

	public String getFabric_Material() {
			return Fabric_Material;
		}

		public void setFabric_Material(String fabric_Material) {
			Fabric_Material = fabric_Material;
		}



		public Integer getWiewtruycap() {
			return wiewtruycap;
		}

		public void setWiewtruycap(Integer wiewtruycap) {
			this.wiewtruycap = wiewtruycap;
		}

		public Set<Promontion> getPromontions() {
			return promontions;
		}

		public void setPromontions(Set<Promontion> promontions) {
			this.promontions = promontions;
		}

		public String getImage() {
			return Image;
		}

		public void setImage(String image) {
			Image = image;
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



	public Integer getImport_Price() {
		return import_Price;
	}

	public void setImport_Price(Integer import_Price) {
		this.import_Price = import_Price;
	}

	public Integer getRetail_Price() {
		return retail_Price;
	}

	public void setRetail_Price(Integer retail_Price) {
		this.retail_Price = retail_Price;
	}

	

	public String getDescriptions() {
		return Descriptions;
	}

	public void setDescriptions(String descriptions) {
		Descriptions = descriptions;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


			
			
			
}

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
@Entity(name = "product_color")
public class Product_Color {
	@Id
	// Khai báo phương thức sinh id, sử dụng generator có tên generator_id
	@GeneratedValue(generator = "generator_id")
	// Khai báo generator có tên generator_id định nghĩa trong class StringGeneratorId
	@GenericGenerator(name = "generator_id", strategy = "com.YameShop.StringId.Color_Id")
			private String Id_Product_Color;
	@Column(name = "color_name")
			private String Color_Name;
	@Column(name = "images")
	private String images;
	@Column(name = "status")
	private Integer status;

	
	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getId_Product_Color() {
		return Id_Product_Color;
	}
	public void setId_Product_Color(String id_Product_Color) {
		Id_Product_Color = id_Product_Color;
	}
	public String getColor_Name() {
		return Color_Name;
	}
	public void setColor_Name(String color_Name) {
		Color_Name = color_Name;
	}


	
	
	
}

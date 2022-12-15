package com.YameShop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "images")
public class Images {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
			private Long Id_Images;
	@Column(name = "image_name")
			private String Image_Name;
	
	@ManyToOne
	@JoinColumn(name = "Id_Poroduct")
	private Product product;


	public Long getId_Images() {
		return Id_Images;
	}

	public void setId_Images(Long id_Images) {
		Id_Images = id_Images;
	}

	public String getImage_Name() {
		return Image_Name;
	}

	public void setImage_Name(String image_Name) {
		Image_Name = image_Name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}

package com.YameShop.domain;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "promotional_price")
public class Promotional_price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "Gia_KM")
	private Integer Gia_KM;
	@Column(name = "thoigian")
	private Timestamp thoigian;
	
	@ManyToOne(cascade = CascadeType.ALL)
	 @JsonIgnore
	    @JoinColumn(name = "Id_Poroduct", nullable = false)
	 private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGia_KM() {
		return Gia_KM;
	}

	public void setGia_KM(Integer gia_KM) {
		Gia_KM = gia_KM;
	}

	public Timestamp getThoigian() {
		return thoigian;
	}

	public void setThoigian(Timestamp thoigian) {
		this.thoigian = thoigian;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	
	
}

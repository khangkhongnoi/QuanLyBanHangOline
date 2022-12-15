package com.YameShop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity(name = "ImagesPromontion")
public class ImagesPromontion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long Id;
	@Column(name =  "imageName")
	private String imageName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	 @JsonIgnore
	    @JoinColumn(name = "Id_Promontion", nullable = false)
	private Promontion promontion;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Promontion getPromontion() {
		return promontion;
	}

	public void setPromontion(Promontion promontion) {
		this.promontion = promontion;
	}
	
	
	
}

package com.YameShop.domain;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "category")
public class Category extends BaseEntity{
	@Id
	// Khai báo phương thức sinh id, sử dụng generator có tên generator_id
	@GeneratedValue(generator = "generator_id")
	// Khai báo generator có tên generator_id định nghĩa trong class StringGeneratorId
	@GenericGenerator(name = "generator_id", strategy = "com.YameShop.StringId.Category_Id")
	private String Id_Category;
	@Column(name = "category_name")
	private String Category_Name;
	@Column(name = "status")
	private Integer status;
	
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	
	private Set<Product> product;

	
	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getId_Category() {
		return Id_Category;
	}


	public void setId_Category(String id_Category) {
		Id_Category = id_Category;
	}


	public String getCategory_Name() {
		return Category_Name;
	}


	public void setCategory_Name(String category_Name) {
		Category_Name = category_Name;
	}


	public Set<Product> getProduct() {
		return product;
	}


	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	
}

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "size")
public class Size {
	@Id
	// Khai báo phương thức sinh id, sử dụng generator có tên generator_id
	@GeneratedValue(generator = "generator_idsize")
	// Khai báo generator có tên generator_id định nghĩa trong class StringGeneratorId
	@GenericGenerator(name = "generator_idsize", strategy = "com.YameShop.StringId.Size_Id")
			private String Id_Size;
	@Column(name = "size_name")
			private String Size_Name;
	@Column(name = "start_hieght")
			private Integer Start_Hieght;
	@Column(name = "end_hieght")
			private Integer End_Hieght;
	@Column(name = "start_weigh")
			private Integer Start_Weigh;
	@Column(name = "end_weigh")
	private Integer End_Weigh;
	@Column(name = "status")
			
			private Integer status;
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL , mappedBy = "size")
//	@JsonIgnore
//	Set<Product_details>product_details;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL , mappedBy = "size")
	@JsonIgnore
	Set<Product_details> product_details;

	

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

	public String getId_Size() {
		return Id_Size;
	}

	public void setId_Size(String id_Size) {
		Id_Size = id_Size;
	}

	public String getSize_Name() {
		return Size_Name;
	}

	public void setSize_Name(String size_Name) {
		Size_Name = size_Name;
	}

	public Integer getStart_Hieght() {
		return Start_Hieght;
	}

	public void setStart_Hieght(Integer start_Hieght) {
		Start_Hieght = start_Hieght;
	}

	public Integer getEnd_Hieght() {
		return End_Hieght;
	}

	public void setEnd_Hieght(Integer end_Hieght) {
		End_Hieght = end_Hieght;
	}

	public Integer getStart_Weigh() {
		return Start_Weigh;
	}

	public void setStart_Weigh(Integer start_Weigh) {
		Start_Weigh = start_Weigh;
	}

	public Integer getEnd_Weigh() {
		return End_Weigh;
	}

	public void setEnd_Weigh(Integer end_Weigh) {
		End_Weigh = end_Weigh;
	}

//	public Product_details getProduct_details() {
//		return product_details;
//	}
//
//	public void setProduct_details(Product_details product_details) {
//		this.product_details = product_details;
//	}


			
			
}

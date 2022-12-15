package com.YameShop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	// Khai báo phương thức sinh id, sử dụng generator có tên generator_id
	@GeneratedValue(generator = "generator_id")
	// Khai báo generator có tên generator_id định nghĩa trong class StringGeneratorId
	@GenericGenerator(name = "generator_id", strategy = "com.YameShop.domain.StringGeneratorId")
	private String id;
	private String name;
    // getter, setter 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
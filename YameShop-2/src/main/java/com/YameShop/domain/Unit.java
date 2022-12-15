package com.YameShop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "unit")
public class Unit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer Id_unit;
	@Column(name = "unit_name")
	private String unit_Name;
	@Column(name = "status")
	private Integer status;
	public Integer getId_unit() {
		return Id_unit;
	}
	public void setId_unit(Integer id_unit) {
		Id_unit = id_unit;
	}
	public String getUnit_Name() {
		return unit_Name;
	}
	public void setUnit_Name(String unit_Name) {
		this.unit_Name = unit_Name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}

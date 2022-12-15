package com.YameShop.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "position")
public class Position  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
		private Long IdPosition;
	@Column(name = "job_titel", length = 30)
		private String job_titel;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Idsaff", nullable=false)
	 private Staff staff;
	public Long getId() {
		return IdPosition;
	}
	public void setId(Long id) {
		IdPosition = id;
	}
	public String getJob_titel() {
		return job_titel;
	}
	public void setJob_titel(String job_titel) {
		this.job_titel = job_titel;
	}
	
	
}

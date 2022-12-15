package com.YameShop.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "App_Role")
public class AppRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Role_id")
	private Long Role_Id;
	
	@Column(name = "Role_Name")
	private String RoleName;

	 @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL , mappedBy = "appRole")
		@JsonIgnore
				private Set<UserRole> userRoles ;
	
	
	 
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Long getRole_Id() {
		return Role_Id;
	}

	public void setRole_Id(Long role_Id) {
		Role_Id = role_Id;
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	
}

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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "User_Role")
public class UserRole {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
	
		@ManyToOne
		@JsonIgnore
		@JoinColumn(name = "User_Id")
		private AppUser appUser;
		
		@ManyToOne
		@JsonIgnore
		@JoinColumn(name = "Role_Id")
		private AppRole appRole;

		
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public AppUser getAppUser() {
			return appUser;
		}

		public void setAppUser(AppUser appUser) {
			this.appUser = appUser;
		}

		public AppRole getAppRole() {
			return appRole;
		}

		public void setAppRole(AppRole appRole) {
			this.appRole = appRole;
		}
		
}

package com.YameShop.dao;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.YameShop.domain.AppUser;

@Repository
public class AppUserDAO1 {
	  @Autowired
	    private EntityManager entityManager;
	  
	  public AppUser findUserAccount(String userName) {
	        try {
	            String sql = "Select e from " + AppUser.class.getName() + " e " //
	                    + " Where e.userName = :userName ";

	            Query query = (Query) entityManager.createQuery(sql, AppUser.class);
	            ((TypedQuery<AppUser>) query).setParameter("userName", userName);

	            return (AppUser) ((javax.persistence.Query) query).getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	  }
}

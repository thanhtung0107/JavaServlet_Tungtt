package com.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaUtils {
	
	public static EntityManager getEntityManager() {
	return	Persistence.createEntityManagerFactory("AssJ4HT").createEntityManager();
		
	}

}

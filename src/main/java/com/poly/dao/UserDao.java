package com.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.poly.models.User;

public class UserDao extends AbstractEntityDao<User> {

	public UserDao() {
		super(User.class);

	}

	public void changePassword(String username, String oldPassword, String newPassword) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		String jpql = "select u from User u where u.username = :username and u.password = :password";

		try {
			trans.begin();
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("username", username);
			query.setParameter("password", oldPassword);

			User user = query.getSingleResult();
			if (user == null) {
				throw new Exception("Mật khẩu mới hoặc tài khoản không chính xác !! ");
			}
			user.setPasswords(newPassword);
			em.merge(user);

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			
		
		} finally {
			em.close();
		}
	}

}

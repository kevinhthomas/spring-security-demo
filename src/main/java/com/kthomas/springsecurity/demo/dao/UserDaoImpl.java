package com.kthomas.springsecurity.demo.dao;

import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kthomas.springsecurity.demo.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
    private Logger logger = Logger.getLogger(getClass().getName());

	
	@Override
	public User findByUserName(String userName) {

		logger.info("DAO START FIND");

		
		// get the current hibernate session
		Session currentSession = this.sessionFactory.getCurrentSession();

		// now retrieve/read from database using name
		Query<User> query = currentSession.createQuery("from User where userName=:userName", User.class);
		query.setParameter("userName", userName);
		
		User user = null;
		
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		
		return user;
	}

	@Override
	public void save(User user) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);		
	}

}

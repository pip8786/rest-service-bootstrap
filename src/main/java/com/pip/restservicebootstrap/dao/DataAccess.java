package com.pip.restservicebootstrap.dao;

import com.pip.restservicebootstrap.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by phsabo on 1/26/14.
 */
public class DataAccess {

	private static DataAccess dataAccess = null;
	private SessionFactory sessionFactory = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public static synchronized DataAccess getInstance() {
		if(dataAccess == null) {
			dataAccess = new DataAccess();
		}
		return dataAccess;
	}

	public List<User> getAllUsers(){
		Session session = sessionFactory.getCurrentSession();
		List<User> users = null;
		try {
			users = session.createCriteria(User.class).list();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		return users;
	}

	public Object getObjectByID(Class objectClass, int id) {
		Session session = sessionFactory.getCurrentSession();
		Object o = null;
		try {
			o = session.createCriteria(objectClass).add(Restrictions.eq("id",id)).uniqueResult();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		return o;
	}
}

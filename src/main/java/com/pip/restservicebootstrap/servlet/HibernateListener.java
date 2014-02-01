package com.pip.restservicebootstrap.servlet;

import com.pip.restservicebootstrap.dao.DataAccess;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

	private SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		context.setAttribute("sessionFactory", sessionFactory);
		DataAccess.getInstance().setSessionFactory(sessionFactory);

	}

	public void contextDestroyed(ServletContextEvent sce) {
		sessionFactory.close();
	}
}
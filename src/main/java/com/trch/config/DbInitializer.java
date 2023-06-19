package com.trch.config;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zaxxer.hikari.HikariDataSource;


public class DbInitializer implements ServletContextListener {
	
	HikariDataSource dataSource;
	
	Connection connection;
	
	public void contextInitialized(ServletContextEvent event) {
		
		ServletContext context = event.getServletContext();
		
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String user = context.getInitParameter("user");
		String password = context.getInitParameter("password");
		
		
		try {
			
			DbConfig.createDataSource(driver, url, user, password);
			
			connection = DbConfig.getConnection();
			
			DbConfig.runDatabaseScript(connection);
			
			connection.close();
			
		} catch (SQLException e) {
			
			Logger logger = Logger.getLogger(DbInitializer.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		}
		
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		
		dataSource.close();
		
	}

}

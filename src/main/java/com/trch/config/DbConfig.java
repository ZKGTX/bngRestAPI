package com.trch.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.Data;

@Data
public class DbConfig {
	
	private static DataSource dataSource;
	
	
	public static Connection getConnection() throws SQLException {
			    
	    return dataSource.getConnection();
	    
	  }
	
	protected static void createDataSource(String driver, String url, String user, String password) {
				
		HikariConfig hikariConfig = setHikariConfig(driver, url, user, password);
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		dataSource = hikariDataSource;
	}
	
	private static HikariConfig setHikariConfig (String driver, String url, String user, String password) {
		
		HikariConfig hikariConfig = new HikariConfig();
		
		hikariConfig.setDriverClassName(driver);
		
		hikariConfig.setJdbcUrl(url);
		
		hikariConfig.setUsername(user);
		
		hikariConfig.setPassword(password);
		
		hikariConfig.setPoolName("PostgresPool");
		
		hikariConfig.setMaximumPoolSize(7);
		
		hikariConfig.setConnectionTimeout(Duration.ofSeconds(30).toMillis());
		
		hikariConfig.setIdleTimeout(Duration.ofMinutes(2).toMillis());
		
	    return hikariConfig;
		
	}
	
		
	protected static void runDatabaseScript (Connection connection) {
		
		ScriptRunner scriptRunner = new ScriptRunner(connection);
		
		try {
			
			InputStream is = ResourcesReader.readFile("db_init.sql");
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			
			scriptRunner.runScript(bufferedReader);
			
			bufferedReader.close();
			
		} catch (IOException e) {
            Logger logger = Logger.getLogger(DbConfig.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
	}
	 
}

package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

public class Database {

	final static Logger LOG = Logger.getLogger(Database.class);
	
	private static Connection con = null;
	private static String jdbcDriver;
	private static String userName;
	private static String password;
	private static String url;
	
	public Database(String jdbcDriver, String userName, String password, String url) {
		super();
		Database.jdbcDriver = jdbcDriver;
		Database.userName = userName;
		Database.password = password;
		Database.url = url;
	}

	public static Connection getConnection(){
		
		if(jdbcDriver.isEmpty() || userName.isEmpty() || url.isEmpty()){
			LOG.error("#### Database connection details are empty ####");
		}else{
			try {
				Class.forName(jdbcDriver);
				con = DriverManager.getConnection(url, userName, password);
				return con;
			} catch (Exception e) {
				LOG.error("#### Exception occurred while connecting to database ####" + e.getMessage());
			}
		}
        return con;
    }
	
}

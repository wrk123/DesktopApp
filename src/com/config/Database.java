package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;
import com.constants.Constants;

public class Database {

	final static Logger LOG = Logger.getLogger(Database.class);
	private static Connection con = null;
	
	public static Connection getConnection(){
		try {
			Class.forName(Constants.DatabaseConnection.JDBC_DRIVER);
			con = DriverManager.getConnection(Constants.DatabaseConnection.URL,
					Constants.DatabaseConnection.USERNAME, Constants.DatabaseConnection.PASSWORD);
			return con;
		} catch (Exception e) {
			LOG.error("Exception occurred while connecting to database " + e.getMessage());
		}
        return con;
    }
	
}

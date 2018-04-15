package com.constants;

public class Constants {

	public interface DatabaseConnection {
		
		public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		public static final String URL 		= "jdbc:mysql://localhost:3306/world";
		public static final String USERNAME 	= "root";
		public static final String PASSWORD 	= "";
		
	}
	
	
	
	public interface CommonConfigs{
		public static final String LOG4JCONFIG = "src/com/resources/log4j.properties";
	}
	
	public interface Errors{
		public static final String ERROR="Error occured.";
	}
	
	public interface Success{
		public static final String SUCCESS="SUCCESS";
	}
}

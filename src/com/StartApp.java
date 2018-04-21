package com;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.SwingUtilities;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.config.Database;
import com.constants.Constants;
import com.dao.PersonDAO;
import com.dao.PersonDAOImpl;
import com.model.Person;
import com.view.MainWindow;


public class StartApp {

	final static Logger LOG = Logger.getLogger(StartApp.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure(loadConfigs().toString());
		
		LOG.info("Initializing the application...");
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// create of the class that need to be instantiated
				new MainWindow();
			}
		});	
		
		//test();
		
	}
	
	private static String loadConfigs(){
		Properties prop = new Properties();
		InputStream input = null;
		String jdbcDriver="", userName="", password="", url="",logFile="";

		try{
			input = new FileInputStream("src/com/resources/application.properties");
			prop.load(input);
			url = prop.getProperty("database.url");
			jdbcDriver = prop.getProperty("database.driver");
			userName = prop.getProperty("database.username");
			password = prop.getProperty("database.password");
			logFile = prop.getProperty("log4j.filepath");
			
			System.out.println("---- Fetched properties are ----" + url + "," + jdbcDriver + "," + userName + ","  + password + "," + logFile);
			
			new Database(jdbcDriver, userName, password, url);
			
		}catch(Exception e){
			System.out.println("#### Exception occurred while reading application properties file. ####" + e);
		}
		return logFile;
	}
	
	private static void test(){
		Person p1 = new Person(1, "A", "B", 15, "ab@gmail.com", "VNS");
		Person p2 = new Person(2, "Aa", "Bb", 20, "Aabb@gmail.com", "AGRA");
		Person p3 = new Person(3, "Aaa", "Bbb", 25, "AaaBbb@gmail.com", "Meerut");
		Person p4 = new Person(4, "Anshul", "Babu",35, "Anshul@gmail.com", "Banglore");
		
		PersonDAO pDAO = new PersonDAOImpl(); 
		try{

		/*	pDAO.save(p1); 
			pDAO.save(p2);
			pDAO.save(p3);
			System.out.println("----- Calling select statement for one person ---");
			System.out.println( pDAO.getOnePersonDetails(1).toString() );*/
			System.out.println("----- Calling update statement -----");
			System.out.println( pDAO.update(pDAO.compareUpdate(pDAO.getOnePersonDetails(3), p4)));
			System.out.println("----- Calling fetch all the records statement -----");
			System.out.println( pDAO.getAll());
			System.out.println("----- Fetch all completed -----------");
			
		}catch(Exception e){
			LOG.error("--- CANNOT SAVE THE DETAILS---" + e);
		}
	}
}

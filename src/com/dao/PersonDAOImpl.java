package com.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import com.config.Database;
import com.model.Person;
import com.mysql.jdbc.PreparedStatement;

public class PersonDAOImpl implements PersonDAO {

	final static Logger LOG = Logger.getLogger(PersonDAOImpl.class);
	Connection connection = null;

	@Override
	public Person getOnePersonDetails(Integer id) {
		String fetchOne = "SELECT ID, FIRSTNAME, LASTNAME, AGE, CITY, EMAIL FROM PERSON WHERE ID=?";
	//	String fetchOne = "SELECT * FROM PERSON WHERE ID = ?";
		PreparedStatement preparedStatement = null;
		Person personDetail = new Person();

		LOG.info("---- Fetching record of a user based on ID ------");

		try {
			connection = Database.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(fetchOne);
			preparedStatement.setInt(1, Integer.valueOf(id));
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				personDetail.setId(Integer.valueOf(rs.getString("ID")));
				personDetail.setFirstName(rs.getString("FIRSTNAME"));
				personDetail.setLastName(rs.getString("LASTNAME"));
				personDetail.setCity(rs.getString("CITY"));
				personDetail.setAge(Integer.valueOf(rs.getString("AGE")));
				personDetail.setEmail(rs.getString("EMAIL"));
			}
			connection.close();
			LOG.info("---- Appending records done -----");
		} catch (Exception e) {
			LOG.error("--- Exception occurred while fetching the file ---" + e);
		}
		return personDetail;
	}

	@Override
	public List<Person> getAll() {
		String fetchAll = "SELECT ID, FIRSTNAME, LASTNAME, AGE, CITY, EMAIL FROM PERSON";
		PreparedStatement preparedStatement = null;
		List<Person> personDetails = new ArrayList<Person>();
		Person personDetail = null;

		LOG.info("---- Fetching record of all users based on ID ------");

		try {
			connection = Database.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(fetchAll);
			ResultSet rs = preparedStatement.executeQuery(fetchAll);

			while (rs.next()) {
				personDetail = new Person();
				personDetail.setId(Integer.valueOf(rs.getString("ID")));
				personDetail.setFirstName(rs.getString("FIRSTNAME"));
				personDetail.setLastName(rs.getString("LASTNAME"));
				personDetail.setAge(Integer.valueOf(rs.getString("AGE")));
				personDetail.setCity(rs.getString("CITY"));
				personDetail.setEmail(rs.getString("EMAIL"));
				personDetails.add(personDetail);
			}

			connection.close();
			LOG.info("---- Appending records done -----");
		} catch (Exception e) {
			LOG.error("--- Exception occurred while fetching the details " + e.getMessage());
		}
		return personDetails;
	}

	@Override
	public int save(Person person) throws Exception {
		String insertTableSQL = "INSERT INTO PERSON (ID, FIRSTNAME, LASTNAME, AGE, CITY, EMAIL) VALUES (?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		int insert = 0 ;
		
		try {
			connection = Database.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = (PreparedStatement) connection.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, person.getId());
			preparedStatement.setString(2, person.getFirstName());
			preparedStatement.setString(3, person.getLastName());
			preparedStatement.setInt(4, person.getAge());
			preparedStatement.setString(5, person.getCity());
			preparedStatement.setString(6, person.getEmail());

			insert = preparedStatement.executeUpdate();

			LOG.info("---- Creating the records done -----");
			connection.commit();
			
		} catch (Exception e) {
			LOG.error("--- Exception occurred while inserting the details " + e.getMessage());
			connection.rollback();
		}finally {
			if(preparedStatement != null)
				preparedStatement.close();
			if(connection != null)
				connection.close();
			LOG.info("---- Finally execution completed INSERT -----");
		}
		return insert;
	}

	@Override
	public int update(Person person)throws Exception {
		String updateTableSQL = "UPDATE PERSON SET FIRSTNAME = ?, LASTNAME=?, AGE=?, CITY=?, EMAIL=? WHERE ID = ?";
		PreparedStatement preparedStatement = null;
		int update = 0 ;
		
		try {
			connection = Database.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = (PreparedStatement) connection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setInt(3, person.getAge());
			preparedStatement.setString(4, person.getCity());
			preparedStatement.setString(5, person.getEmail());
			preparedStatement.setInt(6, person.getId());

			update = preparedStatement.executeUpdate();

			LOG.info("---- Updating the records done -----");
			connection.commit();
			
		} catch (Exception e) {
			LOG.error("--- Exception occurred while updating the details " + e.getMessage());
			connection.rollback();
		}finally {
			if(preparedStatement != null)
				preparedStatement.close();
			if(connection != null)
				connection.close();
			LOG.info("---- Finally execution completed  UPDATE-----");
		}
		return update;
	}

	//compare and change only the value altered 
	@Override
	public Person compareUpdate(Person oldDetails, Person newDetails) {
		Person updatedValue = null;
		if(newDetails == null || oldDetails == null){
			LOG.info("--- Cannot compare, either object value entered is null -----");
			return updatedValue;
		}
		
		updatedValue = new Person();
		updatedValue.setId(oldDetails.getId());
		
		if(newDetails.getFirstName()== null || newDetails.getFirstName().equals("") || newDetails.getFirstName().equals(oldDetails.getFirstName()) ){
			updatedValue.setFirstName(oldDetails.getFirstName());
		}else{
			updatedValue.setFirstName(newDetails.getFirstName());
		}
		
		if(newDetails.getLastName()== null || newDetails.getLastName().equals("") || newDetails.getLastName().equals(oldDetails.getLastName())){
			updatedValue.setLastName(oldDetails.getLastName());
		}else{
			updatedValue.setLastName(newDetails.getLastName());
		}
		
		if(newDetails.getEmail() == null || newDetails.getEmail().equals("") || newDetails.getEmail().equals(oldDetails.getEmail())){
			updatedValue.setEmail(oldDetails.getEmail());
		}else{
			updatedValue.setEmail(newDetails.getEmail());
		}
		
		if(newDetails.getAge()== null || newDetails.getAge().equals("") || newDetails.getAge().equals(oldDetails.getAge())){
			updatedValue.setAge(oldDetails.getAge());
		}else{
			updatedValue.setAge(newDetails.getAge());
		}
		
		if(newDetails.getCity()== null || newDetails.getCity().equals("") || newDetails.getCity().equals(oldDetails.getCity())){
			updatedValue.setCity(oldDetails.getCity());
		}else{
			updatedValue.setCity(newDetails.getCity());
		}
		return updatedValue;
	}

	/*@Override
	public Person compareUpdate(Person oldDetails, Person newDetails) {
		Person updatedValue = null;
		if(newDetails == null || oldDetails == null){
			LOG.info("--- Cannot compare, object value entered is null -----");
			return updatedValue;
		}
		
		updatedValue = new Person();
		updatedValue.setId(oldDetails.getId());
		
		if(!(newDetails.getFirstName().equals(oldDetails.getFirstName()))){
			updatedValue.setFirstName(newDetails.getFirstName());
		}else{
			updatedValue.setFirstName(oldDetails.getFirstName());
		}
		
		if(!(newDetails.getLastName().equals(oldDetails.getLastName()))){
			updatedValue.setLastName(newDetails.getLastName());
		}else{
			updatedValue.setLastName(oldDetails.getLastName());
		}
		
		if(!(newDetails.getEmail().equals(oldDetails.getEmail()))){
			updatedValue.setEmail(newDetails.getEmail());
		}else{
			updatedValue.setEmail(oldDetails.getEmail());
		}
		
		if(!(newDetails.getAge().equals(oldDetails.getAge()))){
			updatedValue.setAge(newDetails.getAge());
		}else{
			updatedValue.setAge(oldDetails.getAge());
		}
		
		if(!(newDetails.getCity().equals(oldDetails.getCity()))){
			updatedValue.setCity(newDetails.getCity());
		}else{
			updatedValue.setCity(oldDetails.getCity());
		}
		return updatedValue;
	}*/

	
	@Override
	public Person delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.dao;


import java.util.List;

import com.model.Person;

public interface PersonDAO {
	
	Person getOnePersonDetails(Integer id);
	
	List<Person> getAll();	
	
	int save(Person person) throws Exception;

	int update(Person person) throws Exception;
	
	Person compareUpdate(Person oldDetails, Person newDetails);
	
	Person delete(Integer id);
	
}

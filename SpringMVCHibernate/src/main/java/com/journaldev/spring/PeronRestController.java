package com.journaldev.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;

@RestController
public class PeronRestController {
	private PersonService personService;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService ps){
		this.personService = ps;
	}
	
	@RequestMapping(value="/rohan", method=RequestMethod.GET,produces="APPLICATION/JSON")
    public List<Person> findAllPersons(){
    	
    	final List<Person> persons = this.personService.listPersons();
    	return persons;
    }
	
	@RequestMapping(value="/paged", method=RequestMethod.GET,produces="APPLICATION/JSON")
    public List<Person> findAllPersonsPagesd(@RequestParam("page") int page , @RequestParam("total") int total){
    	
    	final List<Person> persons = this.personService.listPaginatedPerson(page, total);
    	
    	
    	return persons;
    }
}

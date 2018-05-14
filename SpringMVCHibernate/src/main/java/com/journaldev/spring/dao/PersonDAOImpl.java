package com.journaldev.spring.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Person saved successfully, Person Details=" + p);
	}

	@Override
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Person updated successfully, Person Details=" + p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Person> personsList = session.createQuery("from Person").list();
		for (Person p : personsList) {
			logger.info("Person List::" + p);
		}
		
		List<Person> persons = (List<Person>) session.createQuery("From Person")
				.setFirstResult(1 - 1 * 1)
				.setMaxResults(1)
				.list();
		for (Person p : persons) {
			logger.info("Paged Person List::" + p);
		}

		return personsList;
	}

	@Override
	public Person getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Integer(id));
		logger.info("Person loaded successfully, Person details=" + p);
		return p;
	}

	@Override
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
		logger.info("Person deleted successfully, person details=" + p);
	}

	@Override
	public List<Person> listPaginatedPerson(int pageIndex, int rowsPerPage) {
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		List<Person> persons = (List<Person>) session.createQuery("From Person")
				.setFirstResult(pageIndex - 1 * rowsPerPage)
				.setMaxResults(rowsPerPage).list();
		return persons;

	}

}

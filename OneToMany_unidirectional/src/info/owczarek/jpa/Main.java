package info.owczarek.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import info.owczarek.jpa.domain.Employee;
import info.owczarek.jpa.domain.Phone;

public class Main {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();

		Employee employee = new Employee();
		Phone phone1 = new Phone();
		Phone phone2 = new Phone();
		employee.setFirstName("Jan");
		employee.setLastName("Nowak");
		employee.setSalary(1111.5);
		phone1.setType("mobile");
		phone2.setType("home");
		phone1.setNumber("999666333");
		phone2.setNumber("628884466");
		
		List<Phone> phones = new ArrayList<>();
		phones.add(phone1);
		phones.add(phone2);
		employee.setPhones(phones);
		
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.persist(phone1);
		entityManager.persist(phone2);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
}

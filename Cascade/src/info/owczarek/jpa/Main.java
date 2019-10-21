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
		employee.setFirstName("Jan");
		employee.setLastName("Nowak");
		employee.setSalary(1111.5);

		List<Phone> phones = new ArrayList<>();
		Phone phone1 = new Phone();
		phone1.setType("mobile");
		phone1.setNumber("999666333");
		Phone phone2 = new Phone();
		phone2.setType("home");
		phone2.setNumber("628884466");
		phones.add(phone1);
		phones.add(phone2);
		employee.setPhones(phones);

		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		//entityManager.persist(phone1);
		//entityManager.persist(phone2);
		entityManager.getTransaction().commit();

		entityManager.refresh(employee);

		entityManager.close();
		entityManagerFactory.close();
	}
}

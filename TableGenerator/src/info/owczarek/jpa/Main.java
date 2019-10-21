package info.owczarek.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import info.owczarek.jpa.domain.Employee;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Employee employee1 = new Employee("Jan", "Nowak", 4000.0);
		//employee1.setId(1L);
		Employee employee2 = new Employee("Anna", "Kwiatek", 2500.0);
		//employee1.setId(2L);
		Employee employee3 = new Employee("Piotr", "Majdan", 3300.0);
		//employee1.setId(3L);
		
		entityManager.getTransaction().begin();
		entityManager.persist(employee1);
		entityManager.persist(employee2);
		entityManager.persist(employee3);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}

}

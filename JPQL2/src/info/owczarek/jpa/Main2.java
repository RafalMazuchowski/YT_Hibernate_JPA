package info.owczarek.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import info.owczarek.jpa.domain.Employee;

public class Main2 {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();

		addEmployees();
		
		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Employee e where e.salary > :minSalary",
				Employee.class);
		query.setParameter("minSalary", 4000.0);
		for (Employee employee : query.getResultList()) {
			System.out.println(employee.getFirstName());
			System.out.println(employee.getLastName());
			System.out.println(employee.getSalary());
			System.out.println();
		}

		// *************************************
		System.out.println("*************************************");
		System.out.println();

		TypedQuery<Employee> query2 = entityManager.createQuery(
						"select e from Employee e where e.salary > ?1 and e.salary <?2", 
						Employee.class);
		query2.setParameter(1, 2500.0);
		query2.setParameter(2, 3000.0);
		for (Employee employee : query2.getResultList()) {
			System.out.println(employee.getFirstName());
			System.out.println(employee.getLastName());
			System.out.println(employee.getSalary());
			System.out.println();
		}

		// *************************************
		System.out.println("*************************************");
		System.out.println();
		
		TypedQuery<Employee> query3 = entityManager.createQuery(
				"select e from Employee e where e.lastName in :names",
				Employee.class);
		List<String> names = new ArrayList<>();
		names.add("Matusiewicz");
		names.add("Stañczyk");
		query3.setParameter("names", names);
		for (Employee employee : query3.getResultList()) {
			System.out.println(employee.getFirstName());
			System.out.println(employee.getLastName());
			System.out.println(employee.getSalary());
			System.out.println();
		}

		entityManager.close();
		entityManagerFactory.close();
	}

	private static void addEmployees() {
		addEmployee("Karol", "Matusiewicz", 2633);
		addEmployee("Marika", "Bednarek", 3598);
		addEmployee("Jan", "Matusiek", 4521);
		addEmployee("Daria", "K³odzka", 3654);
		addEmployee("Monika", "Usiedzka", 4522);
		addEmployee("Ernet", "¥cki", 3287);
		addEmployee("Kamil", "Paj¹k", 2964);
		addEmployee("Przemek", "Stañczyk", 2451);
		addEmployee("Rafa³", "Stêpien", 2684);
		addEmployee("Agnieszka", "Matusiewicz", 2934);
		addEmployee("Joanna", "Bednarska", 3166);

	}

	private static void addEmployee(String firstname, String lastname, double salary) {

		Employee employee = new Employee();
		employee.setFirstName(firstname);
		employee.setLastName(lastname);
		employee.setSalary(salary);

		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}
}

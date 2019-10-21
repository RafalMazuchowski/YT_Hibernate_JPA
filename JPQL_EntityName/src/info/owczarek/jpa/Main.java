package info.owczarek.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import domain.Employee;

public class Main {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();

		addEmployees();

		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Pracownik e where e.salary > :minSalary",
				Employee.class);
		query.setParameter("minSalary", 3000.0);
		
		List<Employee> employees = query.getResultList();
		for (Employee employee : employees) {
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

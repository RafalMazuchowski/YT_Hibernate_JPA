package info.owczarek.jpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import info.owczarek.jpa.domain.Employee;

public class Main {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();

		addEmployees();

		// *************************************
		TypedQuery<Employee> query = entityManager
				.createQuery("select e from Employee e where e.salary > 3000 order by e.salary", Employee.class);
		List<Employee> employees = query.getResultList();
		for (Employee employee : employees) {
			System.out.println(employee.getFirstName());
			System.out.println(employee.getLastName());
			System.out.println(employee.getSalary());
			System.out.println();
		}
		// *************************************
		System.out.println("*************************************");
		System.out.println();
		
		Query query2 = entityManager
				.createQuery("select concat(e.firstName, ' ', e.lastName), e.salary * 0.2 from Employee e");
		Iterator<?> iterator = query2.getResultList().iterator();
		while (iterator.hasNext()) {
			Object[] item = (Object[]) iterator.next();
			String name = (String) item[0];
			double tax = (double) item[1];
			System.out.println(name + " has to pay " + tax);
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

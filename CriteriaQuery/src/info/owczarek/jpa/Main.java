package info.owczarek.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import info.owczarek.jpa.domain.Employee;

public class Main {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();

		addEmployees();
		
		// select e from Employee e where e.salary >3000 and e.salary <5000

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
		Root<Employee> employee = criteriaQuery.from(Employee.class);
		
		Path<Double> salary = employee.get("salary");
		criteriaQuery.select(employee).where(builder.and(builder.greaterThan(salary, 3000.0)), builder.lessThan(salary, 5000.0));
		
		TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
		List<Employee> employees = query.getResultList();
		
		for (Employee emp : employees) {
			System.out.println(emp.getFirstName());
			System.out.println(emp.getLastName());
			System.out.println(emp.getSalary()+"\n");
		}
		
		entityManager.close();
		entityManagerFactory.close();
	}

	private static void addEmployees() {
		addEmployee("Karol", "Matusiewicz", 2633);
		addEmployee("Marika", "Bednarek", 5598);
		addEmployee("Jan", "Matusiek", 4521);
		addEmployee("Daria", "K³odzka", 3654);
		addEmployee("Monika", "Usiedzka", 4522);
		addEmployee("Ernet", "¥cki", 7287);
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

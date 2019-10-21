package info.owczarek.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import info.owczarek.jpa.domain.Employee;

public class Main {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();

		addEmployees();

		Query query = entityManager.createQuery(
				"select avg(e.salary), min(e.salary), max(e.salary), sum(e.salary), count(e) from Employee e");
		Object[] result = (Object []) query.getSingleResult();
		System.out.println("Œrednia: "+result[0]);
		System.out.println("Najni¿sza: "+result[1]);
		System.out.println("Najwy¿sza: "+result[2]);
		System.out.println("Suma: "+result[3]);
		System.out.println("Iloœæ pracowników: "+result[4]);
		System.out.println("");
		System.out.println("*******************************");
		System.out.println("");
		
		
		Query query2 = entityManager.createQuery(
				"select substring(e.firstName, 1, 3), trim(e.lastName), lower(e.firstName), upper(e.firstName), length(e.firstName) from Employee e where e.firstName = 'Karol'");
		Object[] result2 = (Object []) query2.getSingleResult();
		System.out.println("Pierwsze trzy litery imienia: " + result2[0]);
		System.out.println("Nazwisko: |" + result2[1] + "|");
		System.out.println("Imiê ma³ymi literami: " + result2[2]);
		System.out.println("Imiê du¿ymi literami: " + result2[3]);
		System.out.println("D³ugoœæ imienia: " + result2[4]);
		
		entityManager.close();
		entityManagerFactory.close();
	}

	private static void addEmployees() {
		addEmployee("Karol", "   Matusiewicz ", 2633);
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

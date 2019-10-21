package info.owczarek.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import info.owczarek.jpa.domain.Professor;
import info.owczarek.jpa.domain.Student;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Professor professor = new Professor();
		professor.setFirstName("Jan");
		professor.setLastName("Kulczyk");
		professor.setSalary(7568.0);

		Student student = new Student();
		student.setFirstName("Marek");
		student.setLastName("Kowalski");
		student.setAverageGrade(4.68);

		entityManager.getTransaction().begin();
		entityManager.persist(professor);
		entityManager.persist(student);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	}

}

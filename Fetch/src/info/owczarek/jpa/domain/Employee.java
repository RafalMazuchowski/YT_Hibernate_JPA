package info.owczarek.jpa.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private long id;  //domy�ln. EAGER

	private String firstName;  //domy�ln. EAGER
	private String lastName;  //domy�ln. EAGER
	private double salary;  //domy�ln. EAGER

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id")
	private List<Phone> phones;  //domy�ln. LAZY

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

}
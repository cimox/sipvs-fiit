package models;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;


@Data
public class Person {
	private String firstName;
	private String lastName;
	private String email;
	private String notification;
	private Address address;
	private List books = new LinkedList<Book>();

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List getBooks() {
		return books;
	}

	public void setBooks(LinkedList<Book> books) {
		this.books = books;
	}
}
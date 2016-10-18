package models;

public class Person {
	private String firstName;
	private String lastName;
	private String street;
	private Integer streetNumber;
	private String city;
	private String postalCode;
	private String state;
	private String email;
	private Integer notification;

	public Person(String firstName, String lastName, String street, Integer streetNumber, String city,
			String postalCode, String state, String email, Integer notification) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.postalCode = postalCode;
		this.state = state;
		this.email = email;
		this.notification = notification;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStreet() {
		return street;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getState() {
		return state;
	}

	public String getEmail() {
		return email;
	}

	public Integer getNotification() {
		return notification;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNotification(Integer notification) {
		this.notification = notification;
	}
}

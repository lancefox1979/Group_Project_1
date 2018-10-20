package edu.metrostate.ics372.gp1;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String MEMBER_STRING = "C";
	private String name;
	private String phoneNumber;
	private String id;

	/**
	 * Represents a single customer.
	 * 
	 * @param name
	 *            the customer's name
	 * @param phoneNumber
	 *            the customer's phone number
	 */
	public Customer(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		id = MEMBER_STRING + (CustomerIdServer.instance()).getId();
	}

	/**
	 * Setter for the customer's name.
	 * 
	 * @param name
	 *            customer's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for the customer's phone number.
	 * 
	 * @param phoneNumber
	 *            customer's new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Getter for the customer's name.
	 * 
	 * @return customer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the customer's phone number.
	 * 
	 * @return customer phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Getter for ID
	 * 
	 * @return customer ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * String form of the customer.
	 * 
	 */
	@Override
	public String toString() {
		return "Customer name: " + name + ", phone number: " + phoneNumber + ", ID: " + id + ".";
	}

	/**
	 * Checks whether the customer is equal to the one with the given id.
	 * 
	 * @param id
	 *            ID of the customer who should be compared
	 * @return true if the member IDs match
	 */
	public boolean equals(String id) {
		return this.id.equals(id);
	}
}

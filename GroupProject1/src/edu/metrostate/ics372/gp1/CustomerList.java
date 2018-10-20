package edu.metrostate.ics372.gp1;

public class CustomerList {

	private static CustomerList customerList;

	// Private constructor for singleton pattern
	private CustomerList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static CustomerList instance() {
		if (customerList == null) {
			return (customerList = new CustomerList());
		} else {
			return customerList;
		}
	}
}

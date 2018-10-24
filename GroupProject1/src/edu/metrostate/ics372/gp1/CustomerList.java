package edu.metrostate.ics372.gp1;

import java.util.Iterator;

/**
 * The CustomerList class is used to maintain a collection of customers.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class CustomerList extends ItemList<Customer, String> {
	private static final long serialVersionUID = 1L;
	private static CustomerList customerList;

	// Private constructor for singleton pattern.
	private CustomerList() {
	}

	/**
	 * Supports the singleton pattern.
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

	/**
	 * Inserts a customer into the collection.
	 * 
	 * @param customer
	 *            the customer to be inserted
	 * @return true if the customer could be inserted
	 */
	public boolean insertCustomer(Customer customer) {
		return super.add(customer);
	}
	
	/**
	 * Finds a customer from the collection.
	 * 
	 * @param customer id
	 *            the customer id be searched for
	 * @return true if the customer was found
	 */
	public boolean findUser(String customerID, CustomerList list) {
		return (list.search(customerID) != null);
	}

	/**
	 * String form of the CustomerList.
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<Customer> customers = this.iterator();
		while (customers.hasNext()) {
			stringBuilder.append(customers.next() + "\n");
		}
		return stringBuilder.toString();
	}
}

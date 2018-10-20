package edu.metrostate.ics372.gp1;

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
}

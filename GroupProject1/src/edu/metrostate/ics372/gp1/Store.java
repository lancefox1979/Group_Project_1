package edu.metrostate.ics372.gp1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * The Store class is used for calling the primary business functions of the
 * application. It keeps track of all customers and washers in the inventory.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;
	private Inventory inventory;
	private CustomerList customerList;
	private static Store store;

	/**
	 * Private constructor using the singleton pattern. Creates the inventory
	 * and customer collection objects.
	 */
	private Store() {
		inventory = Inventory.instance();
		customerList = CustomerList.instance();
	}

	/**
	 * Supports the singleton pattern.
	 * 
	 * @return the singleton object
	 */
	public static Store instance() {
		if (store == null) {
			CustomerIdServer.instance(); // instantiate all singletons
			return (store = new Store());
		} else {
			return store;
		}
	}

	/**
	 * Organizes the operations for adding a member
	 * 
	 * @param name
	 *            member name
	 * @param address
	 *            member address
	 * @param phone
	 *            member phone
	 * @return the Member object created
	 */
	public Customer addCustomer(String name, String phoneNumber) {
		Customer customer = new Customer(name, phoneNumber);
		if (customerList.insertCustomer(customer)) {
			return (customer);
		}
		return null;
	}

	/**
	 * Retrieves a deserialized version of the Store from disk.
	 * 
	 * @return a Store object
	 */
	public static Store retrieve() {
		try {
			FileInputStream file = new FileInputStream("StoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			store = (Store) input.readObject();
			CustomerIdServer.retrieve(input);
			return store;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Serializes the Store object.
	 * 
	 * @return true if the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("StoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(store);
			output.writeObject(CustomerIdServer.instance());
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * String form of the Store.
	 * 
	 */
	@Override
	public String toString() {
		return inventory + "\n" + customerList;
	}
}

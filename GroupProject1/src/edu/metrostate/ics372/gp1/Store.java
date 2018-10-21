package edu.metrostate.ics372.gp1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * The Store class is used for calling the primary business functions of the
 * application. It keeps track of all customers, back orders, and washers in the
 * inventory.
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
	private WasherList washerList;
	private static Store store;

	/**
	 * Private constructor using the singleton pattern. Creates the inventory
	 * and customer collection objects.
	 */
	private Store() {
		inventory = Inventory.instance();
		customerList = CustomerList.instance();
		washerList = WasherList.instance();
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
	 * Organizes the operations for adding a customer.
	 * 
	 * @param name
	 *            customer name
	 * @param address
	 *            customer address
	 * @param phone
	 *            customer phone
	 * @return the Customer object created
	 */
	public Customer addCustomer(String name, String phoneNumber) {
		Customer customer = new Customer(name, phoneNumber);
		if (customerList.insertCustomer(customer)) {
			return (customer);
		}
		return null;
	}

	/**
	 * Organizes the operations for adding a washer.
	 * 
	 * @param brand
	 *            washer brand
	 * @param model
	 *            washer model
	 * @param price
	 *            washer price
	 * @return true if the washer model and brand could be added
	 */
	public Washer addWasher(String brand, String model, double price) {
		Washer washer = new Washer(brand, model, price);
		if (washerList.insertWasher(washer)) {
			return (washer);
		}
		return null;
	}

	/**
	 * Organizes the operations for adding a washer to the inventory.
	 * 
	 * @param washer
	 *            the washer to add to the inventory
	 * @param quantity
	 *            the number of washers to add
	 * @return true if the washer could be added to the inventory
	 */
	public boolean addWasherToInventory(Washer washer, int quantity) {
		boolean result = inventory.insertWasher(washer, quantity);
		if (result) {
			processBackOrders(washer.getId());
		}
		return result;
	}

	/**
	 * Organizes the operations for displaying all customers in the system.
	 * 
	 * @return a list of all customers in the system
	 */
	public String listCustomers() {
		return customerList.toString();
	}

	/**
	 * Organizes the operations for displaying all washers in the inventory.
	 * 
	 * @return a list of all washers in the inventory
	 */
	public String listWashers() {
		return inventory.getAllWashers();
	}

	/**
	 * Processes back orders for a single washer.
	 * 
	 * @param washerId
	 *            id of the washer
	 * @return the customer who should be notified the order has been fulfilled
	 */
	public Customer processBackOrders(String washerId) {
		Washer washer = inventory.search(washerId);
		if (washer == null) {
			return (null);
		}
		BackOrder backOrder = washer.getNextBackOrder();
		if (backOrder == null) {
			return (null);
		}
		backOrder.getCustomer().removeBackOrder(washerId);
		backOrder.getWasher().removeBackOrder(backOrder.getCustomer().getId());
		return (backOrder.getCustomer());
	}

	/**
	 * Searches for a given washer.
	 * 
	 * @param washerId
	 *            ID of the washer
	 * @return true if the washer is in the washer collection
	 */
	public Washer searchWashers(String washerId) {
		return washerList.search(washerId);
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

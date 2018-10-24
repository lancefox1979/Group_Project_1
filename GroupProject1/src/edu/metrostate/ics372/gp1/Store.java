package edu.metrostate.ics372.gp1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private CustomerList customerList;
	private WasherList washerList;
	private static Store store;
	private double totalSales;
	private List<Transaction> transactionsList = new ArrayList<>();
	private Transaction transaction;
	private Customer customer;
	private Washer washer;

	/**
	 * Private constructor using the singleton pattern. Creates the inventory,
	 * customer, washer, and back order collection objects.
	 */
	private Store() {
		customerList = CustomerList.instance();
		washerList = WasherList.instance();
		totalSales = 0.0;
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
	public boolean addCustomer(Customer customer) {
		return customerList.insertCustomer(customer);
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
	public boolean addWasher(Washer washer) {
		return washerList.insertWasher(washer);
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
	public void addWasherToInventory(Washer washer, int quantity) {
		washer.updateQuantity(quantity);
		if (washer.isBackOrdered() && washer.getQuantity() >= washer.backOrderList.peek().getQuantity()) {
			processBackOrder(washer);
		}
	}

	/*
	 * Will process back order once there are available units. If all units are
	 * available for back order processing, a transaction will be created to
	 * sell all of the units. If there are still not enough units to satisfy the
	 * back order, then units available will be processed and the remainder will
	 * be still back ordered.
	 */
	public void processBackOrder(Washer washer) {
		boolean isBackOrder = true;
		customer = washer.backOrderList.peek().getCustomer();
		transaction = new Transaction(customer, washer, washer.backOrderList.peek().getQuantity(), isBackOrder);
		washer.backOrderList.poll();
		washer.setBackOrdered(false);
		transactionsList.add(transaction);
		System.out.println(transaction);

	}

	/*
	 * Processes the a purchase transaction of the customer
	 */
	public void purchaseWasher(String customerId, String brand, String model, int quantity) {
		boolean isBackOrder = false;
		customer = searchCustomers(customerId);
		washer = searchWashers(brand + model);
		transaction = new Transaction(customer, washer, quantity, isBackOrder);
		transactionsList.add(transaction);
		System.out.println(transaction);
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
		return washerList.toString();
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
	 * Searches to see if there is a given washer
	 */
	public boolean isAWasher(String washerId) {
		return (washerList.search(washerId) != null);
	}

	/**
	 * Getter method to retrieve the total sales.
	 * 
	 * @return the total sales
	 */
	public double getTotalSales() {
		for (Transaction transaction : transactionsList) {
			totalSales += transaction.getAmount();
		}
		return totalSales;
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
			// ioe.printStackTrace();
			System.out.println("File doesnt exist; creating new store...");
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

	/*
	 * Checks to see if customer id is valid
	 */
	public boolean isACustomer(String id) {
		return customerList.findUser(id, customerList);
	}

	/*
	 * Gets a customer from the collection
	 */
	public Customer searchCustomers(String customerId) {
		return (customerList.search(customerId));
	}

	/**
	 * String form of the Store.
	 * 
	 */
	@Override
	public String toString() {
		return "";
	}
}

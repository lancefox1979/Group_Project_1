package edu.metrostate.ics372.gp1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

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
	private BackOrderList backOrderList;
	private static Store store;
	private double totalSales = 0.0;

	/**
	 * Private constructor using the singleton pattern. Creates the inventory,
	 * customer, washer, and back order collection objects.
	 */
	private Store() {
		inventory = Inventory.instance();
		customerList = CustomerList.instance();
		washerList = WasherList.instance();
		backOrderList = BackOrderList.instance();
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
	public boolean addWasherToInventory(String brand, String model, int quantity) {
		Washer washer = searchWashers(brand + model);
		boolean result = inventory.insertWasher(washer, quantity);
		if (result) {
			processBackOrders();
		}
		return result;
	}

	public boolean purchaseWasher(String id, String brand, String model, int quantity) {
		Washer washer = store.searchWashers(brand + model);
		Customer customer = null;
		boolean purchase = customerList.findUser(id, customerList);
		Iterator<Customer> customers = customerList.iterator();
		
		if (purchase) {
			if (washer == null) {
				System.out.println("No such washer exists.");
				return false;
			} else {
				purchase = inventory.findWasher(brand, model, quantity);
			}
			while (customers.hasNext()) {
				Customer nextCustomer = customers.next();
				if (nextCustomer.matches(id)) {
					customer = nextCustomer;
				}
			}
			if (purchase) {
				int count = quantity;
				while (count != 0) {
					Iterator<Washer> washers = washerList.iterator();
					while (washers.hasNext()) {
						Washer nextWasher = washers.next();
						if (nextWasher.matches(washer.getBrand() + washer.getModel())) {
							customer.purchase(nextWasher);
							totalSales += nextWasher.getPrice();
						}
					}
					count--;
				}	
				inventory.updateQuantity(washer.getBrand(), washer.getModel(), quantity);
			} else {
				if (addToBackOrder(customer, washer, quantity)) {
					System.out.println("Not enough of " + brand + " " + model + " in stock. Back order placed for " + quantity + " units.");
				} else {
					System.out.println("The back order could not be placed.");
				}

			}
		} else {
			System.out.println("Invalid Customer ID.");
		}

		return purchase;
	}

	// Private helper method to quickly add a back order.
	private boolean addToBackOrder(Customer customer, Washer washer, int quantity) {
		return backOrderList.insertBackOrder(new BackOrder(customer, washer, quantity));
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
		Iterator<Washer> washers = inventory.getAllWashers();
		Iterator<Washer> washerLog = washerList.iterator();
		StringBuilder stringBuilder = new StringBuilder();
		Map<Washer,Integer> washerCount = new LinkedHashMap<Washer,Integer>();
		while (washerLog.hasNext()) {
			washerCount.put(washerLog.next(), 0);
		}
		while (washers.hasNext()) {
			washerCount.merge(washers.next(), 1, (x, y) -> x + y);
		}
		for (Map.Entry<Washer,Integer> entry : washerCount.entrySet()) {
			stringBuilder.append(entry.getKey() + " inventory count: " + entry.getValue() +"\n");
		}
		
		return stringBuilder.toString();
	}

	/**
	 * Processes back orders for the store.
	 */
	public void processBackOrders() {
		Iterator<BackOrder> backOrderLog = backOrderList.iterator();
		
		while (backOrderLog.hasNext()) {
			BackOrder backOrder = backOrderLog.next();
			Customer customer = backOrder.getCustomer();
			Washer washer = backOrder.getWasher();
			int quantity = backOrder.getQuantity();
			boolean purchase = inventory.findWasher(washer.getBrand(), washer.getModel(), quantity);
			
			if (purchase) {
				double sale = 0.0;
				int count = quantity;
				while (count != 0) {
					Iterator<Washer> washers = washerList.iterator();
					while (washers.hasNext()) {
						Washer temp = washers.next();
						if (temp.matches(washer.getBrand() + washer.getModel())) {
							customer.purchase(temp);
							totalSales += temp.getPrice();
							sale += temp.getPrice();
						}
					}
					count--;
				}	
				inventory.updateQuantity(washer.getBrand(), washer.getModel(), quantity);
				System.out.println("A backorder for " + customer.getId() + " purchasing " + quantity + " of " + washer+" has been processed for: " + String.format("$%.2f.%n", (float)sale));
			}
		}
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
	 * Getter method to retrieve the total sales.
	 * 
	 * @return the total sales
	 */
	public double getTotalSales() {
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

	/**
	 * String form of the Store.
	 * 
	 */
	@Override
	public String toString() {
		return inventory + "\n" + customerList;
	}
}

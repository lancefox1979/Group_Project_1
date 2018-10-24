package edu.metrostate.ics372.gp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * The UserInterface class contains the main entry point, and provides end-users
 * a means of interfacing with the application through the command-line.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Store store;
	private static final int EXIT = 0;
	private static final int ADD_CUSTOMER = 1;
	private static final int ADD_MODEL = 2;
	private static final int ADD_TO_INVENTORY = 3;
	private static final int PURCHASE = 4;
	private static final int LIST_CUSTOMERS = 5;
	private static final int LIST_WASHERS = 6;
	private static final int DISPLAY_TOTAL = 7;
	private static final int SAVE = 8;
	private Washer washer;
	private Customer customer;
	private String brand;
	private String model;
	private double price;
	private String customerId;
	private String name;
	private String phoneNumber;
	private int quantity;

	/**
	 * Made private for the singleton pattern. Conditionally looks for any saved
	 * data. Otherwise, it gets a singleton Store object.
	 * 
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and use it?")) {
			retrieve();
		} else {
			store = Store.instance();
		}
	}

	/**
	 * Supports the singleton pattern.
	 * 
	 * @return the singleton object
	 * 
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Method to be called for adding a customer. Prompts the user for the
	 * appropriate values and uses the appropriate Store method for adding the
	 * customer.
	 * 
	 */
	public void addCustomer() {
		do {
			customer = new Customer(getAndSetName(), getAndSetPhoneNumber());
			if (store.addCustomer(customer)) {
				System.out.println(customer + "has been added.");
			} else {
				System.out.println("Could not add customer");
			}
		} while (yesOrNo("Would you like to add another Customer?"));
	}

	/**
	 * Method to be called for adding a washer. Prompts the user for the
	 * appropriate values and uses the appropriate Store method for adding the
	 * washer.
	 * 
	 */
	public void addWasher() {
		do {
			washer = new Washer(getAndSetBrand(), getAndSetModel(), getAndSetPrice());
			if (store.addWasher(washer)) {
				System.out.println(washer + "has been added");
			}
		} while (yesOrNo("Would you like to add another washer?"));
	}

	/**
	 * Method to be called for adding a washer to the inventory. Prompts the
	 * user for the appropriate values and uses the appropriate Inventory method
	 * for adding the washer.
	 * 
	 */
	public void addToInventory() {
		do {
			washer = store.searchWashers(getAndSetBrand() + getAndSetModel());
			if (washer == null) {
				System.out.println("No such washer exists.");
				return;
			}
			store.addWasherToInventory(washer, getAndSetQuantity());
		} while (yesOrNo("Add more washers to the inventory?"));
	}

	/**
	 * Method to be called for purchasing a washer. Prompts the user for the
	 * appropriate values and uses the appropriate Store method for executing
	 * the sale.
	 * 
	 * Purchase: The actor identifies the washer by its brand and model names
	 * and the customer by the customer id. The actor enters the quantity as
	 * well. If there is enough on stock, the purchase is immediate. Otherwise,
	 * this goes on back order.
	 * 
	 */
	public void purchase() {
		try {
			do {
				if (store.isACustomer(getAndSetId()) && store.isAWasher(getAndSetBrand() + getAndSetModel())) {
					store.purchaseWasher(customerId, brand, model, getAndSetQuantity());
				} else {
					throw new NoSuchElementException();
				}
			} while (yesOrNo("Make another Purchase?"));

		} catch (NoSuchElementException e) {
			System.out.println("Invalid input, please check Customer ID, Washer Brand, or Washer Model...");
		}
	}

	/**
	 * Method to be called for displaying a list of all customers in the system.
	 * 
	 */
	public void listCustomers() {
		System.out.println(store.listCustomers());
	}

	/**
	 * Method to be called for displaying a list of all washers in the
	 * inventory.
	 * 
	 */
	public void listWashers() {
		System.out.println(store.listWashers());
	}

	/**
	 * Method to be called for displaying the total washer sales.
	 * 
	 */
	public void displayTotal() {
		System.out.println(String.format("Total Sales: $%-10.2f", store.getTotalSales()));
	}

	/**
	 * Displays the menu screen.
	 * 
	 */
	public void displayMenu() {
		System.out.println("Enter a number between 0 and 8 as explained below: \n");
		System.out.println("[" + ADD_CUSTOMER + "] Add a customer.");
		System.out.println("[" + ADD_MODEL + "] Add a model.");
		System.out.println("[" + ADD_TO_INVENTORY + "] Add a washer to inventory.");
		System.out.println("[" + PURCHASE + "] Purchase a washer.");
		System.out.println("[" + LIST_CUSTOMERS + "] Display all customers.");
		System.out.println("[" + LIST_WASHERS + "] Display all washers.");
		System.out.println("[" + DISPLAY_TOTAL + "] Display total sales.");
		System.out.println("[" + SAVE + "] Save data.");
		System.out.println("[" + EXIT + "] to Exit");
	}

	/**
	 * Method to be called for saving the Store object. Uses the appropriate
	 * Store method for saving.
	 * 
	 */
	private void save() {
		if (Store.save()) {
			System.out.println(" > The store has been successfully saved in the file StoreData.\n");
		} else {
			System.out.println(" > An error occurred during saving.\n");
		}
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate Store
	 * method for retrieval.
	 * 
	 */
	private void retrieve() {
		try {
			if (store == null) {
				store = Store.retrieve();
				if (store != null) {
					System.out.println(" The store has been successfully retrieved from the file StoreData. \n");
				} else {
					store = Store.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Orchestrates the whole process. Calls the appropriate method for the
	 * different functionalities.
	 * 
	 */
	public void process() {
		displayMenu();
		int command = getCommand();
		while (command != EXIT) {
			switch (command) {
			case ADD_CUSTOMER:
				addCustomer();
				break;
			case ADD_MODEL:
				addWasher();
				break;
			case ADD_TO_INVENTORY:
				addToInventory();
				break;
			case PURCHASE:
				purchase();
				break;
			case LIST_CUSTOMERS:
				listCustomers();
				break;
			case LIST_WASHERS:
				listWashers();
				break;
			case DISPLAY_TOTAL:
				displayTotal();
				break;
			case SAVE:
				save();
				break;
			}
			displayMenu();
			command = getCommand();
		}
		System.out.println("Goodbye.");
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt
	 *            - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no.
	 * 
	 * @param prompt
	 *            The string to be prepended to the yes/no prompt.
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Converts the String to an integer.
	 * 
	 * @param prompt
	 *            the String for prompting
	 * @return the integer corresponding to the String.
	 * 
	 */
	public int getInteger(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number.");
			}
		} while (true);
	}

	/**
	 * Converts the String to a double.
	 * 
	 * @param prompt
	 *            the String for prompting
	 * @return the double corresponding to the String.
	 * 
	 */
	public double getDouble(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Double number = Double.valueOf(item);
				return number.doubleValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number.");
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken(""));
				if (value >= EXIT && value <= SAVE) {
					return value;
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException nfe) {
				System.out.print("Invalid entry please try again.");
			}
		} while (true);
	}

	/*
	 * Prompts clerk for brand
	 */
	private String getAndSetBrand() {
		this.brand = getToken("Enter washer brand: ").toUpperCase().trim();
		return this.brand;
	}

	/*
	 * Prompts clerk for model
	 */
	private String getAndSetModel() {
		this.model = getToken("Enter washer model: ").toUpperCase().trim();
		return this.model;
	}

	/*
	 * Prompts clerk for Price
	 */
	private double getAndSetPrice() {
		this.price = getDouble("Enter washer price(in dollars): ");
		return this.price;
	}

	/*
	 * Prompts clerk for ID
	 */
	private String getAndSetId() {
		this.customerId = getToken("Enter customer id: ").toUpperCase().trim();
		return this.customerId;
	}

	/*
	 * Prompts clerk for Name
	 */
	private String getAndSetName() {
		this.name = getToken("Enter a Name: ").toUpperCase().trim();
		return this.name;
	}

	/*
	 * Prompts clerk for Phone Number
	 */
	private String getAndSetPhoneNumber() {
		this.phoneNumber = getToken("Enter a Phone Number: ");
		return this.phoneNumber;
	}

	private int getAndSetQuantity() {
		this.quantity = getInteger("Enter quantity: ");
		while (this.quantity <= 0) {
			System.out.println("Please enter a positive Quanitity");
			getAndSetQuantity();
		}
		return this.quantity;
	}

	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}

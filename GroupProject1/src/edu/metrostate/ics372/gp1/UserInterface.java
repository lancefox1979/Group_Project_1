package edu.metrostate.ics372.gp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Store store;
	private static final int EXIT = 0;
	private static final int ADD_CUSTOMER = 1;
	private static final int ADD_WASHER = 2;
	private static final int ADD_TO_INVENTORY = 3;
	private static final int PURCHASE = 4;
	private static final int LIST_CUSTOMERS = 5;
	private static final int LIST_WASHERS = 6;
	private static final int DISPLAY_TOTAL = 7;
	private static final int SAVE = 8;
	private static final int HELP = 9;

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
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command [" + HELP + "] for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 9 as explained below: \n");
		System.out.println("[" + EXIT + "] to Exit\n");
		System.out.println("[" + ADD_CUSTOMER + "] Add a customer.");
		System.out.println("[" + ADD_WASHER + "] Add a washer.");
		System.out.println("[" + ADD_TO_INVENTORY + "] Add a washer to inventory.");
		System.out.println("[" + PURCHASE + "] Purchase a washer.");
		System.out.println("[" + LIST_CUSTOMERS + "] Display all customers.");
		System.out.println("[" + LIST_WASHERS + "] Display all washers.");
		System.out.println("[" + DISPLAY_TOTAL + "] Display total sales.");
		System.out.println("[" + SAVE + "] Save data.");
		System.out.println("[" + HELP + "] Help menu.");
	}

	/**
	 * Method to be called for adding a customer. Prompts the user for the
	 * appropriate values and uses the appropriate Store method for adding the
	 * customer.
	 * 
	 */
	public void addCustomer() {
		String name = getToken("Enter the customer's name: ");
		String phoneNumber = getToken("Enter the phone number: ");
		Customer result;
		result = store.addCustomer(name, phoneNumber);
		if (result == null) {
			System.out.println("Could not add customer.");
		}
		System.out.println(result);
	}

	/**
	 * Method to be called for adding a washer. Prompts the user for the
	 * appropriate values and uses the appropriate Store method for adding the
	 * washer.
	 * 
	 */
	public void addWasher() {
		// TODO: Implementation...
	}

	/**
	 * Method to be called for adding a washer to the inventory. Prompts the
	 * user for the appropriate values and uses the appropriate Inventory method
	 * for adding the washer.
	 * 
	 */
	public void addToInventory() {
		// TODO: Implementation...
	}

	/**
	 * Method to be called for purchasing a washer. Prompts the user for the
	 * appropriate values and uses the appropriate Store method for executing
	 * the sale.
	 * 
	 */
	public void purchase() {
		// TODO: Implementation...
	}

	/**
	 * Method to be called for displaying a list of all customers.
	 * 
	 */
	public void listCustomers() {
		// TODO: Implementation...
	}

	/**
	 * Method to be called for displaying a list of all washers in the
	 * inventory.
	 * 
	 */
	public void listWashers() {
		// TODO: Implementation...
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
	 * Method to be called for displaying the total washer sales.
	 * 
	 */
	public void displayTotal() {
		// TODO: Implementation...
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
					System.out.println(" The store has been successfully retrieved from the file StoreData \n");
				} else {
					System.out.println("File doesnt exist; creating new store...");
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
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_CUSTOMER:
				addCustomer();
				break;
			case ADD_WASHER:
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
			case HELP:
				help();
				break;
			}
		}
		System.out.println("Goodbye.");
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

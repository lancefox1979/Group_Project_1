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
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
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
		System.out.println("Enter a number between 0 and 12 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_CUSTOMER + " to add a customer.");
		System.out.println(ADD_WASHER + " to  add a washer.");
		System.out.println(ADD_TO_INVENTORY + " to add a washer to the inventory.");
		System.out.println(PURCHASE + " to purchase a washer.");
		System.out.println(LIST_CUSTOMERS + " to display all customers.");
		System.out.println(LIST_WASHERS + " to display all washers.");
		System.out.println(DISPLAY_TOTAL + " to display total sales.");
		System.out.println(SAVE + " to save data.");
		System.out.println(HELP + " for help.");
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
				// TODO: Implement method...
				// addCustomer();
				break;
			case ADD_WASHER:
				// TODO: Implement method...
				// addWasher();
				break;
			case ADD_TO_INVENTORY:
				// TODO: Implement method...
				// addToInventory();
				break;
			case PURCHASE:
				// TODO: Implement method...
				// purchase();
				break;
			case LIST_CUSTOMERS:
				// TODO: Implement method...
				// listCustomers();
				break;
			case LIST_WASHERS:
				// TODO: Implement method...
				// listWashers();
				break;
			case DISPLAY_TOTAL:
				// TODO: Implement method...
				// displayTotal();
				break;
			case SAVE:
				// TODO: Implement method...
				// save();
				break;
			case HELP:
				help();
				break;
			}
		}
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

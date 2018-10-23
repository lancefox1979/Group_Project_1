package edu.metrostate.ics372.gp1;

import java.util.Iterator;

/**
 * The Inventory class is used to maintain a collection of washers and their
 * respective quantities currently in the inventory.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class Inventory extends ItemList<Washer, String> {

	private static final long serialVersionUID = 1L;
	private static Inventory inventory;

	// Private constructor for the singleton pattern.
	private Inventory() {
	}

	/**
	 * Supports the singleton pattern.
	 * 
	 * @return the singleton object
	 */
	public static Inventory instance() {
		if (inventory == null) {
			return (inventory = new Inventory());
		} else {
			return inventory;
		}
	}

	/**
	 * Inserts a washer into the collection.
	 * 
	 * @param washer
	 *            the washer to be inserted
	 * @return true if the washer could be inserted
	 */
	public boolean insertWasher(Washer washer, int quantity) {
		return super.add(washer, quantity);
	}

	/**
	 * Updates the quantity of a washer for a specified quantity.
	 * 
	 * @param brand
	 *            the washer brand
	 * @param model
	 *            the washer model
	 * @param quantity
	 *            quantity to remove
	 */
	public void updateQuantity(String brand, String model, int quantity) {
		Iterator<Washer> washers = this.iterator();
		while (washers.hasNext()) {
			Washer washer = washers.next();
			if (washer.matches(brand + model) && (quantity != 0)) {
				washers.remove();
				quantity--;
			}
		}
	}

	/**
	 * Finds a washer from the collection.
	 * 
	 * @param brand
	 *            the washer brand
	 * @param model
	 *            the washer model
	 * @param quantity
	 *            quantity desired to purchase
	 * @return true if the washer was found with quantity met
	 */
	public boolean findWasher(String brand, String model, int quantity) {
		Iterator<Washer> washers = this.iterator();
		boolean found = false;
		int count = 0;
		while (washers.hasNext()) {
			Washer washer = washers.next();
			if (washer.matches(brand + model)) {
				count++;
			}
		}

		if (count >= quantity) {
			found = true;
		}
		return found;
	}

	/**
	 * Returns all washers and their quantities currently in the inventory.
	 * 
	 * @return all washers in the inventory
	 */
	public String getAllWashers() {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<Washer> washers = this.iterator();
		while (washers.hasNext()) {
			stringBuilder.append(washers.next() + "\n");
		}
		return stringBuilder.toString();
	}
}

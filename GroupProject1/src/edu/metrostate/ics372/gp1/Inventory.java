package edu.metrostate.ics372.gp1;

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
	 * Returns all washers and their quantities currently in the inventory.
	 * 
	 * @return all washers in the inventory
	 */
	public String getAllWashers() {
		// TODO: Implementation required.
		return "";
	}
}

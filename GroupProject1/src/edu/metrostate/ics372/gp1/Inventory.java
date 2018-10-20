package edu.metrostate.ics372.gp1;

/**
 * The Inventory class is used to maintain a collection of washers.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class Inventory extends ItemList<Washer> {

	private static final long serialVersionUID = 1L;
	private static Inventory inventory;

	// Private constructor for the singleton pattern
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
	public boolean insertWasher(Washer washer) {
		return super.add(washer);
	}
}

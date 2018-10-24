package edu.metrostate.ics372.gp1;

import java.util.Iterator;

/**
 * The WasherList class is used to maintain a collection of washers.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class WasherList extends ItemList<Washer, String> {
	private static final long serialVersionUID = 1L;
	private static WasherList washerList;

	// Private constructor for singleton pattern.
	private WasherList() {
	}

	/**
	 * Supports the singleton pattern.
	 * 
	 * @return the singleton object
	 */
	public static WasherList instance() {
		if (washerList == null) {
			return (washerList = new WasherList());
		} else {
			return washerList;
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
		if (washerIsUnique(washer)) {
			return super.add(washer);
		} else {
			return false;
		}
	}

	/*
	 * Checks to see if washer brand and model exists. If the brand and model
	 * already exists and a new price is defined. The system will update the
	 * price of the washer to the new price indicated.
	 */
	private boolean washerIsUnique(Washer newWasher) {
		for (Washer washer : getList()) {
			if (newWasher.getBrand().equals(washer.getBrand()) && newWasher.getModel().equals(washer.getModel())) {
				System.out.println("Washer Brand and Model already exists.");
				if (newWasher.getPrice() == washer.getPrice()) {
					return false;
				} else {
					washer.setPrice(newWasher.getPrice());
					System.out.println("Updating the new washer price...");
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Returns the list of Washers for the clerk
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<Washer> washers = this.iterator();
		while (washers.hasNext()) {
			Washer washer = washers.next();
			stringBuilder.append(washer + "Quantity: " + washer.getQuantity() + "\n");
		}
		if (stringBuilder.length() > 0) {
			return stringBuilder.toString();
		} else {
			return "No Washers Found";
		}
	}
}

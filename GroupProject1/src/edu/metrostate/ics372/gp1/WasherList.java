package edu.metrostate.ics372.gp1;

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
	public boolean washerIsUnique(Washer newWasher) {
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
}

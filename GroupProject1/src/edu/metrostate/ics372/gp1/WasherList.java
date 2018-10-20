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
		return super.add(washer);
	}
}

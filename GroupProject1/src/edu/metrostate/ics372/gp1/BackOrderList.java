package edu.metrostate.ics372.gp1;

/**
 * The BackOrderList class is used to maintain a collection of back orders.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class BackOrderList extends ItemList<BackOrder, String> {
	private static final long serialVersionUID = 1L;
	private static BackOrderList backOrderList;

	// Private constructor for singleton pattern.
	private BackOrderList() {
	}

	/**
	 * Supports the singleton pattern.
	 * 
	 * @return the singleton object
	 */
	public static BackOrderList instance() {
		if (backOrderList == null) {
			return (backOrderList = new BackOrderList());
		} else {
			return backOrderList;
		}
	}

	/**
	 * Inserts a back order into the collection.
	 * 
	 * @param backOrder
	 *            the back order to be inserted
	 * @return true if the back order could be inserted
	 */
	public boolean insertBackOrder(BackOrder backOrder) {
		return super.add(backOrder);
	}
}

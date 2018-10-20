package edu.metrostate.ics372.gp1;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * The ItemList class is a base class used to manage generic collections.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class ItemList<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> list = new LinkedList<T>();

	protected ItemList() {
	}

	/**
	 * Adds an item to the list.
	 * 
	 * @param item
	 *            the item to be added
	 * @return true if the item could be added
	 */
	public boolean add(T item) {
		return list.add(item);
	}
}

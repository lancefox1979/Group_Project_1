package edu.metrostate.ics372.gp1;

import java.io.Serializable;
import java.util.Iterator;
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
public class ItemList<T extends IMatchable<K>, K> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> list = new LinkedList<T>();

	protected ItemList() {
	}

	/**
	 * Checks whether an item with a given ID exists.
	 * 
	 * @param key
	 *            the ID of the item
	 * @return the item if the item exists
	 * 
	 */
	public T search(K key) {
		for (T item : list) {
			if (item.matches(key)) {
				return item;
			}
		}
		return null;
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

	/**
	 * Adds item of the specified quantity to the list.
	 * 
	 * @param item
	 *            the item to be added
	 * @param item
	 *            the number of items to be added
	 * @return true if the item could be added
	 */
	public boolean add(T item, int quantity) {
		List<T> manyItems = new LinkedList<T>();
		for (int i = 0; i < quantity; i++) {
			manyItems.add(item);
		}
		return list.addAll(manyItems);
	}

	/**
	 * Returns an iterator for this collection.
	 * 
	 * @return iterator for this collection
	 */
	public Iterator<T> iterator() {
		return list.iterator();
	}

	public List<T> getList() {
		return list;
	}
}

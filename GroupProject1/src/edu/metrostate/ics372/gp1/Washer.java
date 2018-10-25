package edu.metrostate.ics372.gp1;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * The Washer class is used to manage a single washer.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class Washer implements Serializable, IMatchable<String> {
	private static final long serialVersionUID = 1L;
	private String brand;
	private String model;
	private String id;
	private double price;
	private List<BackOrder> backOrders = new LinkedList<BackOrder>();

	/**
	 * Represents a single washer.
	 * 
	 * @param brand
	 *            the washer's brand
	 * @param model
	 *            the washer's model
	 * @param price
	 *            the washer's price
	 */
	public Washer(String brand, String model, double price) {
		this.brand = brand;
		this.model = model;
		this.id = brand + model;
		this.price = price;
	}

	/**
	 * Getter for the washer's brand.
	 * 
	 * @return washer brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Getter for the washer's model.
	 * 
	 * @return washer model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Getter for the washer's ID.
	 * 
	 * @return washer ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter for the washer's price.
	 * 
	 * @return washer price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Adds one back order to this washer.
	 * 
	 * @param backOrder
	 *            the back order for this washer
	 */
	public void placeBackOrder(BackOrder backOrder) {
		backOrders.add(backOrder);
	}

	/**
	 * Removes a back order for a specific customer.
	 * 
	 * @param customerId
	 *            customer whose back order is to be removed
	 * @return true if the back order could be removed
	 */
	public boolean removeBackOrder(String customerId) {
		for (ListIterator<BackOrder> iterator = backOrders.listIterator(); iterator.hasNext();) {
			BackOrder backOrder = iterator.next();
			String id = backOrder.getCustomer().getId();
			if (id.equals(customerId)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a valid back order.
	 * 
	 * @return the next valid back order
	 */
	public BackOrder getNextBackOrder() {
		for (ListIterator<BackOrder> iterator = backOrders.listIterator(); iterator.hasNext();) {
			BackOrder backOrder = iterator.next();
			iterator.remove();
			return backOrder;
		}
		return null;
	}

	/**
	 * Checks whether there is a back order on this washer.
	 * 
	 * @return true if there is a back order
	 */
	public boolean hasBackOrder() {
		ListIterator<BackOrder> iterator = backOrders.listIterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
	}

	/**
	 * Returns an iterator for the back orders.
	 * 
	 * @return iterator for the back orders on this washer.
	 */
	public Iterator<BackOrder> getBackOrders() {
		return backOrders.iterator();
	}

	@Override
	public boolean matches(String key) {
		if (this.id.equals(key)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * String form of the washer.
	 * 
	 */
	@Override
	public String toString() {
		return "Brand: " + brand + ", Model: " + model + ", Price: " + price;
	}
}

package edu.metrostate.ics372.gp1;

import java.io.Serializable;

/**
 * The BackOrder class represents an order for a specific customer that has been
 * put on hold until the required quanity of washers for the sale are in the
 * inventory.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class BackOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private Washer washer;
	private int quantity;

	/**
	 * The customer and washer are stored, along with the requested quantity.
	 * 
	 * @param customer
	 *            the customer requesting the purchase
	 * @param washer
	 *            the washer that the customer wants to purchase
	 * @param quantity
	 *            the number of washers requested for purchase
	 */
	public BackOrder(Customer customer, Washer washer, int quantity) {
		this.customer = customer;
		this.washer = washer;
		this.quantity = quantity;
	}
}

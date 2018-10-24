package edu.metrostate.ics372.gp1;

import java.io.Serializable;

public class BackOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private int quantity;

	public BackOrder(Customer customer, int quantity) {
		this.customer = customer;
		this.quantity = quantity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void reduceQuantity(int quantity) {
		this.quantity = this.quantity - quantity;
	}

}

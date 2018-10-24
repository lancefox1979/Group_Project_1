package edu.metrostate.ics372.gp1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The Transaction class is used to manage a single transaction for a washer.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private Washer washer;
	private Calendar date;
	private int quantitySold;
	private int quantityDemanded;
	private int backOrderQuantity;
	private double amount;
	private BackOrder backOrder;
	boolean isBackOrdered;

	/**
	 * Creates the transaction with a given type and a washer ID. The date is
	 * the current date.
	 * 
	 * @param type
	 *            the type of transaction
	 * @param washerId
	 *            the ID of the washer
	 * 
	 */
	public Transaction(Customer customer, Washer washer, int quantity, boolean isBackOrdered) {
		this.customer = customer;
		this.washer = washer;
		this.quantityDemanded = quantity;
		this.isBackOrdered = isBackOrdered;
		date = new GregorianCalendar();
		date.setTimeInMillis(System.currentTimeMillis());
		processTransaction();
	}

	private void processTransaction() {
		while (quantityDemanded > 0) {
			if (washer.getQuantity() == 0) {
				backOrderQuantity++;
				washer.setBackOrdered(true);
			} else {
				washer.reduceQuantity(1);
				quantitySold++;
			}
			quantityDemanded--;
		}
		amount = quantitySold * washer.getPrice();
		backOrder = new BackOrder(customer, backOrderQuantity);
		washer.backOrderList.add(backOrder);
	}

	public double getAmount() {
		return amount;
	}

	/**
	 * Returns the date as a String.
	 * 
	 * @return date with month, day, and year
	 */
	public String getDate() {
		return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
	}

	/**
	 * String form of the transaction.
	 * 
	 */
	@Override
	public String toString() {
		if (isBackOrdered) {
			return String.format("Filled back order for Customer %s. Sold %d units of Brand: %s Model: %s",
					customer.getId(), quantitySold, washer.getBrand(), washer.getModel());
		} else {
			return String.format(
					"Customer %s bought %d of Brand %s Model %s for a total of $%.2f and a back order was placed on %d units.",
					customer.getId(), quantitySold, washer.getBrand(), washer.getModel(), amount, backOrderQuantity);
		}
	}
}

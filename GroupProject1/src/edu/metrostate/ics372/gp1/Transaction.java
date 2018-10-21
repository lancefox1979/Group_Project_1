package edu.metrostate.ics372.gp1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private String washerId;
	private Calendar date;

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
	public Transaction(String type, String washerId) {
		this.type = type;
		this.washerId = washerId;
		date = new GregorianCalendar();
		date.setTimeInMillis(System.currentTimeMillis());
	}

	/**
	 * Returns the type of transaction.
	 * 
	 * @return the type of transaction
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the washer ID.
	 * 
	 * @return washer ID
	 */
	public String getWasherId() {
		return washerId;
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
		return (type + "   " + washerId);
	}
}

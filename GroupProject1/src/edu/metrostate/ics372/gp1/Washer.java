package edu.metrostate.ics372.gp1;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

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
	private int quantity;
	private boolean isBackOrdered = false;
	public Queue<BackOrder> backOrderList = new LinkedList<>();

	/**
	 * Represents a single washer. ww
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
		this.setBackOrdered(true);
		setPrice(price);

	}

	/*
	 * Sets available quantity for this washer.
	 */
	public void updateQuantity(int quantity) {
		this.quantity = getQuantity() + quantity;
	}

	public void reduceQuantity(int quantity) {
		this.quantity = getQuantity() - quantity;
	}

	/*
	 * returns the quantity of washers
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/*
	 * Sets the price for the washer
	 */
	public void setPrice(double price) {
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

	@Override
	public boolean matchesId(String key) {
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
		return String.format("Brand: %-20s Model: %-20s Price: $%-20.2f", brand, model, price);
	}

	/*
	 * Returns back order status
	 */
	public boolean isBackOrdered() {
		return isBackOrdered;
	}

	/*
	 * Sets the back order status
	 */
	public void setBackOrdered(boolean isBackOrdered) {
		this.isBackOrdered = isBackOrdered;
	}
}

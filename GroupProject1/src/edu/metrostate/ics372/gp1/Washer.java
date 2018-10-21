package edu.metrostate.ics372.gp1;

import java.io.Serializable;

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
	private double price;

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
	 * Getter for the washer's price.
	 * 
	 * @return washer price
	 */
	public double getPrice() {
		return price;
	}

	@Override
	public boolean matches(String key) {
		if ((this.brand + this.model).equals(key)) {
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
		return "Washer brand: " + brand + ", model: " + model + ", price: " + price + ".";
	}
}

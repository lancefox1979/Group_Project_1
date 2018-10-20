package edu.metrostate.ics372.gp1;

import java.io.Serializable;

public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Inventory inventory;

	// Private constructor for the singleton pattern
	private Inventory() {
	}

	/**
	 * Supports the singleton pattern.
	 * 
	 * @return the singleton object
	 */
	public static Inventory instance() {
		if (inventory == null) {
			return (inventory = new Inventory());
		} else {
			return inventory;
		}
	}
}

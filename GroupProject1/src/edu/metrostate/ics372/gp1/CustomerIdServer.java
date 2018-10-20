package edu.metrostate.ics372.gp1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * The CustomerIdServer class is used for generating unique IDs for each
 * customer.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 */
public class CustomerIdServer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idCounter;
	private static CustomerIdServer server;

	/*
	 * Private constructor for singleton pattern
	 */
	private CustomerIdServer() {
		idCounter = 1;
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static CustomerIdServer instance() {
		if (server == null) {
			return (server = new CustomerIdServer());
		} else {
			return server;
		}
	}

	/**
	 * Getter for id
	 * 
	 * @return id of the member
	 */
	public int getId() {
		return idCounter++;
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return ("IdServer" + idCounter);
	}

	/**
	 * Retrieves the server object
	 * 
	 * @param input
	 *            input stream for deserialization
	 */
	public static void retrieve(ObjectInputStream input) {
		try {
			server = (CustomerIdServer) input.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}
}

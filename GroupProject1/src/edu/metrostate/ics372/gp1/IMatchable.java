package edu.metrostate.ics372.gp1;

public interface IMatchable<K> {
	/**
	 * Checks whether an item's key matches the given key.
	 * 
	 * @param key
	 *            the key value for matching
	 * @return true if the item's key matches the given key
	 */
	public boolean matches(K key);
}

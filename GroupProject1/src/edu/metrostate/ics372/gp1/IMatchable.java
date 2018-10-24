package edu.metrostate.ics372.gp1;

/**
 * The IMatchable interface is a base interface used for comparing two like
 * business objects.
 * 
 * ICS372-01 - Group Project #1
 * 
 * @author Shannon Fisher
 * 
 * @param <K>
 *            the generic type to compare against
 */
public interface IMatchable<K> {
	/**
	 * Checks whether an item's key matches the given key.
	 * 
	 * @param key
	 *            the key value for matching
	 * @return true if the item's key matches the given key
	 */
	public boolean matchesId(K key);
}

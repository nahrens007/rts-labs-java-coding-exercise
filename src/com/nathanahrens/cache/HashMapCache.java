package com.nathanahrens.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * HashMap implementation of cache. Constant time to access elements, and
 * constant time to put new elements, but O(n) complexity to update existing elements
 * due to requirement of maintaining order of insertion (must remove() key and add() key to maintain order once updated). 
 * Has a constraint of max size.
 * 
 * @author nahrens
 *
 * @param <V> data type of the cache value.
 */
public class HashMapCache<V> implements Cache<V> {
	/**
	 * Max size of cache.
	 */
	private int maxSize;
	/**
	 * Cache for objects. Constant time to access keys.
	 */
	private HashMap<String, V> cache;
	/**
	 * Maintains order of access; O(n) to search through list, or to remove(key),
	 * but otherwise constant time.
	 */
	private LinkedList<String> keys;
	
	/**
	 * Set the max size of the cache.
	 * @param maxSize
	 */
	public HashMapCache(int maxSize) {
		this.cache = new HashMap<String,V>(maxSize+(int)(maxSize*.25f));
		this.keys = new LinkedList<String>();
		this.setMaxSize(maxSize);
	}
	
	/**
	 * Sets default max size to 10.
	 */
	public HashMapCache() {
		this.cache = new HashMap<String,V>(10);
		this.keys = new LinkedList<String>();
		this.setMaxSize(10);
	}

	@Override
	public int size() {
		return this.cache.size();
	}

	@Override
	public boolean isEmpty() {
		return this.cache.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.cache.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.cache.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return this.cache.get(key);
	}

	@Override
	public V put(String key, V value) {
		// Update if key exists
		if (this.cache.containsKey(key)) {
			// Ensure updated pair is placed in front of list
			this.keys.remove(key);
			this.keys.add(key);

			// Update value
			return this.cache.put(key, value);
		}
		
		// At this point, the key does not already exist...
		// Ensure not exceeding maxSize by adding new; remove old elements if we are.
		if (this.cache.size() == this.maxSize) {
			// remove first item
			this.cache.remove(this.keys.remove());
		}
		// Add new item
		this.keys.add(key);
		return this.cache.put(key, value);
	}

	@Override
	public V remove(Object key) {
		// Only attempt to remove from cache if present in keys. Save time.
		if (this.keys.remove(key))
			return this.cache.remove(key);
		// Fallback on return null if key does not exist.
		return null;
	}

	/**
	 * Copies all of the mappings from the specified map to this map.These mappings will replace any mappings that this map had for any of the keys currently in the specified map.
	 * <br>
	 * O(n) complexity, due to ensuring new elements stay within {@link #maxSize}. To ensure this requirement, local put method is used for each element in {@link #m}.
	 */
	@Override
	public void putAll(Map<? extends String, ? extends V> m) {
		if (m == null) throw new NullPointerException("Provided map cannot be null.");
		
		for (Entry<? extends String, ? extends V> entry : m.entrySet()) {
			// this.put maintains staying under maxSize.
			this.put(entry.getKey(),entry.getValue());
		}
	}

	@Override
	public void clear() {
		this.cache.clear();
		this.keys.clear();
	}

	@Override
	public Set<String> keySet() {
		return this.cache.keySet();
	}

	@Override
	public Collection<V> values() {
		return this.cache.values();
	}

	@Override
	public Set<Entry<String, V>> entrySet() {
		return this.cache.entrySet();
	}

	@Override
	public int getMaxSize() {
		return this.maxSize;
	}

	@Override
	public void setMaxSize(int maxSize) throws IllegalArgumentException {
		if (maxSize < 1) {
			throw new IllegalArgumentException("Max size must be greater than 0");
		}
		this.maxSize = maxSize;

		int removeCount = this.cache.size() - this.maxSize;
		for (int i = 0; i < removeCount; i++) {
			this.cache.remove(this.keys.remove());
		}
	}

	@Override
	public int getCurrentCacheSize() {
		return this.cache.size();
	}
	
	@Override
	public String toString() {
		return this.cache.toString();
	}

}

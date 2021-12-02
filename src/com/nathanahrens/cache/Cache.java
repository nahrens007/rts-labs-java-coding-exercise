/**
 * 
 */
package com.nathanahrens.cache;

import java.util.Map;

/**
 * @author nahrens
 * @param <V>
 *
 */
public interface Cache<V> extends Map<String, V> {
	/**
     * Gets the allowed maximum size of the cache.
     * @return The current maximum size of the cache.
     */
    int getMaxSize();

    /**
     * Adjusts the maximum size of the cache.  If the cache is resized to hold less values that are currently cached
     * then values must be ejected until the current size is equal to the maximum size.
     * @param maxSize The new maximum size of the cache.
     * @throws IllegalArgumentException If maxSize is less than or equal to 0
     */
    void setMaxSize(int maxSize);

    /**
     * Gets the number of currently cached items.
     * @return The cached item count.
     */
    int getCurrentCacheSize();
}

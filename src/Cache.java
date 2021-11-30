/**
 * A key-value cache with a size limit.
 * @author justin.boutwell
 * @since 1/6/2017
 */
public interface Cache<T> {

    /**
     * Gets the value stored by the key.
     * @param key The key from which to get a value.
     * @return The value, or {@code null} if no value is cached for the given key.
     */
    T get(String key);

    /**
     * Puts the value into the cache by the key.  Only one key of the same value can be cached such that the value
     * with the most recently put key is kept.
     * If putting the value would extend the cache past the maximum size limit defined by {@link #setMaxSize(int)}
     * then another cached value must first be ejected.
     * @param key The key for which to cache the value.
     * @param value The cached value.
     */
    void put(String key, T value);

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
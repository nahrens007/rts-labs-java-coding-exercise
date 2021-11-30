import java.util.LinkedList;

public class UtilCache<T> implements Cache<T> {

	private int maxSize;
	private LinkedList<Map> cache;

	public UtilCache() {

		cache = new LinkedList<Map>();
	}

	private class Map<String, U> {
		private String key;
		private U value;

		public Map(String key, U value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return this.key;
		}

		public U getValue() {
			return this.value;
		}

		public void setValue(U value) {
			this.value = value;
		}

		public java.lang.String toString() {
			return this.key + " " + this.value;
		}
	}

	private Map<String, T> getMap(String key) {
		for (Map<String, T> obj : cache) {
			if (obj.getKey().compareTo(key) == 0) {
				return obj;
			}
		}
		return null;
	}

	@Override
	public T get(String key) {
		try {
			return this.getMap(key).getValue();
		} catch (NullPointerException e) {
			return null;
		}

	}

	@Override
	public void put(String key, T value) {

		Map<String, T> obj = this.getMap(key);

		// check if key already exists.
		if (obj == null) {
			// Evaluate current size
			// if current size == max size
			if (cache.size() == this.maxSize) {
				// remove first item
				cache.remove();
			}

			// else (now it is less than)
			// insert at end of cache
			cache.add(new Map<String, T>(key, value));
			return;
		}
		
		// update existing
		obj.setValue(value);
		// update location in cache list
		this.cache.remove(obj);
		this.cache.add(obj);
	}

	@Override
	public int getMaxSize() {
		// TODO Auto-generated method stub
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
			this.cache.remove();
		}
	}

	@Override
	public int getCurrentCacheSize() {
		// TODO Auto-generated method stub
		return this.cache.size();
	}

	public void printCache() {
		System.out.println(this.cache);
	}

}

package com.nathanahrens.cache;

public class Driver {

	public static void main(String[] args) {
		HashMapCache<String> cache = new HashMapCache<String>(3);
		
		cache.put("name1", "Nathan");
		System.out.println(cache);
		cache.put("name2", "Ben");
		cache.put("name3", "Brad");
		cache.put("name4", "Lily");
		cache.put("name2", "Jonny");
		System.out.println(cache);
		cache.put("name1", "George");
		System.out.println(cache);
	}

}

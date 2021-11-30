
public class Driver {

	public static void main(String[] args) {
		UtilCache<String> cache = new UtilCache<String>();
		cache.setMaxSize(3);

		cache.put("name1", "Nathan");
		cache.put("name2", "Ben");
		cache.put("name3", "Brad");

		cache.put("name4", "Lily");

		cache.printCache();

		cache.put("name4", "Nathan");
		cache.printCache();
		
		cache.put("name2", "George");
		cache.printCache();
		
		cache.put("name5", "Tom");
		cache.printCache();
		
		cache.setMaxSize(1);
		cache.printCache();
		
		
	}

}

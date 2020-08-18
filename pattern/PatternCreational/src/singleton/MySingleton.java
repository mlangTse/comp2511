package singleton;

import java.time.LocalTime;

public class MySingleton {

	private static MySingleton single_instance = null ;

	private LocalTime localTime;

	private MySingleton() {
		localTime = LocalTime.now();
	}

	public static synchronized MySingleton getInstance() {

		if (single_instance == null) {
			single_instance = new MySingleton();
		}
		return single_instance;
	}

	public LocalTime getTime() {
		return localTime;
	}
}

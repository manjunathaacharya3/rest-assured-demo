package utils;

public class ObjectFactory<T> {

	private Object instance;

	/** Private constructor to prevent external instantiation. */
	private ObjectFactory() {
	}

	public static <T> T getInstance(Class<T> inputClass) {
		return SingletonHolder.INSTANCE.getInstanceInternal(inputClass);
	}

	private synchronized <T> T getInstanceInternal(Class<T> inputClass) {
		if (instance == null) {
			try {
				instance = inputClass.getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (T) instance;
	}

	private static class SingletonHolder {
		private static final ObjectFactory<?> INSTANCE = new ObjectFactory<>();
	}
}
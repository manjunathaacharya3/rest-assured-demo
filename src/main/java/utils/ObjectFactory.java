package utils;

import java.lang.reflect.Constructor;

public class ObjectFactory<T> {

	private Object instance;

	/** Private constructor to prevent external instantiation. */
	private ObjectFactory() {
	}

	public static <T> T getInstance(Class<T> inputClass) {
		return SingletonHolder.INSTANCE.getInstanceInternal(inputClass);
	}

	public static <T> T getInstance(Class<T> inputClass, Object... constructorArgs) {
		return SingletonHolder.INSTANCE.getInstanceInternal(inputClass, constructorArgs);
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

	private synchronized <T> T getInstanceInternal(Class<T> inputClass, Object... constructorArgs) {
		if (instance == null) {
			try {
				Constructor<T> constructor = inputClass.getDeclaredConstructor();
				instance = constructor.newInstance(constructorArgs);
			} catch (Exception e) {
				throw new RuntimeException("Error creating instance.", e);
			}
		}
		return (T) instance;
	}

	private static class SingletonHolder {
		private static final ObjectFactory<?> INSTANCE = new ObjectFactory<>();
	}
}
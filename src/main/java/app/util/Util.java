package app.util;

public class Util {
	public static <T>T getOrDefault(T updateValue, T existingValue) {
	    return updateValue != null ? updateValue : existingValue;
	}
}

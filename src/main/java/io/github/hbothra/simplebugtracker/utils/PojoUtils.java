package io.github.hbothra.simplebugtracker.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public final class PojoUtils {

	private PojoUtils() {
		// Need to make sure this class only hosts static utility methods
	}

	private static Set<String> getFieldsWithNullValue(Object object) {
		BeanWrapper src = new BeanWrapperImpl(object);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> keysWithNullValues = new HashSet<>();
		for (PropertyDescriptor pd : pds) {
			if (null == src.getPropertyValue(pd.getName())) {
				keysWithNullValues.add(pd.getName());
			}
		}
		return keysWithNullValues;
	}
	
	public static void updateTaregtWithValues(Object src, Object target) {
		Set<String> ignoreFildList = getFieldsWithNullValue(src);
		String[] ignoreFields = new String[ignoreFildList.size()];
		BeanUtils.copyProperties(src, target, ignoreFildList.toArray(ignoreFields));
	}
	
	public static Object deepClone(Object src) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object target = src.getClass().getDeclaredConstructor().newInstance();
		BeanUtils.copyProperties(src, target);
		return target;
	}
}

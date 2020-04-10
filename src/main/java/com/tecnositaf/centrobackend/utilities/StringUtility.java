package com.tecnositaf.centrobackend.utilities;

public class StringUtility {
	
	private StringUtility() {}

	public static boolean isEmptyString(String s) {
		return CommonsUtility.isNull(s) || s.isEmpty();
	}
	public static boolean isNullOrBlankString(String s) {
		return CommonsUtility.isNull(s) ||  s.isBlank();
	}
}

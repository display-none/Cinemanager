package org.cinemanager.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;


public class ValidatingHelper {

	public static boolean isNonNegativeInteger(String string) {
		int parsedInt;
		try {
			parsedInt = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return false;
		}
		if(parsedInt < 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Careful! Empty string is a valid date!
	 */
	public static boolean isValidDate(String string, String dateFormat) {
		SimpleDateFormat dateParser = new SimpleDateFormat(dateFormat);
		dateParser.setLenient(false);
		
		ParsePosition position = new ParsePosition(0);
		dateParser.parse(string, position);
		if (position.getIndex() != string.length()) {
		    return false;
		}
		return true;
	}
}

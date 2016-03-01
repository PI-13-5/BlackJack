package ua.nure.bj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static boolean isNullOrEmpty(String... strings) {
		for (String string : strings) {
			if (string == null || string.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static String getDateString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy.MM.dd G 'at' HH:mm:ss z");
		return dateFormat.format(new Date());

	}
}

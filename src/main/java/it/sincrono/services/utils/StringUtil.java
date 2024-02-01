package it.sincrono.services.utils;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

	public String[] yearAndMonthByPath(String path) {

		String[] parts = path.split("/");

		String year = null;
		String month = null;

		for (String part : parts) {
			if (part.matches("\\d{4}")) {
				year = part;
			}

			if (part.matches("\\d{1,2}\\.txt")) {
				month = part.replace(".txt", "");
			}
		}

		return new String[] { year, month };

	}

}

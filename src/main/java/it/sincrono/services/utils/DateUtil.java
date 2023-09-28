package it.sincrono.services.utils;

import java.util.Calendar;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;

import org.springframework.stereotype.Component;

public class DateUtil {
	public static Boolean dateCompare(Date date, Date otherDate) {

		if (date == null && otherDate == null) {

			return true;
		} else if (date == null || otherDate == null) {

			return false;
		}

		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);

		Calendar calendarOtherDate = Calendar.getInstance();
		calendarOtherDate.setTime(otherDate);

		if (calendarDate.get(Calendar.DAY_OF_MONTH) == calendarOtherDate.get(Calendar.DAY_OF_MONTH)
				&& calendarDate.get(Calendar.MONTH) + 1 == calendarOtherDate.get(Calendar.MONTH) + 1
				&& calendarDate.get(Calendar.YEAR) == calendarOtherDate.get(Calendar.YEAR)) {

			return true;
		} else {

			return false;
		}

	}

	public static LocalDate convertorDate(Date data) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH));

		return localDate;

	}

	public static boolean compareYear(Date dateFirstCompare, Integer dateSecondCompare) {

		LocalDate date1 = convertorDate(dateFirstCompare);

		int anno1 = date1.getYear();

		return anno1 == dateSecondCompare;
	}

	public static boolean compareMonth(Date dateFirstCompare, Integer dateSecondCompare) {

		LocalDate date1 = convertorDate(dateFirstCompare);

		Integer mese1 = date1.getMonthValue();

		return mese1 == dateSecondCompare;
	}
}

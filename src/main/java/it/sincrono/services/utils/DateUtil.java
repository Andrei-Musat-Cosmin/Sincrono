package it.sincrono.services.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

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

	public static int calcolaGiorniUtiliLavoro(int anno, int meseIntero) {

		Month mese = null;

		mese = convertIntInMonth(meseIntero);

		int giorniLavorativi = 0;

		LocalDate dataInizio = LocalDate.of(anno, mese, 1);
		LocalDate dataFine = LocalDate.of(anno, mese, dataInizio.lengthOfMonth());

		LocalDate dataCorrente = dataInizio;
		while (!dataCorrente.isAfter(dataFine)) {
			DayOfWeek giornoSettimana = dataCorrente.getDayOfWeek();

			if (giornoSettimana != DayOfWeek.SATURDAY && giornoSettimana != DayOfWeek.SUNDAY) {

				giorniLavorativi++;
			}

			dataCorrente = dataCorrente.plusDays(1);
		}

		return giorniLavorativi;
	}

	private static Month convertIntInMonth(int numeroMese) {

		switch (numeroMese) {
		case 1:
			return Month.JANUARY;
		case 2:
			return Month.FEBRUARY;
		case 3:
			return Month.MARCH;
		case 4:
			return Month.APRIL;
		case 5:
			return Month.MAY;
		case 6:
			return Month.JUNE;
		case 7:
			return Month.JULY;
		case 8:
			return Month.AUGUST;
		case 9:
			return Month.SEPTEMBER;
		case 10:
			return Month.OCTOBER;
		case 11:
			return Month.NOVEMBER;
		case 12:
			return Month.DECEMBER;
		default:
			return null;
		}
	}

}

package it.sincrono.services.utils;

import java.time.DayOfWeek;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.MeseDto;
import it.sincrono.repositories.dto.RapportinoDto;

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

	public static String getNomeGiorno(Integer numeroGiorno, Integer anno, Integer mese) {

		Date data = new Date(anno - 1900, mese - 1, numeroGiorno);

		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");

		return sdf.format(data);

	}

	public static void checkFestivitàNazionale(RapportinoDto rapportinoDto, Integer mese,Integer anno) {
		LocalDate datePasqua = calcolaPasqua(anno);
		LocalDate datePasquetta = calcolaPasquetta(datePasqua);
		final List<MonthDay> GIORNI_FESTIVI = Arrays.asList(MonthDay.of(1, 1), // Capodanno
				MonthDay.of(1, 6), // Epifania
				MonthDay.of(4, 25), // Festa della Liberazione
				MonthDay.of(5, 1), // Festa dei lavoratori
				MonthDay.of(6, 2), // Festa della Repubblica
				MonthDay.of(8, 15), // Assunzione di Maria Vergine (Ferragosto)
				MonthDay.of(11, 1), // Tutti i Santi
				MonthDay.of(12, 8), // Immacolata Concezione
				MonthDay.of(12, 25), // Natale
				MonthDay.of(12, 26), // Santo Stefano
				MonthDay.of(datePasqua.getMonth(), datePasqua.getDayOfMonth()), // Pasqua
				MonthDay.of(datePasquetta.getMonth(), datePasquetta.getDayOfMonth()) // Pasquetta

		);

		List<GiornoDto> listGiorni = rapportinoDto.getMese().getGiorni();

		for (GiornoDto giorno : listGiorni) {
			int numeroGiorno = giorno.getNumeroGiorno();

			if (GIORNI_FESTIVI.contains(MonthDay.of(mese, numeroGiorno))) {
				System.out.println("Festività rilevata");

				giorno.setFestivitaNazionale(true);

			}
		}

	}

	public static LocalDate calcolaPasqua(int year) {

		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int month = (h + l - 7 * m + 114) / 31;
		int day = ((h + l - 7 * m + 114) % 31) + 1;

		return LocalDate.of(year, month, day);
	}

	public static LocalDate calcolaPasquetta(LocalDate date) {

		return LocalDate.from(date.plusDays(1));
	}
	

}

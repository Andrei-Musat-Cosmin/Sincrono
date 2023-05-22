package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Profilo;

@Component
public class ProfiloValidator {

	public boolean validate(Profilo profilo) /* throws ParseException */ {

		boolean result = true;

		Long d1 = profilo.getDataInizio().getTime();
		Long d2 = null;
		if (profilo.getDataFine() != null) {
			d2 = profilo.getDataFine().getTime();
		}

		if (profilo.getDataInizio() == null) {
			result = false;
		} else if (d2 != null) {
			if (d1 >= d2) {
				result = false;

			}
		}
		return result;
	}
}

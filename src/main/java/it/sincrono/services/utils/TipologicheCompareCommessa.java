package it.sincrono.services.utils;

import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;

public class TipologicheCompareCommessa {

	public static Boolean tipologicheCompare(Commessa commessa, Commessa CommessaDb) {

		return tipoAziendaCompare(commessa, CommessaDb);

	}

	private static Boolean tipoAziendaCompare(Commessa commessa, Commessa commessaDb) {

		Boolean check = false;

		if (commessa.getTipoAzienda() != null) {

			if (commessa.getTipoAzienda().equals(commessaDb.getTipoAzienda())) {

				check = true;

			} else {

				check = false;
			}

		} else if (commessaDb.getTipoAzienda() != null) {

			if (commessa.getTipoAzienda().equals(commessaDb.getTipoAzienda())) {

				check = true;

			} else {

				check = false;
			}

		} else {

			check = true;

		}

		return check;

	}

}

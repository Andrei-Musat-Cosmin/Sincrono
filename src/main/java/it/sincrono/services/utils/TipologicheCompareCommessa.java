package it.sincrono.services.utils;

import it.sincrono.entities.Commessa;

public class TipologicheCompareCommessa {

	public static Boolean tipologicheCompare(Commessa commessa, Commessa CommessaDb) {

		return tipoAziendaCompare(commessa, CommessaDb);

	}

	private static Boolean tipoAziendaCompare(Commessa commessa, Commessa commessaDb) {

		Boolean check = false;

		if (commessa.getTipoAziendaCliente() != null) {

			if (commessa.getTipoAziendaCliente().equals(commessaDb.getTipoAziendaCliente())) {

				check = true;

			} else {

				check = false;
			}

		} else if (commessaDb.getTipoAziendaCliente() != null) {

			if (commessa.getTipoAziendaCliente().equals(commessaDb.getTipoAziendaCliente())) {

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

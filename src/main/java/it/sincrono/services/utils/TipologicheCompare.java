package it.sincrono.services.utils;

import it.sincrono.entities.Contratto;

public class TipologicheCompare {

	public static Boolean tipologicheCompare(Contratto contratto, Contratto ContrattoDb) {

		return tipoAziendaCompare(contratto, ContrattoDb) && tipoCanaleReclutamentoCompare(contratto, ContrattoDb)
				&& tipoCausaFineRapportoCompare(contratto, ContrattoDb) && tipoCcnlCompare(contratto, ContrattoDb)
				&& tipoContrattoCompare(contratto, ContrattoDb) && tipoLivelloContrattoCompare(contratto, ContrattoDb);

	}

	private static Boolean tipoAziendaCompare(Contratto contratto, Contratto ContrattoDb) {

		Boolean check = false;

		// tipoAzienda

		if (contratto.getTipoAzienda() != null) {

			if (contratto.getTipoAzienda().equals(ContrattoDb.getTipoAzienda())) {

				check = true;

			} else {

				check = false;
			}

		} else if (ContrattoDb.getTipoAzienda() != null) {

			if (ContrattoDb.getTipoAzienda().equals(contratto.getTipoAzienda())) {

				check = true;

			} else {

				check = false;
			}

		} else {

			check = true;

		}

		return check;

	}

	private static Boolean tipoCanaleReclutamentoCompare(Contratto contratto, Contratto ContrattoDb) {

		Boolean check = false;

		// tipoCanaleReclutamento

		if (contratto.getTipoCanaleReclutamento() != null) {

			if (contratto.getTipoCanaleReclutamento().equals(ContrattoDb.getTipoCanaleReclutamento())) {

				check = true;

			} else {

				check = false;
			}

		} else if (ContrattoDb.getTipoCanaleReclutamento() != null) {

			if (ContrattoDb.getTipoCanaleReclutamento().equals(contratto.getTipoCanaleReclutamento())) {

				check = true;

			} else {

				check = false;
			}

		} else {

			check = true;

		}

		return check;
	}

	private static Boolean tipoCausaFineRapportoCompare(Contratto contratto, Contratto ContrattoDb) {

		Boolean check = false;
		// getTipoCausaFineRapporto

		if (contratto.getTipoCausaFineRapporto() != null) {

			if (contratto.getTipoCausaFineRapporto().equals(ContrattoDb.getTipoCausaFineRapporto())) {

				check = true;

			} else {

				check = false;
			}

		} else if (ContrattoDb.getTipoCausaFineRapporto() != null) {

			if (ContrattoDb.getTipoCausaFineRapporto().equals(contratto.getTipoCausaFineRapporto())) {

				check = true;

			} else {

				check = false;
			}

		} else {

			check = true;

		}

		return check;
	}

	private static Boolean tipoCcnlCompare(Contratto contratto, Contratto ContrattoDb) {

		Boolean check = false;

		// getTipoCcnl

		if (contratto.getTipoCcnl() != null) {

			if (contratto.getTipoCcnl().equals(ContrattoDb.getTipoCcnl())) {

				check = true;

			} else {

				check = false;
			}

		} else if (ContrattoDb.getTipoCcnl() != null) {

			if (ContrattoDb.getTipoCcnl().equals(contratto.getTipoCcnl())) {

				check = true;

			} else {

				check = false;
			}

		} else {

			check = true;

		}

		return check;

	}

	private static Boolean tipoContrattoCompare(Contratto contratto, Contratto ContrattoDb) {

		Boolean check = false;

		// getTipoContratto

		if (contratto.getTipoContratto() != null) {

			if (contratto.getTipoContratto().equals(ContrattoDb.getTipoContratto())) {

				check = true;

			} else {

				check = false;
			}

		} else if (ContrattoDb.getTipoContratto() != null) {

			if (ContrattoDb.getTipoContratto().equals(contratto.getTipoContratto())) {

				check = true;

			} else {

				check = false;
			}

		} else {

			check = true;

		}

		return check;

	}

	private static Boolean tipoLivelloContrattoCompare(Contratto contratto, Contratto ContrattoDb) {

		Boolean check = false;

		// getTipoLivelloContratto

		if (contratto.getTipoLivelloContratto() != null) {

			if (contratto.getTipoLivelloContratto().equals(ContrattoDb.getTipoLivelloContratto())) {

				check = true;

			} else {

				check = false;
			}

		} else if (ContrattoDb.getTipoLivelloContratto() != null) {

			if (ContrattoDb.getTipoLivelloContratto().equals(contratto.getTipoLivelloContratto())) {

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

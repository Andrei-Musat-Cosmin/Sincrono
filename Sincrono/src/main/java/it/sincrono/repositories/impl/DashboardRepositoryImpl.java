package it.sincrono.repositories.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Ccnl;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.LivelloContratto;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.Utente;
import it.sincrono.repositories.DashboardCustomRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class DashboardRepositoryImpl extends BaseRepositoryImpl implements DashboardCustomRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<AnagraficaDto> listCommesse(AnagraficaDto anagraficaDto) throws RepositoryException {
		try {

			String queryString = SqlStrings.SQL_LIST_COMMESSE;
			String subString = "";

			if (anagraficaDto != null) {

				if (anagraficaDto.getAnagrafica() != null) {

					if (anagraficaDto.getAnagrafica() != null) {

						if (anagraficaDto.getAnagrafica().getCognome() != null
								&& anagraficaDto.getAnagrafica().getCognome() != "") {

							subString += "AND a.cognome LIKE '" + anagraficaDto.getAnagrafica().getCognome() + "'";

						}

						if (anagraficaDto.getAnagrafica().getAziendaTipo() != null
								&& anagraficaDto.getAnagrafica().getAziendaTipo() != "") {

							subString += "AND a.azienda_tipo LIKE '" + anagraficaDto.getAnagrafica().getAziendaTipo()
									+ "'";

						}

					}

				}

			}

			if (anagraficaDto.getCommesse() != null) {

				if (anagraficaDto.getCommesse().get(0) != null) {

					if (anagraficaDto.getCommesse().get(0).getDataFine() != null) {

						subString += "AND  d.data_fine LIKE '" + anagraficaDto.getCommesse().get(0).getDataFine() + "'";

					}

					if (anagraficaDto.getCommesse().get(0).getCliente() != null
							&& anagraficaDto.getCommesse().get(0).getCliente() != "") {

						subString += "AND d.cliente LIKE '" + anagraficaDto.getCommesse().get(0).getCliente() + "'";

					}

				}

			}

			if (anagraficaDto.getContratto() != null) {

				if (anagraficaDto.getContratto().getDataFineRapporto() != null) {

					subString += "AND c.data_fine_contratto LIKE '" + anagraficaDto.getContratto().getDataFineRapporto()
							+ "'";

				}

			}

			queryString = queryString.replace("{0}", subString);

			Query query = entityManager.createNativeQuery(queryString);

			List<Object> list = query.getResultList();

			List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();
			for (Iterator<Object> it = list.iterator(); it.hasNext();) {
				Object[] result = (Object[]) it.next();

				Commessa commessa = new Commessa();
				if (result != null) {
					if (result[0] != null)
						commessa.setId((Integer) result[0]);
					if (result[1] != null)
						commessa.setCliente((String) result[1]);
					if (result[2] != null)
						commessa.setClienteFinale((String) result[2]);
					if (result[3] != null)
						commessa.setTitoloPosizione((String) result[3]);
					if (result[4] != null)
						commessa.setDistacco((String) result[4]);
					if (result[5] != null)
						commessa.setDataInizio((Date) result[5]);
					if (result[6] != null)
						commessa.setDataFine((Date) result[6]);
					if (result[7] != null)
						commessa.setCostoMese((String) result[7]);
					if (result[8] != null)
						commessa.setTariffaGiornaliera((String) result[8]);
					if (result[9] != null)
						commessa.setNominativo((String) result[9]);
					if (result[10] != null)
						commessa.setAzienda((String) result[10]);
					if (result[11] != null)
						commessa.setAziendaDiFatturazioneInterna((String) result[11]);
					if (result[12] != null)
						commessa.setStato((Boolean) result[12]);
					if (result[13] != null)
						commessa.setAttesaLavori((String) result[13]);

				}

				listAnagraficaDto.add(anagraficaDto);

			}

			return listAnagraficaDto;

		} catch (Exception e) {
			throw new RepositoryException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnagraficaDto> listCommesseInScadenza() throws RepositoryException {
		try {

			String queryString = SqlStrings.SQL_ID_ANAGRAFICA;

			Query query = entityManager.createNativeQuery(queryString);

			List<Object> list = query.getResultList();

			List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();
			for (Iterator<Object> it = list.iterator(); it.hasNext();) {
				Object result = (Object) it.next();

				AnagraficaDto anagraficaDto = new AnagraficaDto();

				Anagrafica anagrafica = new Anagrafica();

				if (result != null) {

					anagrafica.setId((Integer) result);

				}

				anagraficaDto.setAnagrafica(anagrafica);

				queryString = SqlStrings.SQL_DETTAGLIO_COMMESSA;

				queryString = queryString.replace("{0}", String.valueOf(anagraficaDto.getAnagrafica().getId()));
				query = null;
				query = entityManager.createNativeQuery(queryString);
				List<Object> listCommessa = query.getResultList();
				anagraficaDto.setCommesse(new ArrayList<Commessa>());
				for (Iterator<Object> iterator = listCommessa.iterator(); iterator.hasNext();) {
					Object[] currentCommessa = (Object[]) iterator.next();

					Commessa commessa = new Commessa();
					if (currentCommessa != null) {
						if (currentCommessa[0] != null)
							commessa.setId((Integer) currentCommessa[0]);
						if (currentCommessa[1] != null)
							commessa.setCliente((String) currentCommessa[1]);
						if (currentCommessa[2] != null)
							commessa.setClienteFinale((String) currentCommessa[2]);
						if (currentCommessa[3] != null)
							commessa.setTitoloPosizione((String) currentCommessa[3]);
						if (currentCommessa[4] != null)
							commessa.setDistacco((String) currentCommessa[4]);
						if (currentCommessa[5] != null)
							commessa.setDataInizio((Date) currentCommessa[5]);
						if (currentCommessa[6] != null)
							commessa.setDataFine((Date) currentCommessa[6]);
						if (currentCommessa[7] != null)
							commessa.setCostoMese((String) currentCommessa[7]);
						if (currentCommessa[8] != null)
							commessa.setTariffaGiornaliera((String) currentCommessa[8]);
						if (currentCommessa[9] != null)
							commessa.setNominativo((String) currentCommessa[9]);
						if (currentCommessa[10] != null)
							commessa.setAzienda((String) currentCommessa[10]);
						if (currentCommessa[11] != null)
							commessa.setAziendaDiFatturazioneInterna((String) currentCommessa[11]);
						if (currentCommessa[12] != null)
							commessa.setStato((Boolean) currentCommessa[12]);
						if (currentCommessa[13] != null)
							commessa.setAttesaLavori((String) currentCommessa[13]);

					}

					anagraficaDto.getCommesse().add(commessa);

				}

				listAnagraficaDto.add(anagraficaDto);

			}

			listAnagraficaDto = checkCommesseInScadenza(listAnagraficaDto);

			return listAnagraficaDto;

		} catch (Exception e) {
			throw new RepositoryException(e);
		}

	}

	private List<AnagraficaDto> checkCommesseInScadenza(List<AnagraficaDto> list) {

		List<AnagraficaDto> listInScadenza = new ArrayList<AnagraficaDto>();

		for (AnagraficaDto anagraficaDto : list) {

			List<Commessa> listCommesse = new ArrayList<Commessa>();

			AnagraficaDto anagraficaDtoApp = new AnagraficaDto();

			for (Commessa commessa : anagraficaDto.getCommesse()) {

				LocalDate localDateFine = convertorDate(commessa.getDataFine());

				if (localDateFine.isAfter(LocalDate.now().minus(40, ChronoUnit.DAYS))

						&&

						localDateFine.isBefore(LocalDate.now())) {

					listCommesse.add(commessa);

				}

			}

			if (!listCommesse.isEmpty()) {

				anagraficaDtoApp.setAnagrafica(anagraficaDto.getAnagrafica());
				anagraficaDtoApp.setCommesse(listCommesse);
				listInScadenza.add(anagraficaDtoApp);

			}

		}

		return listInScadenza;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnagraficaDto> listContrattiInScadenza() throws RepositoryException {

		try {

			String queryString = SqlStrings.SQL_LIST_ANAGRAFICA;
			String subString = "";

			Query query = entityManager.createNativeQuery(queryString);

			List<Object> list = query.getResultList();

			List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();
			Integer lastResult = null;
			for (Iterator<Object> it = list.iterator(); it.hasNext();) {
				Object[] result = (Object[]) it.next();
				AnagraficaDto anagraficaDto = new AnagraficaDto();

				Anagrafica anagrafica = new Anagrafica();

				anagrafica.setId((Integer) result[0]);
				if (result[1] != null)

					anagraficaDto.setAnagrafica(anagrafica);

				Contratto contratto = new Contratto();

				if (result[20] != null)
					contratto.setId((Integer) result[20]);

				LivelloContratto livelloContratto = new LivelloContratto();
				if (result[22] != null)
					livelloContratto.setId((Integer) result[22]);
				if (result[58] != null)
					livelloContratto.setCcnl((String) result[58]);
				if (result[59] != null)
					livelloContratto.setLivello((String) result[59]);
				if (result[60] != null)
					livelloContratto.setMinimiRet23((String) result[60]);
				contratto.setLivelloContratto(livelloContratto);

				TipoContratto tipoContratto = new TipoContratto();
				if (result[21] != null)
					tipoContratto.setId((Integer) result[21]);
				if (result[62] != null)
					tipoContratto.setDescrizione((String) result[62]);
				contratto.setTipoContratto(tipoContratto);

				Ccnl ccnl = new Ccnl();

				if (result[24] != null)
					ccnl.setId((Integer) result[24]);
				if (result[64] != null)
					ccnl.setDescrizione((String) result[64]);
				contratto.setTipoCcnl(ccnl);

				TipoAzienda tipoAzienda = new TipoAzienda();
				if (result[23] != null)
					tipoAzienda.setId((Integer) result[23]);
				if (result[66] != null)
					tipoAzienda.setDescrizione((String) result[66]);
				contratto.setTipoAzienda(tipoAzienda);

				if (result[25] != null)
					contratto.setAttivo((Boolean) result[25]);
				if (result[26] != null)
					contratto.setQualifica((String) result[26]);
				if (result[27] != null)
					contratto.setSedeAssunzione((String) result[27]);
				if (result[28] != null)
					contratto.setDataAssunzione((Date) result[28]);
				if (result[29] != null)
					contratto.setDataInizioProva((Date) result[29]);
				if (result[30] != null)
					contratto.setDataFineProva((Date) result[30]);
				if (result[31] != null)
					contratto.setDataFineRapporto((Date) result[31]);
				if (result[32] != null)
					contratto.setMesiDurata((Integer) result[32]);
				if (result[33] != null)
					contratto.setLivelloAttuale((String) result[33]);
				if (result[34] != null)
					contratto.setLivelloFinale((String) result[34]);
				if (result[35] != null)
					contratto.setDimissioni((Boolean) result[35]);
				if (result[36] != null)
					contratto.setPartTime((Boolean) result[36]);
				if (result[37] != null)
					contratto.setPartTimeA((Boolean) result[37]);
				if (result[38] != null)
					contratto.setRetribuzioneMensileLorda((String) result[38]);
				if (result[39] != null)
					contratto.setSuperminimoMensile((String) result[39]);
				if (result[40] != null)
					contratto.setRalAnnua((String) result[40]);
				if (result[41] != null)
					contratto.setSuperminimoRal((String) result[41]);
				if (result[42] != null)
					contratto.setDiariaMese((String) result[42]);
				if (result[43] != null)
					contratto.setDiariaGg((String) result[43]);
				if (result[44] != null)
					contratto.setTicket((String) result[44]);
				if (result[45] != null)
					contratto.setValoreTicket((String) result[45]);
				if (result[46] != null)
					contratto.setCategoriaProtetta((Boolean) result[46]);
				if (result[47] != null)
					contratto.setTutor((String) result[47]);
				if (result[48] != null)
					contratto.setPfi((String) result[48]);
				if (result[49] != null)
					contratto.setCorsoSicurezza((Date) result[49]);
				if (result[50] != null)
					contratto.setMotivazioneFineRapporto((String) result[50]);
				if (result[51] != null)
					contratto.setPc((Boolean) result[51]);
				if (result[53] != null)
					contratto.setScattiAnzianita((String) result[53]);
				if (result[54] != null)
					contratto.setTariffaPartitaIva((String) result[54]);
				if (result[55] != null)
					contratto.setCanaleReclutamento((String) result[55]);
				if (result[56] != null)
					contratto.setAssicurazioneObbligatoria((String) result[56]);
				anagraficaDto.setContratto(contratto);

				listAnagraficaDto.add(anagraficaDto);

			}

			listAnagraficaDto = checkContrattiInScadenza(listAnagraficaDto);

			return listAnagraficaDto;

		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	private List<AnagraficaDto> checkContrattiInScadenza(List<AnagraficaDto> list) {

		List<AnagraficaDto> listInScadenza = new ArrayList<AnagraficaDto>();

		for (AnagraficaDto anagraficaDto : list) {

			List<Commessa> listCommesse = new ArrayList<Commessa>();

			LocalDate dataAssunzione = convertorDate(anagraficaDto.getContratto().getDataAssunzione());

			LocalDate dataSommata = dataAssunzione.plusMonths(anagraficaDto.getContratto().getMesiDurata());

			if (dataSommata.isAfter(LocalDate.now().minus(40, ChronoUnit.DAYS))

					&&

					dataSommata.isBefore(dataSommata)) {

				listInScadenza.add(anagraficaDto);

			}

		}

		return listInScadenza;

	}

	private LocalDate convertorDate(Date data) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH));

		return localDate;

	}
}

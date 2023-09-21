package it.sincrono.repositories.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoCanaleReclutamento;
import it.sincrono.entities.TipoCausaFineRapporto;
import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.TipoLivelloContratto;
import it.sincrono.entities.Utente;
import it.sincrono.repositories.DashboardCustomRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import it.sincrono.requests.AnagraficaRequestDto;
import jakarta.persistence.Query;

public class DashboardRepositoryImpl extends BaseRepositoryImpl implements DashboardCustomRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<AnagraficaDto> listCommesse(AnagraficaRequestDto anagraficaRequestDto) throws RepositoryException {
		try {

			String queryString = SqlStrings.SQL_LIST_COMMESSE;
			String subString = "";

			AnagraficaDto anagraficaDto = anagraficaRequestDto.getAnagraficaDto();

			if (anagraficaDto != null) {

				if (anagraficaDto.getAnagrafica() != null) {

					if (anagraficaDto.getAnagrafica() != null) {

						if (anagraficaDto.getAnagrafica().getCognome() != null
								&& anagraficaDto.getAnagrafica().getCognome() != "") {

							subString += " AND a.cognome LIKE '" + anagraficaDto.getAnagrafica().getCognome() + "%'";

						}
						if (anagraficaDto.getAnagrafica().getNome() != null
								&& anagraficaDto.getAnagrafica().getNome() != "") {

							subString += " AND a.nome LIKE '" + anagraficaDto.getAnagrafica().getNome() + "%'";

						}

						if (anagraficaDto.getAnagrafica().getTipoAzienda() != null) {

							if (anagraficaDto.getAnagrafica().getTipoAzienda().getId() != null) {

								subString += " AND a.id_tipo_azienda LIKE '"
										+ anagraficaDto.getAnagrafica().getTipoAzienda().getId() + "'";

							}

						}

					}

				}

				if (anagraficaDto.getCommesse() != null) {

					if (anagraficaDto.getCommesse().get(0).getAziendaCliente() != null
							&& anagraficaDto.getCommesse().get(0).getAziendaCliente() != "") {

						subString += " AND d.azienda_cliente LIKE '"
								+ anagraficaDto.getCommesse().get(0).getAziendaCliente() + "%'";

					}

				}
			}

			if (anagraficaRequestDto.getAnnoDataFine() != null) {

				subString += " AND YEAR(d.data_fine) LIKE '" + anagraficaRequestDto.getAnnoDataFine() + "%'";

				if (anagraficaRequestDto.getMeseDataFine() != null) {

					subString += " AND MONTH(d.data_fine) LIKE'" + anagraficaRequestDto.getMeseDataFine() + "%'";

				}
			}

			if (anagraficaRequestDto.getAnnoDataInizio() != null) {
				subString += " AND YEAR(d.data_inizio) LIKE '" + anagraficaRequestDto.getAnnoDataInizio() + "%'";
				if (anagraficaRequestDto.getMeseDataInizio() != null) {

					subString += " AND MONTH(d.data_inizio) LIKE'" + anagraficaRequestDto.getMeseDataInizio() + "%'";

				}
			}

			if (anagraficaRequestDto.getAnnoFineContratto() != null) {

				subString += " AND MONTH(d.data_fine_rapporto) LIKE'" + anagraficaRequestDto.getAnnoFineContratto()
						+ "%'";

				if (anagraficaRequestDto.getMeseFineContratto() != null) {

					subString += " AND YEAR(d.data_fine_rapporto) LIKE'" + anagraficaRequestDto.getMeseFineContratto()
							+ "%'";

				}
			}

			queryString += subString;

			Query query = entityManager.createNativeQuery(queryString);

			List<Object> list = query.getResultList();

			List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();
			for (Iterator<Object> it = list.iterator(); it.hasNext();) {
				Object[] result = (Object[]) it.next();

				Commessa commessa = new Commessa();
				if (result != null) {
					if (result[4] != null)
						commessa.setId((Integer) result[4]);
					if (result[5] != null)
						commessa.setAziendaCliente((String) result[5]);
					if (result[6] != null)
						commessa.setClienteFinale((String) result[6]);
					if (result[7] != null)
						commessa.setTitoloPosizione((String) result[7]);
					if (result[8] != null)
						commessa.setDistacco((Boolean) result[8]);
					if (result[9] != null)
						commessa.setDistaccoAzienda((String) result[9]);
					if (result[10] != null)
						commessa.setDistaccoData((Date) result[10]);
					if (result[11] != null)
						commessa.setDataInizio((Date) result[11]);
					if (result[12] != null)
						commessa.setDataFine((Date) result[12]);
					if (result[13] != null)
						commessa.setTariffaGiornaliera((String) result[13]);
					if (result[14] != null)
						commessa.setAziendaDiFatturazioneInterna((String) result[14]);

				}

				if (checkScaduta(commessa)) {

					AnagraficaDto anagraficaDtoApp = new AnagraficaDto();

					anagraficaDtoApp.setAnagrafica(new Anagrafica());

					anagraficaDtoApp.getAnagrafica().setId((Integer) result[0]);
					anagraficaDtoApp.getAnagrafica().setNome((String) result[1]);
					anagraficaDtoApp.getAnagrafica().setCognome((String) result[2]);
					anagraficaDtoApp.getAnagrafica().setCodiceFiscale((String) result[3]);

					anagraficaDtoApp.setCommesse(new ArrayList<Commessa>());

					anagraficaDtoApp.getCommesse().add(commessa);

					listAnagraficaDto.add(anagraficaDtoApp);

				}

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
				Object[] result = (Object[]) it.next();

				AnagraficaDto anagraficaDto = new AnagraficaDto();

				Anagrafica anagrafica = new Anagrafica();

				if (result != null) {

					anagrafica.setId((Integer) result[0]);
					anagrafica.setNome((String) result[1]);
					anagrafica.setCognome((String) result[2]);
					anagrafica.setCodiceFiscale((String) result[3]);

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
							commessa.setAziendaCliente((String) currentCommessa[1]);
						if (currentCommessa[2] != null)
							commessa.setClienteFinale((String) currentCommessa[2]);
						if (currentCommessa[3] != null)
							commessa.setTitoloPosizione((String) currentCommessa[3]);
						if (currentCommessa[4] != null)
							commessa.setDistacco((Boolean) currentCommessa[4]);
						if (currentCommessa[5] != null)
							commessa.setDistaccoAzienda((String) currentCommessa[5]);
						if (currentCommessa[6] != null)
							commessa.setDistaccoData((Date) currentCommessa[6]);
						if (currentCommessa[7] != null)
							commessa.setDataInizio((Date) currentCommessa[7]);
						if (currentCommessa[8] != null)
							commessa.setDataFine((Date) currentCommessa[8]);
						if (currentCommessa[9] != null)
							commessa.setTariffaGiornaliera((String) currentCommessa[9]);
						if (currentCommessa[10] != null)
							commessa.setAziendaDiFatturazioneInterna((String) currentCommessa[10]);

						anagraficaDto.getCommesse().add(commessa);

					}

					listAnagraficaDto.add(anagraficaDto);

				}

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

				if (localDateFine.isAfter(LocalDate.now())

						&&

						localDateFine.isBefore(LocalDate.now().plus(40, ChronoUnit.DAYS))) {

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

			Query query = entityManager.createNativeQuery(queryString);

			List<Object> list = query.getResultList();

			List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();
			for (Iterator<Object> it = list.iterator(); it.hasNext();) {
				Object[] result = (Object[]) it.next();
				AnagraficaDto anagraficaDto = new AnagraficaDto();

				Anagrafica anagrafica = new Anagrafica();

				anagrafica.setId((Integer) result[0]);
				if (result[1] != null)
					anagrafica.setNome((String) result[1]);
				if (result[2] != null)
					anagrafica.setCognome((String) result[2]);
				if (result[3] != null)
					anagrafica.setCodiceFiscale((String) result[3]);
				if (result[4] != null)
					anagrafica.setUtente(new Utente((Integer) result[4]));
				TipoAzienda tipoAzienda = new TipoAzienda();
				if (result[71] != null) {
					if ((Integer) result[71] != 0) {
						tipoAzienda.setId((Integer) result[71]);
						if (result[72] != null)
							tipoAzienda.setDescrizione((String) result[72]);
					}
				}
				anagrafica.setTipoAzienda(tipoAzienda);
				if (result[6] != null)
					anagrafica.setComuneDiNascita((String) result[6]);
				if (result[7] != null)
					anagrafica.setDataDiNascita((Date) result[7]);
				if (result[8] != null)
					anagrafica.setResidenza((String) result[8]);
				if (result[9] != null)
					anagrafica.setDomicilio((String) result[9]);
				if (result[10] != null)
					anagrafica.setCellularePrivato((String) result[10]);
				if (result[11] != null)
					anagrafica.setCellulareAziendale((String) result[11]);
				if (result[12] != null)
					anagrafica.setMailPrivata((String) result[12]);
				if (result[13] != null)
					anagrafica.setMailAziendale((String) result[13]);
				if (result[14] != null)
					anagrafica.setMailPec((String) result[14]);
				if (result[15] != null)
					anagrafica.setAltriTitoli((String) result[15]);
				if (result[16] != null)
					anagrafica.setTitoliDiStudio((String) result[16]);
				if (result[17] != null)
					anagrafica.setConiugato((Boolean) result[17]);
				if (result[18] != null)
					anagrafica.setFigliACarico((Boolean) result[18]);
				if (result[19] != null)
					anagrafica.setAttivo((Boolean) result[19]);
				if (result[20] != null)
					anagrafica.setAttesaLavori((Boolean) result[20]);
				anagraficaDto.setAnagrafica(anagrafica);

				/** SET DI CONTRATTO **/

				Contratto contratto = new Contratto();

				contratto.setId((Integer) result[21]);

				TipoContratto tipoContratto = new TipoContratto();
				if (result[66] != null)
					tipoContratto.setId((Integer) result[66]);
				if (result[67] != null)
					tipoContratto.setDescrizione((String) result[67]);
				contratto.setTipoContratto(tipoContratto);

				TipoLivelloContratto livelloContratto = new TipoLivelloContratto();
				if (result[62] != null)
					livelloContratto.setId((Integer) result[62]);
				if (result[63] != null)
					livelloContratto.setCcnl((String) result[63]);
				if (result[64] != null)
					livelloContratto.setLivello((String) result[64]);
				if (result[65] != null)
					livelloContratto.setMinimiRet23((Double.valueOf(((BigDecimal) result[65]).toString())));
				contratto.setTipoLivelloContratto(livelloContratto);
				/*
				 * SI UTILIZZA LA STESSA AZIENDA DELL'ANAGRAFICA
				 */
				contratto.setTipoAzienda(tipoAzienda);

				TipoCcnl ccnl = new TipoCcnl();
				if (result[68] != null)
					ccnl.setId((Integer) result[68]);
				if (result[69] != null)
					ccnl.setDescrizione((String) result[69]);
				if (result[70] != null)
					ccnl.setNumeroMensilita((Integer) result[70]);
				contratto.setTipoCcnl(ccnl);

				TipoCanaleReclutamento tipoCanaleReclutamento = new TipoCanaleReclutamento();
				if (result[71] != null)
					tipoCanaleReclutamento.setId((Integer) result[71]);
				if (result[72] != null)
					tipoCanaleReclutamento.setDescrizione((String) result[72]);
				contratto.setTipoCanaleReclutamento(tipoCanaleReclutamento);

				TipoCausaFineRapporto tipoCausaFineRapporto = new TipoCausaFineRapporto();
				if (result[73] != null)
					tipoCausaFineRapporto.setId((Integer) result[73]);
				if (result[74] != null)
					tipoCausaFineRapporto.setDescrizione((String) result[74]);
				contratto.setTipoCausaFineRapporto(tipoCausaFineRapporto);

				if (result[28] != null)
					contratto.setAttivo((Boolean) result[28]);
				if (result[29] != null)
					contratto.setQualifica((String) result[29]);
				if (result[30] != null)
					contratto.setSedeAssunzione((String) result[30]);
				if (result[31] != null)
					contratto.setDataAssunzione((Date) result[31]);
				if (result[32] != null)
					contratto.setDataInizioProva((Date) result[32]);
				if (result[33] != null)
					contratto.setDataFineProva((Date) result[33]);
				if (result[34] != null)
					contratto.setDataFineRapporto((Date) result[34]);
				if (result[35] != null)
					contratto.setMesiDurata((Integer) result[35]);
				if (result[36] != null)
					contratto.setLivelloAttuale((String) result[36]);
				if (result[37] != null)
					contratto.setLivelloFinale((String) result[37]);
				if (result[38] != null)
					contratto.setPartTime((Boolean) result[38]);
				if (result[39] != null)
					contratto.setPercentualePartTime((Double.valueOf(((BigDecimal) result[39]).toString())));
				if (result[40] != null)
					contratto.setRetribuzioneMensileLorda((Double.valueOf(((BigDecimal) result[40]).toString())));
				if (result[41] != null)
					contratto.setSuperminimoMensile((Double.valueOf(((BigDecimal) result[41]).toString())));
				if (result[42] != null)
					contratto.setRalAnnua((Double.valueOf(((BigDecimal) result[42]).toString())));
				if (result[43] != null)
					contratto.setSuperminimoRal((Double.valueOf(((BigDecimal) result[43]).toString())));
				if (result[44] != null)
					contratto.setDiariaMensile((Double.valueOf(((BigDecimal) result[44]).toString())));
				if (result[45] != null)
					contratto.setDiariaGiornaliera((Double.valueOf(((BigDecimal) result[45]).toString())));
				if (result[46] != null)
					contratto.setTicket((Boolean) result[46]);
				if (result[47] != null)
					contratto.setValoreTicket((Double.valueOf(((BigDecimal) result[47]).toString())));
				if (result[48] != null)
					contratto.setCategoriaProtetta((Boolean) result[48]);
				if (result[49] != null)
					contratto.setTutor((String) result[49]);
				if (result[50] != null)
					contratto.setPfi((Boolean) result[50]);
				if (result[51] != null)
					contratto.setCorsoSicurezza((Boolean) result[51]);
				if (result[52] != null)
					contratto.setDataCorsoSicurezza((Date) result[52]);
				if (result[53] != null)
					contratto.setPc((Boolean) result[53]);
				if (result[54] != null)
					contratto.setVisitaMedica((Boolean) result[54]);
				if (result[55] != null)
					contratto.setDataVisitaMedica((Date) result[55]);
				if (result[56] != null)
					contratto.setScattiAnzianita((Double.valueOf(((BigDecimal) result[56]).toString())));
				if (result[57] != null)
					contratto.setTariffaPartitaIva((Double.valueOf(((BigDecimal) result[57]).toString())));
				if (result[58] != null)
					contratto.setAssicurazioneObbligatoria((Boolean) result[58]);
				if (result[59] != null)
					contratto.setRetribuzioneNettaMensile((Double.valueOf(((BigDecimal) result[59]).toString())));
				if (result[60] != null)
					contratto.setRetribuzioneNettaGiornaliera((Double.valueOf(((BigDecimal) result[60]).toString())));
				if (result[61] != null)
					contratto.setDiariaAnnua((Double.valueOf(((BigDecimal) result[61]).toString())));

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

			if (dataSommata.isAfter(LocalDate.now())

					&&

					dataSommata.isBefore(LocalDate.now().plus(40, ChronoUnit.DAYS))) {

				listInScadenza.add(anagraficaDto);

			}

		}

		return listInScadenza;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnagraficaDto> listAllCommesse() throws RepositoryException {
		try {

			String queryString = SqlStrings.SQL_LIST_COMMESSE;

			Query query = entityManager.createNativeQuery(queryString);

			List<Object> list = query.getResultList();

			List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();
			for (Iterator<Object> it = list.iterator(); it.hasNext();) {
				Object[] result = (Object[]) it.next();

				Commessa commessa = new Commessa();
				if (result != null) {
					if (result[4] != null)
						commessa.setId((Integer) result[4]);
					if (result[5] != null)
						commessa.setAziendaCliente((String) result[5]);
					if (result[6] != null)
						commessa.setClienteFinale((String) result[6]);
					if (result[7] != null)
						commessa.setTitoloPosizione((String) result[7]);
					if (result[8] != null)
						commessa.setDistacco((Boolean) result[8]);
					if (result[9] != null)
						commessa.setDistaccoAzienda((String) result[9]);
					if (result[10] != null)
						commessa.setDistaccoData((Date) result[10]);
					if (result[11] != null)
						commessa.setDataInizio((Date) result[11]);
					if (result[12] != null)
						commessa.setDataFine((Date) result[12]);
					if (result[13] != null)
						commessa.setTariffaGiornaliera((String) result[13]);
					if (result[14] != null)
						commessa.setAziendaDiFatturazioneInterna((String) result[14]);

				}
				if (checkScaduta(commessa)) {

					AnagraficaDto anagraficaDtoApp = new AnagraficaDto();

					anagraficaDtoApp.setAnagrafica(new Anagrafica());

					anagraficaDtoApp.getAnagrafica().setId((Integer) result[0]);
					anagraficaDtoApp.getAnagrafica().setNome((String) result[1]);
					anagraficaDtoApp.getAnagrafica().setCognome((String) result[2]);
					anagraficaDtoApp.getAnagrafica().setCodiceFiscale((String) result[3]);

					anagraficaDtoApp.setCommesse(new ArrayList<Commessa>());

					anagraficaDtoApp.getCommesse().add(commessa);

					listAnagraficaDto.add(anagraficaDtoApp);

				}

			}

			return listAnagraficaDto;

		} catch (Exception e) {
			throw new RepositoryException(e);
		}

	}

	private Boolean checkScaduta(Commessa commessa) {

		Boolean check = false;

		if (convertorDate(commessa.getDataFine()).isBefore(LocalDate.now())) {

			check = true;

		}

		return check;

	}

	private LocalDate convertorDate(Date data) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH));

		return localDate;

	}
}

package it.sincrono.repositories.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.Ruolo;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoCanaleReclutamento;
import it.sincrono.entities.TipoCausaFineRapporto;
import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.TipoLivelloContratto;
import it.sincrono.entities.Utente;
import it.sincrono.repositories.AnagraficaCustomRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

public class AnagraficaRepositoryImpl extends BaseRepositoryImpl implements AnagraficaCustomRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<AnagraficaDto> filterListAnagraficaDto(AnagraficaDto anagraficaDto) throws RepositoryException {

		String queryString = SqlStrings.SQL_LIST_ANAGRAFICA;
		String subString = "";
		List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();

		/*
		 * AGGIUNTA PARAMETRI DI CONTROLLO ALLA QUERY
		 */

		if (anagraficaDto != null) {

			if (anagraficaDto.getAnagrafica() != null) {

				if (anagraficaDto.getAnagrafica().getNome() != null && anagraficaDto.getAnagrafica().getNome() != "") {

					subString += " AND a.nome LIKE '" + anagraficaDto.getAnagrafica().getNome() + "'";
				}
				if (anagraficaDto.getAnagrafica().getCognome() != null
						&& anagraficaDto.getAnagrafica().getCognome() != "") {
					subString += " AND a.cognome LIKE '" + anagraficaDto.getAnagrafica().getCognome() + "'";
				}

				if (anagraficaDto.getAnagrafica().getTipoAzienda() != null
						&& anagraficaDto.getAnagrafica().getTipoAzienda().getDescrizione() != "") {

					subString += " AND g.descrizione LIKE '"
							+ anagraficaDto.getAnagrafica().getTipoAzienda().getDescrizione() + "'";
				}
			}
			if (anagraficaDto.getContratto() != null) {

				if (anagraficaDto.getContratto().getRalAnnua() != null
						&& anagraficaDto.getContratto().getRalAnnua().toString() != "") {
					subString += " AND c.ral_annua LIKE '" + anagraficaDto.getContratto().getRalAnnua() + "'";
				}
				if (anagraficaDto.getContratto().getDataAssunzione() != null) {
					SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
					subString += " AND c.data_assunzione LIKE '"
							+ outputFormat.format(anagraficaDto.getContratto().getDataAssunzione()) + "'";
				}
				if (anagraficaDto.getContratto().getDataFineRapporto() != null) {
					subString += " AND c.data_fine_rapporto LIKE '" + anagraficaDto.getContratto().getDataFineRapporto()
							+ "'";
				}
				if (anagraficaDto.getContratto().getTipoLivelloContratto() != null
						&& anagraficaDto.getContratto().getTipoLivelloContratto().getLivello() != null
						&& anagraficaDto.getContratto().getTipoLivelloContratto().getLivello() != "") {
					subString += " AND d.ccnl LIKE '"
							+ anagraficaDto.getContratto().getTipoLivelloContratto().getLivello() + "'";
				}
				if (anagraficaDto.getContratto().getTipoContratto() != null
						&& anagraficaDto.getContratto().getTipoContratto().getDescrizione() != null
						&& anagraficaDto.getContratto().getTipoContratto().getDescrizione() != "") {
					subString += " AND e.descrizione LIKE '"
							+ anagraficaDto.getContratto().getTipoContratto().getDescrizione() + "'";
				}
				if (anagraficaDto.getContratto().getTipoCcnl() != null
						&& anagraficaDto.getContratto().getTipoCcnl().getDescrizione() != null
						&& anagraficaDto.getContratto().getTipoCcnl().getDescrizione() != "") {
					subString += " AND f.descrizione LIKE '"
							+ anagraficaDto.getContratto().getTipoCcnl().getDescrizione() + "'";
				}
				if (anagraficaDto.getContratto().getTipoAzienda() != null
						&& anagraficaDto.getContratto().getTipoAzienda().getDescrizione() != null
						&& anagraficaDto.getContratto().getTipoAzienda().getDescrizione() != "") {
					subString += " AND g.descrizione LIKE '"
							+ anagraficaDto.getContratto().getTipoAzienda().getDescrizione() + "'";
				}
				if (anagraficaDto.getContratto().getTipoCanaleReclutamento() != null
						&& anagraficaDto.getContratto().getTipoCanaleReclutamento().getDescrizione() != null
						&& anagraficaDto.getContratto().getTipoCanaleReclutamento().getDescrizione() != "") {
					subString += " AND h.descrizione LIKE '"
							+ anagraficaDto.getContratto().getTipoCanaleReclutamento().getDescrizione() + "'";
				}
				if (anagraficaDto.getContratto().getTipoCausaFineRapporto() != null
						&& anagraficaDto.getContratto().getTipoCausaFineRapporto().getDescrizione() != null
						&& anagraficaDto.getContratto().getTipoCausaFineRapporto().getDescrizione() != "") {
					subString += " AND i.descrizione LIKE '"
							+ anagraficaDto.getContratto().getTipoCanaleReclutamento().getDescrizione() + "'";
				}
			}
			queryString += subString;

			Query query = entityManager.createNativeQuery(queryString);
			List<Object> list = query.getResultList();
			if (list != null) {
				for (Iterator<Object> it = list.iterator(); it.hasNext();) {
					Object[] result = (Object[]) it.next();
					listAnagraficaDto.add(parsingAnagrafica(result, anagraficaDto));
				}
			}
		}
		return listAnagraficaDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnagraficaDto> listAnagraficaDto() throws RepositoryException {
		try {

			String queryString = SqlStrings.SQL_LIST_ANAGRAFICA;
			String subString = "";
			List<AnagraficaDto> lista = new ArrayList<>();
			Query query = entityManager.createNativeQuery(queryString);
			List<Object> list = query.getResultList();
			for (Iterator<Object> it = list.iterator(); it.hasNext();) {
				Object[] result = (Object[]) it.next();
				lista.add(parsingAnagrafica(result, null));
			}
			return lista;
		} catch (Exception e) {
			throw new RepositoryException(e);
		}

	}

	@Override
	public AnagraficaDto getAnagraficaDto(Integer id) throws RepositoryException {
		AnagraficaDto anagraficaDto = new AnagraficaDto();

		try {
			String queryString = SqlStrings.SQL_DETTAGLIO_ANAGRAFICA_DTO;

			queryString = queryString.replace("{0}", String.valueOf(id));
			String subString = "";
			Query query = entityManager.createNativeQuery(queryString);
			List<Object[]> lista = (List<Object[]>) query.getResultList();

			Object[] result = lista.get(0);
			if (result != null) {
				anagraficaDto = parsingAnagrafica(result, null);
				Ruolo ruolo = new Ruolo();
				if (result[77] != null)
					ruolo.setId((Integer) result[77]);
				if (result[79] != null)
					ruolo.setNome((String) result[79]);
				anagraficaDto.setRuolo(ruolo);
				anagraficaDto.setCommesse(parsingCommesse(subString, anagraficaDto.getAnagrafica().getId()));
			}
		} catch (

		Exception e) {
			throw new RepositoryException(e);
		}

		return anagraficaDto;
	}

	@Override
	public List<AnagraficaDto> listAnagraficaDtoContratti() throws RepositoryException {
		try {

			String queryStringContratti = SqlStrings.CONTRATTI_SCATTI_LIVELLO;

			Query queryContratti = entityManager.createNativeQuery(queryStringContratti);

			List<Object> listContratti = queryContratti.getResultList();

			String idContratti = new String();

			for (Iterator<Object> it = listContratti.iterator(); it.hasNext();) {

				idContratti += ((Integer) it.next()) + "";

				if (it.hasNext()) {
					idContratti += ",";
				}

			}

			String queryString = SqlStrings.SQL_ANAGRAFICA_DTO_CONTRATTI;

			queryString = queryString.replace("{0}", idContratti);

			Query query = entityManager.createNativeQuery(queryString);

			List<Object> listFilter = query.getResultList();

			List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();

			for (Iterator<Object> it = listFilter.iterator(); it.hasNext();) {
				Object[] result = (Object[]) it.next();

				AnagraficaDto anagraficDto = new AnagraficaDto();

				Anagrafica anagrafica = new Anagrafica();
				if (result[0] != null)
					anagrafica.setId((Integer) result[0]);
				if (result[1] != null)
					anagrafica.setNome((String) result[1]);
				if (result[2] != null)
					anagrafica.setCognome((String) result[2]);
				if (result[3] != null)
					anagrafica.setCodiceFiscale((String) result[3]);

				anagraficDto.setAnagrafica(anagrafica);

				Contratto contratto = new Contratto();

				if (result[4] != null)
					contratto.setDataAssunzione((Date) result[4]);

				TipoLivelloContratto livelloContratto = new TipoLivelloContratto();

				if (result[5] != null)
					livelloContratto.setCcnl((String) result[5]);
				if (result[6] != null)
					livelloContratto.setLivello((String) result[6]);
				if (result[7] != null)
					livelloContratto.setMinimiRet23((Double.valueOf(((BigDecimal) result[49]).toString())));

				contratto.setTipoLivelloContratto(livelloContratto);

				anagraficDto.setContratto(contratto);

				listAnagraficaDto.add(anagraficDto);

			}

			return listAnagraficaDto;

		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Transactional
	@Override
	public void deleteScattoContratti() throws RepositoryException {
		try {

			String queryString = SqlStrings.DELETE_CONTRATTI_SCATTI_LIVELLO;

			Query query = entityManager.createNativeQuery(queryString);

			query.executeUpdate();

		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public AnagraficaDto getAnagraficaDtoByToken(String token) throws RepositoryException {

		AnagraficaDto anagraficaDto = new AnagraficaDto();

		try {
			String queryString = SqlStrings.SQL_GET_ANAGRAFICA_BY_TOKEN;

			queryString = queryString.replace("{0}", token);

			Query query = entityManager.createNativeQuery(queryString);

			Object[] result = (Object[]) query.getSingleResult();

			if (result != null) {

				// SET DI ANAGRAFICA
				Anagrafica anagrafica = new Anagrafica();
				if (result[0] != null)
					anagrafica.setId((Integer) result[0]);
				if (result[1] != null)
					anagrafica.setNome((String) result[1]);
				if (result[2] != null)
					anagrafica.setCognome((String) result[2]);
				if (result[3] != null)
					anagrafica.setCodiceFiscale((String) result[3]);

				// SET DI UTENTE

				Utente utente = new Utente();
				if (result[4] != null)
					utente.setId((Integer) result[4]);

				anagrafica.setUtente(utente);

				TipoAzienda tipoAzienda = new TipoAzienda();
				if (result[5] != null)
					tipoAzienda.setId((Integer) result[5]);
				if (result[25] != null)
					tipoAzienda.setDescrizione((String) result[25]);
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

				anagraficaDto.setAnagrafica(anagrafica);

				Ruolo ruolo = new Ruolo();

				if (result[20] != null) {
					ruolo.setId((Integer) result[20]);
				}

				if (result[22] != null) {
					ruolo.setNome((String) result[22]);
				}

				anagraficaDto.setRuolo(ruolo);

			}
		} catch (Exception e) {
			throw new RepositoryException(e);
		}

		return anagraficaDto;
	}

	AnagraficaDto parsingAnagrafica(Object[] result, AnagraficaDto anagraficaDto) {
		List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();

		AnagraficaDto currentAnagraficaDto = new AnagraficaDto();

		/** SET DI ANAGRAFICA **/
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
		if (result[71] != null)
			tipoAzienda.setId((Integer) result[71]);
		if (result[72] != null)
			tipoAzienda.setDescrizione((String) result[72]);
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

		currentAnagraficaDto.setAnagrafica(anagrafica);

		/** SET DI CONTRATTO **/
		Contratto contratto = new Contratto();

		if (result[20] != null)
			contratto.setId((Integer) result[20]);

		TipoContratto tipoContratto = new TipoContratto();
		if (result[21] != null)
			tipoContratto.setId((Integer) result[21]);
		if (result[67] != null)
			tipoContratto.setDescrizione((String) result[67]);
		contratto.setTipoContratto(tipoContratto);

		TipoLivelloContratto livelloContratto = new TipoLivelloContratto();
		if (result[22] != null)
			livelloContratto.setId((Integer) result[22]);
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
		if (result[24] != null)
			ccnl.setId((Integer) result[24]);
		if (result[69] != null)
			ccnl.setDescrizione((String) result[69]);
		if (result[70] != null)
			ccnl.setDescrizione((String) result[70]);
		contratto.setTipoCcnl(ccnl);

		TipoCanaleReclutamento tipoCanaleReclutamento = new TipoCanaleReclutamento();
		if (result[73] != null)
			tipoCanaleReclutamento.setId((Integer) result[73]);
		if (result[74] != null)
			tipoCanaleReclutamento.setDescrizione((String) result[74]);
		contratto.setTipoCanaleReclutamento(tipoCanaleReclutamento);

		TipoCausaFineRapporto tipoCausaFineRapporto = new TipoCausaFineRapporto();
		if (result[75] != null)
			tipoCausaFineRapporto.setId((Integer) result[75]);
		if (result[76] != null)
			tipoCausaFineRapporto.setDescrizione((String) result[76]);
		contratto.setTipoCausaFineRapporto(tipoCausaFineRapporto);

		if (result[27] != null)
			contratto.setAttivo((Boolean) result[27]);
		if (result[28] != null)
			contratto.setQualifica((String) result[28]);
		if (result[29] != null)
			contratto.setSedeAssunzione((String) result[29]);
		if (result[30] != null)
			contratto.setDataAssunzione((Date) result[30]);
		if (result[31] != null)
			contratto.setDataInizioProva((Date) result[31]);
		if (result[32] != null)
			contratto.setDataFineProva((Date) result[32]);
		if (result[33] != null)
			contratto.setDataFineRapporto((Date) result[33]);
		if (result[34] != null)
			contratto.setMesiDurata((Integer) result[34]);
		if (result[35] != null)
			contratto.setLivelloAttuale((String) result[35]);
		if (result[36] != null)
			contratto.setLivelloFinale((String) result[36]);
		if (result[37] != null)
			contratto.setDimissioni((Boolean) result[37]);
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
			contratto.setPfi((String) result[50]);
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
		currentAnagraficaDto.setContratto(contratto);
		if (result[61] != null)
			contratto.setDiariaAnnua((Double.valueOf(((BigDecimal) result[61]).toString())));
		currentAnagraficaDto.setContratto(contratto);

		/*
		 * FILTRI PER LE COMMESSE
		 */

		String subString = "";
		if (anagraficaDto != null) {
			if (anagraficaDto.getCommesse() != null) {

				if (anagraficaDto.getCommesse().get(0) != null) {
					Commessa commessa = anagraficaDto.getCommesse().get(0);
					if (commessa.getAziendaCliente() != null && commessa.getAziendaCliente() != "") {
						subString += " AND c.azienda_cliente LIKE '" + commessa.getAziendaCliente() + "'";
					}
					if (commessa.getAttesaLavori() != null) {
						subString += " AND c.attesa_lavori LIKE '" + commessa.getAttesaLavori() + "'";
					}

				}
			}
		}
		List<Commessa> commesse = parsingCommesse(subString, (Integer) result[0]);
		currentAnagraficaDto.setCommesse(commesse);

		return currentAnagraficaDto;
	}

	List<Commessa> parsingCommesse(String subString, Integer id) {
		String queryString = SqlStrings.SQL_DETTAGLIO_COMMESSA;
		queryString = queryString.replace("{0}", String.valueOf(id)) + subString;
		Query query = entityManager.createNativeQuery(queryString);
		List<Object> resultList = query.getResultList();
		List<Commessa> listaCommesse = new ArrayList<Commessa>();
		for (Iterator<Object> iterator = resultList.iterator(); iterator.hasNext();) {
			Object[] currentCommessa = (Object[]) iterator.next();

			/** SET COMMESSA **/
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
				if (currentCommessa[11] != null)
					commessa.setAttivo((Boolean) currentCommessa[11]);
				if (currentCommessa[12] != null)
					commessa.setAttesaLavori((Boolean) currentCommessa[12]);

			}

			listaCommesse.add(commessa);

		}
		return listaCommesse;
	}
}
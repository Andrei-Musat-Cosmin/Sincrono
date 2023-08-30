package it.sincrono.repositories.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Ccnl;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.LivelloContratto;
import it.sincrono.entities.Ruolo;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.Utente;
import it.sincrono.repositories.AnagraficaCustomRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

public class AnagraficaRepositoryImpl extends BaseRepositoryImpl implements AnagraficaCustomRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<AnagraficaDto> listAnagraficaDto() throws RepositoryException {
		try {

			String queryString = SqlStrings.SQL_LIST_ANAGRAFICA;
			String subString = "";

			/*
			 * if (anagraficaDto != null) {
			 * 
			 * if (anagraficaDto.getAnagrafica() != null) {
			 * 
			 * if (anagraficaDto.getAnagrafica().getNome() != null &&
			 * anagraficaDto.getAnagrafica().getNome() != "") {
			 * 
			 * subString = "AND a.nome LIKE '" + anagraficaDto.getAnagrafica().getNome() +
			 * "'"; } if (anagraficaDto.getAnagrafica().getCognome() != null &&
			 * anagraficaDto.getAnagrafica().getCognome() != "") { subString +=
			 * " AND a.cognome LIKE '" + anagraficaDto.getAnagrafica().getCognome() + "'"; }
			 * 
			 * if (anagraficaDto.getAnagrafica().getAziendaTipo() != null &&
			 * anagraficaDto.getAnagrafica().getAziendaTipo() != "") {
			 * 
			 * subString = "AND a.azienda_tipo LIKE '" +
			 * anagraficaDto.getAnagrafica().getAziendaTipo() + "'"; } } if
			 * (anagraficaDto.getContratto() != null) {
			 * 
			 * if (anagraficaDto.getContratto().getRalAnnua() != null &&
			 * anagraficaDto.getContratto().getRalAnnua() != "") { subString +=
			 * " AND c.ral_annua LIKE '" + anagraficaDto.getContratto().getRalAnnua() + "'";
			 * } if (anagraficaDto.getContratto().getDataAssunzione() != null) { subString
			 * += " AND c.data_assunzione LIKE '" +
			 * anagraficaDto.getContratto().getDataAssunzione() + "'"; } if
			 * (anagraficaDto.getContratto().getDataFineRapporto() != null) { subString +=
			 * " AND c.data_fine_rapporto LIKE '" +
			 * anagraficaDto.getContratto().getDataFineRapporto() + "'"; } if
			 * (anagraficaDto.getContratto().getLivelloContratto() != null &&
			 * anagraficaDto.getContratto().getLivelloContratto().getDescrizione() != null
			 * && anagraficaDto.getContratto().getLivelloContratto().getDescrizione() != "")
			 * { subString += " AND f.descrizione LIKE '" +
			 * anagraficaDto.getContratto().getLivelloContratto().getDescrizione() + "'"; }
			 * if (anagraficaDto.getContratto().getContrattoNazionale() != null &&
			 * anagraficaDto.getContratto().getContrattoNazionale().getDescrizione() != null
			 * && anagraficaDto.getContratto().getContrattoNazionale().getDescrizione() !=
			 * "") { subString += " AND i.descrizione LIKE '" +
			 * anagraficaDto.getContratto().getContrattoNazionale().getDescrizione() + "'";
			 * } if (anagraficaDto.getContratto().getTipoContratto() != null &&
			 * anagraficaDto.getContratto().getTipoContratto().getDescrizione() != null &&
			 * anagraficaDto.getContratto().getTipoContratto().getDescrizione() != "") {
			 * subString += " AND g.descrizione LIKE '" +
			 * anagraficaDto.getContratto().getTipoContratto().getDescrizione() + "'"; } if
			 * (anagraficaDto.getContratto().getTipoAzienda() != null &&
			 * anagraficaDto.getContratto().getTipoAzienda().getDescrizione() != null &&
			 * anagraficaDto.getContratto().getTipoAzienda().getDescrizione() != "") {
			 * subString += " AND i.descrizione LIKE '" +
			 * anagraficaDto.getContratto().getTipoAzienda().getDescrizione() + "'"; } }
			 * 
			 * if (anagraficaDto.getCommessa() != null) {
			 * 
			 * if (anagraficaDto.getCommessa().getAzienda() != null &&
			 * anagraficaDto.getCommessa().getAzienda() != "") { subString +=
			 * "AND e.cliente LIKE '" + anagraficaDto.getCommessa().getAzienda() + "'"; } if
			 * (anagraficaDto.getCommessa().getCliente() != null &&
			 * anagraficaDto.getCommessa().getCliente() != "") { subString +=
			 * "AND e.azienda LIKE '" + anagraficaDto.getCommessa().getCliente() + "'"; } if
			 * (anagraficaDto.getCommessa().getNominativo() != null &&
			 * anagraficaDto.getCommessa().getNominativo() != "") { subString +=
			 * "AND e.nominativo LIKE '" + anagraficaDto.getCommessa().getNominativo() +
			 * "'"; }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * queryString = queryString.replace("{0}", subString);
			 */

			Query query = entityManager.createNativeQuery(queryString);

			List<Object> list = query.getResultList();

			List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();
			Integer lastResult = null;
			for (Iterator<Object> it = list.iterator(); it.hasNext();) {
				Object[] result = (Object[]) it.next();
				AnagraficaDto anagraficaDto = new AnagraficaDto();
				

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
					if (result[5] != null)
						anagrafica.setAziendaTipo((String) result[5]);
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

					/** SET DI CONTRATTO **/
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

				

				queryString = SqlStrings.SQL_DETTAGLIO_COMMESSA;
				
				queryString = queryString.replace("{0}", String.valueOf(anagraficaDto.getAnagrafica().getId()));
				query = null;
				query = entityManager.createNativeQuery(queryString);
				List<Object> listCommessa = query.getResultList();
				anagraficaDto.setCommesse(new ArrayList<Commessa>());
				for (Iterator<Object> iterator = listCommessa.iterator(); iterator.hasNext();) {
					Object[] currentCommessa = (Object[]) iterator.next();

					/** SET COMMESSA **/
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

			return listAnagraficaDto;

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

			Query query = entityManager.createNativeQuery(queryString);
			List<Object[]> lista = (List<Object[]>) query.getResultList();

			Object[] result = lista.get(0);

			if (result != null) {

				/** SET DI ANAGRAFICA **/
				Anagrafica anagrafica = new Anagrafica();
				if (result[0] != null)
					anagrafica.setId((Integer) result[0]);
				if (result[1] != null)
					anagrafica.setNome((String) result[1]);
				if (result[2] != null)
					anagrafica.setCognome((String) result[2]);
				if (result[3] != null)
					anagrafica.setCodiceFiscale((String) result[3]);
				if (result[4] != null)
					anagrafica.setUtente(new Utente((Integer) result[4]));
				if (result[5] != null)
					anagrafica.setAziendaTipo((String) result[5]);
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

				if (result[20] != null)
					ruolo.setId((Integer) result[20]);
				if (result[21] != null)
					ruolo.setRuolo(new Ruolo((Integer) result[21]));
				if (result[22] != null)
					ruolo.setNome((String) result[22]);
				if (result[23] != null)
					ruolo.setDescrizione((String) result[23]);

				anagraficaDto.setRuolo(ruolo);
				
				Contratto contratto = new Contratto();

				if (result[24] != null)
					contratto.setId((Integer) result[24]);

		
				TipoContratto tipoContratto = new TipoContratto();
				if (result[25] != null)
					tipoContratto.setId((Integer) result[25]);
				if (result[70] != null)
					tipoContratto.setDescrizione((String) result[70]);

				contratto.setTipoContratto(tipoContratto);

				LivelloContratto livelloContratto = new LivelloContratto();
				if (result[26] != null)
					livelloContratto.setId((Integer) result[26]);
				if (result[62] != null)
					livelloContratto.setCcnl((String) result[62]);
				if (result[63] != null)
					livelloContratto.setLivello((String) result[63]);
				if (result[64] != null)
					livelloContratto.setMinimiRet23((String) result[64]);
				contratto.setLivelloContratto(livelloContratto);

				TipoAzienda tipoAzienda = new TipoAzienda();
				if (result[27] != null)
					tipoAzienda.setId((Integer) result[27]);
				if (result[68] != null)
					tipoAzienda.setDescrizione((String) result[68]);
				contratto.setTipoAzienda(tipoAzienda);

		
				Ccnl contrattoNazionale = new Ccnl();

				if (result[28] != null)
					contrattoNazionale.setId((Integer) result[28]);
				if (result[66] != null)
					contrattoNazionale.setDescrizione((String) result[66]);
				contratto.setTipoCcnl(contrattoNazionale);
				if (result[29] != null)
					contratto.setAttivo((Boolean) result[29]);
				if (result[30] != null)
					contratto.setQualifica((String) result[30]);
				if (result[31] != null)
					contratto.setSedeAssunzione((String) result[31]);
				if (result[32] != null)
					contratto.setDataAssunzione((Date) result[32]);
				if (result[33] != null)
					contratto.setDataInizioProva((Date) result[33]);
				if (result[34] != null)
					contratto.setDataFineProva((Date) result[34]);
				if (result[35] != null)
					contratto.setDataFineRapporto((Date) result[35]);
				if (result[36] != null)
					contratto.setMesiDurata((Integer) result[36]);
				if (result[37] != null)
					contratto.setLivelloAttuale((String) result[37]);
				if (result[38] != null)
					contratto.setLivelloFinale((String) result[38]);
				if (result[39] != null)
					contratto.setDimissioni((Boolean) result[39]);
				if (result[40] != null)
					contratto.setPartTime((Boolean) result[40]);
				if (result[41] != null)
					contratto.setPartTimeA((Boolean) result[41]);
				if (result[42] != null)
					contratto.setRetribuzioneMensileLorda((String) result[42]);
				if (result[43] != null)
					contratto.setSuperminimoMensile((String) result[43]);
				if (result[44] != null)
					contratto.setRalAnnua((String) result[44]);
				if (result[45] != null)
					contratto.setSuperminimoRal((String) result[45]);
				if (result[46] != null)
					contratto.setDiariaMese((String) result[46]);
				if (result[47] != null)
					contratto.setDiariaGg((String) result[47]);
				if (result[48] != null)
					contratto.setTicket((String) result[48]);
				if (result[49] != null)
					contratto.setValoreTicket((String) result[49]);
				if (result[50] != null)
					contratto.setCategoriaProtetta((Boolean) result[50]);
				if (result[51] != null)
					contratto.setTutor((String) result[51]);
				if (result[52] != null)
					contratto.setPfi((String) result[52]);
				if (result[53] != null)
					contratto.setCorsoSicurezza((Date) result[53]);
				if (result[54] != null)
					contratto.setMotivazioneFineRapporto((String) result[54]);
				if (result[55] != null)
					contratto.setPc((Boolean) result[55]);

			

				if (result[57] != null)
					contratto.setScattiAnzianita((String) result[57]);
				if (result[58] != null)
					contratto.setTariffaPartitaIva((String) result[58]);
				if (result[59] != null)
					contratto.setCanaleReclutamento((String) result[59]);
				if (result[60] != null)
					contratto.setAssicurazioneObbligatoria((String) result[60]);

				anagraficaDto.setContratto(contratto);

			

					queryString = SqlStrings.SQL_DETTAGLIO_COMMESSA;

					queryString = queryString.replace("{0}", String.valueOf(id));
					query = null;
					query = entityManager.createNativeQuery(queryString);
					List<Object> list = query.getResultList();
					anagraficaDto.setCommesse(new ArrayList<Commessa>());
					for (Iterator<Object> it = list.iterator(); it.hasNext();) {
						Object[] currentCommessa = (Object[]) it.next();

					/** SET COMMESSA **/
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
					
				/** SET LISTA COMMESSE **/
				
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

				LivelloContratto livelloContratto = new LivelloContratto();

				if (result[5] != null)
					livelloContratto.setCcnl((String) result[5]);
				if (result[6] != null)
					livelloContratto.setLivello((String) result[6]);
				if (result[7] != null)
					livelloContratto.setMinimiRet23((String) result[7]);

				contratto.setLivelloContratto(livelloContratto);

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
				/*
				 * Utente utente = new Utente(); if (result[4] != null) utente.setId((Integer)
				 * result[4]);
				 * 
				 * anagrafica.setUtente(utente);
				 */

				if (result[5] != null)
					anagrafica.setAziendaTipo((String) result[5]);
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

				Utente utente = new Utente();

				if (result[24] != null) {

					utente.setId((Integer) result[24]);

				}

				anagraficaDto.getAnagrafica().setUtente(utente);

			}
		} catch (Exception e) {
			throw new RepositoryException(e);
		}

		return anagraficaDto;
	}

}

package it.sincrono.repositories.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.ContrattoNazionale;
import it.sincrono.entities.LivelloContratto;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.Utente;
import it.sincrono.repositories.AnagraficaCustomRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class AnagraficaRepositoryImpl extends BaseRepositoryImpl implements AnagraficaCustomRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<AnagraficaDto> listAnagraficaDto() throws RepositoryException {
		try {

			String queryString = SqlStrings.SQL_LIST_ANAGRAFICA;
			String subString = "";

			/*if (anagraficaDto != null) {

				if (anagraficaDto.getAnagrafica() != null) {

					if (anagraficaDto.getAnagrafica().getNome() != null
							&& anagraficaDto.getAnagrafica().getNome() != "") {

						subString = "AND a.nome LIKE '" + anagraficaDto.getAnagrafica().getNome() + "'";
					}
					if (anagraficaDto.getAnagrafica().getCognome() != null
							&& anagraficaDto.getAnagrafica().getCognome() != "") {
						subString += " AND a.cognome LIKE '" + anagraficaDto.getAnagrafica().getCognome() + "'";
					}

					if (anagraficaDto.getAnagrafica().getAziendaTipo() != null
							&& anagraficaDto.getAnagrafica().getAziendaTipo() != "") {

						subString = "AND a.azienda_tipo LIKE '" + anagraficaDto.getAnagrafica().getAziendaTipo() + "'";
					}
				}
				if (anagraficaDto.getContratto() != null) {

					if (anagraficaDto.getContratto().getRalAnnua() != null
							&& anagraficaDto.getContratto().getRalAnnua() != "") {
						subString += " AND c.ral_annua LIKE '" + anagraficaDto.getContratto().getRalAnnua() + "'";
					}
					if (anagraficaDto.getContratto().getDataAssunzione() != null) {
						subString += " AND c.data_assunzione LIKE '" + anagraficaDto.getContratto().getDataAssunzione()
								+ "'";
					}
					if (anagraficaDto.getContratto().getDataFineRapporto() != null) {
						subString += " AND c.data_fine_rapporto LIKE '"
								+ anagraficaDto.getContratto().getDataFineRapporto() + "'";
					}
					if (anagraficaDto.getContratto().getLivelloContratto() != null
							&& anagraficaDto.getContratto().getLivelloContratto().getDescrizione() != null
							&& anagraficaDto.getContratto().getLivelloContratto().getDescrizione() != "") {
						subString += " AND f.descrizione LIKE '"
								+ anagraficaDto.getContratto().getLivelloContratto().getDescrizione() + "'";
					}
					if (anagraficaDto.getContratto().getContrattoNazionale() != null
							&& anagraficaDto.getContratto().getContrattoNazionale().getDescrizione() != null
							&& anagraficaDto.getContratto().getContrattoNazionale().getDescrizione() != "") {
						subString += " AND i.descrizione LIKE '"
								+ anagraficaDto.getContratto().getContrattoNazionale().getDescrizione() + "'";
					}
					if (anagraficaDto.getContratto().getTipoContratto() != null
							&& anagraficaDto.getContratto().getTipoContratto().getDescrizione() != null
							&& anagraficaDto.getContratto().getTipoContratto().getDescrizione() != "") {
						subString += " AND g.descrizione LIKE '"
								+ anagraficaDto.getContratto().getTipoContratto().getDescrizione() + "'";
					}
					if (anagraficaDto.getContratto().getTipoAzienda() != null
							&& anagraficaDto.getContratto().getTipoAzienda().getDescrizione() != null
							&& anagraficaDto.getContratto().getTipoAzienda().getDescrizione() != "") {
						subString += " AND i.descrizione LIKE '"
								+ anagraficaDto.getContratto().getTipoAzienda().getDescrizione() + "'";
					}
				}

				if (anagraficaDto.getCommessa() != null) {

					if (anagraficaDto.getCommessa().getAzienda() != null
							&& anagraficaDto.getCommessa().getAzienda() != "") {
						subString += "AND e.cliente LIKE '" + anagraficaDto.getCommessa().getAzienda() + "'";
					}
					if (anagraficaDto.getCommessa().getCliente() != null
							&& anagraficaDto.getCommessa().getCliente() != "") {
						subString += "AND e.azienda LIKE '" + anagraficaDto.getCommessa().getCliente() + "'";
					}
					if (anagraficaDto.getCommessa().getNominativo() != null
							&& anagraficaDto.getCommessa().getNominativo() != "") {
						subString += "AND e.nominativo LIKE '" + anagraficaDto.getCommessa().getNominativo() + "'";
					}

				}

			}

			queryString = queryString.replace("{0}", subString);*/
			
			Query query = entityManager.createNativeQuery(queryString);

			List<Object> listFilter = query.getResultList();

			List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();

			for (Iterator<Object> it = listFilter.iterator(); it.hasNext();) {
				Object[] result = (Object[]) it.next();

				AnagraficaDto anagraficDtoFilter = new AnagraficaDto();

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

				anagraficDtoFilter.setAnagrafica(anagrafica);

				// SET DI CONTRATTO
				Contratto contratto = new Contratto();

				if (result[23] != null)
					contratto.setId((Integer) result[23]);

				// SET DI TIPO CONTRATTO
				TipoContratto tipoContratto = new TipoContratto();
				if (result[24] != null)
					tipoContratto.setId((Integer) result[24]);
				if (result[82] != null)
					tipoContratto.setDescrizione((String) result[82]);

				contratto.setTipoContratto(tipoContratto);

				// SET DI TIPO LIVELLO
				LivelloContratto livelloContratto = new LivelloContratto();
				if (result[25] != null)
					livelloContratto.setId((Integer) result[25]);
				if (result[78] != null)
					livelloContratto.setCcnl((String) result[78]);
				if (result[79] != null)
					livelloContratto.setDescrizione((String) result[79]);
				if (result[80] != null)
					livelloContratto.setMinimiRet23((String) result[80]);
				contratto.setLivelloContratto(livelloContratto);

				// SET TIPO AZIENDA
				TipoAzienda tipoAzienda = new TipoAzienda();
				if (result[26] != null)
					tipoAzienda.setId((Integer) result[26]);
				if (result[86] != null)
					tipoAzienda.setDescrizione((String) result[86]);
				contratto.setTipoAzienda(tipoAzienda);

				// SET DI CONTRATTO NAZIONALE
				ContrattoNazionale contrattoNazionale = new ContrattoNazionale();

				if (result[27] != null)
					contrattoNazionale.setId((Integer) result[27]);
				if (result[84] != null)
					contrattoNazionale.setDescrizione((String) result[84]);
				contratto.setContrattoNazionale(contrattoNazionale);

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
					contratto.setDimissioni((Boolean) result[38]);
				if (result[39] != null)
					contratto.setPartTime((Boolean) result[39]);
				if (result[40] != null)
					contratto.setPartTimeA((Boolean) result[40]);
				if (result[41] != null)
					contratto.setRetribuzioneMensileLorda((String) result[41]);
				if (result[42] != null)
					contratto.setSuperminimoMensile((String) result[42]);
				if (result[43] != null)
					contratto.setRalAnnua((String) result[43]);
				if (result[44] != null)
					contratto.setSuperminimoRal((String) result[44]);
				if (result[45] != null)
					contratto.setDiariaMese((String) result[45]);
				if (result[46] != null)
					contratto.setDiariaGg((String) result[46]);
				if (result[47] != null)
					contratto.setTicket((String) result[47]);
				if (result[48] != null)
					contratto.setValoreTicket((String) result[48]);
				if (result[49] != null)
					contratto.setCategoriaProtetta((Boolean) result[49]);
				if (result[50] != null)
					contratto.setTutor((String) result[50]);
				if (result[51] != null)
					contratto.setPfi((String) result[51]);
				if (result[52] != null)
					contratto.setCorsoSicurezza((Date) result[52]);
				if (result[53] != null)
					contratto.setMotivazioneFineRapporto((String) result[53]);
				if (result[54] != null)
					contratto.setPc((Boolean) result[54]);

				// if (result[55] != null)contratto.setDataVisitaMedica((Boolean) result[56]);

				if (result[56] != null)
					contratto.setScattiAnzianita((String) result[56]);
				if (result[57] != null)
					contratto.setTariffaPartitaIva((String) result[57]);
				if (result[58] != null)
					contratto.setCanaleReclutamento((String) result[58]);
				if (result[59] != null)
					contratto.setAssicurazioneObbligatoria((String) result[59]);

				anagraficDtoFilter.setContratto(contratto);

				// SET COMMESSA
				Commessa commessa = new Commessa();
				if (result[63] != null)
					commessa.setId((Integer) result[63]);
				if (result[64] != null)
					commessa.setCliente((String) result[64]);
				if (result[65] != null)
					commessa.setClienteFinale((String) result[65]);
				if (result[66] != null)
					commessa.setTitoloPosizione((String) result[66]);
				if (result[67] != null)
					commessa.setDistacco((String) result[67]);
				if (result[68] != null)
					commessa.setDataInizio((Date) result[68]);
				if (result[69] != null)
					commessa.setDataFine((Date) result[69]);
				if (result[70] != null)
					commessa.setCostoMese((String) result[70]);
				if (result[71] != null)
					commessa.setTariffaGiornaliera((String) result[71]);
				if (result[72] != null)
					commessa.setNominativo((String) result[72]);
				if (result[73] != null)
					commessa.setAzienda((String) result[73]);
				if (result[74] != null)
					commessa.setAziendaDiFatturazioneInterna((String) result[74]);
				if (result[75] != null)
					commessa.setStato((Boolean) result[75]);
				if (result[76] != null)
					commessa.setAttesaLavori((String) result[76]);

				anagraficDtoFilter.setCommessa(commessa);

				listAnagraficaDto.add(anagraficDtoFilter);

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

				// SET DI CONTRATTO
				Contratto contratto = new Contratto();

				if (result[23] != null)
					contratto.setId((Integer) result[23]);

				// SET DI TIPO CONTRATTO
				TipoContratto tipoContratto = new TipoContratto();
				if (result[24] != null)
					tipoContratto.setId((Integer) result[24]);
				if (result[82] != null)
					tipoContratto.setDescrizione((String) result[82]);

				contratto.setTipoContratto(tipoContratto);

				// SET DI TIPO LIVELLO
				LivelloContratto livelloContratto = new LivelloContratto();
				if (result[25] != null)
					livelloContratto.setId((Integer) result[25]);
				if (result[78] != null)
					livelloContratto.setCcnl((String) result[78]);
				if (result[79] != null)
					livelloContratto.setDescrizione((String) result[79]);
				if (result[80] != null)
					livelloContratto.setMinimiRet23((String) result[80]);
				contratto.setLivelloContratto(livelloContratto);

				// SET TIPO AZIENDA
				TipoAzienda tipoAzienda = new TipoAzienda();
				if (result[26] != null)
					tipoAzienda.setId((Integer) result[26]);
				if (result[86] != null)
					tipoAzienda.setDescrizione((String) result[86]);
				contratto.setTipoAzienda(tipoAzienda);

				// SET DI CONTRATTO NAZIONALE
				ContrattoNazionale contrattoNazionale = new ContrattoNazionale();

				if (result[27] != null)
					contrattoNazionale.setId((Integer) result[27]);
				if (result[84] != null)
					contrattoNazionale.setDescrizione((String) result[84]);
				contratto.setContrattoNazionale(contrattoNazionale);

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
					contratto.setDimissioni((Boolean) result[38]);
				if (result[39] != null)
					contratto.setPartTime((Boolean) result[39]);
				if (result[40] != null)
					contratto.setPartTimeA((Boolean) result[40]);
				if (result[41] != null)
					contratto.setRetribuzioneMensileLorda((String) result[41]);
				if (result[42] != null)
					contratto.setSuperminimoMensile((String) result[42]);
				if (result[43] != null)
					contratto.setRalAnnua((String) result[43]);
				if (result[44] != null)
					contratto.setSuperminimoRal((String) result[44]);
				if (result[45] != null)
					contratto.setDiariaMese((String) result[45]);
				if (result[46] != null)
					contratto.setDiariaGg((String) result[46]);
				if (result[47] != null)
					contratto.setTicket((String) result[47]);
				if (result[48] != null)
					contratto.setValoreTicket((String) result[48]);
				if (result[49] != null)
					contratto.setCategoriaProtetta((Boolean) result[49]);
				if (result[50] != null)
					contratto.setTutor((String) result[50]);
				if (result[51] != null)
					contratto.setPfi((String) result[51]);
				if (result[52] != null)
					contratto.setCorsoSicurezza((Date) result[52]);
				if (result[53] != null)
					contratto.setMotivazioneFineRapporto((String) result[53]);
				if (result[54] != null)
					contratto.setPc((Boolean) result[54]);

				// if (result[55] != null)contratto.setDataVisitaMedica((Boolean) result[56]);

				if (result[56] != null)
					contratto.setScattiAnzianita((String) result[56]);
				if (result[57] != null)
					contratto.setTariffaPartitaIva((String) result[57]);
				if (result[58] != null)
					contratto.setCanaleReclutamento((String) result[58]);
				if (result[59] != null)
					contratto.setAssicurazioneObbligatoria((String) result[59]);

				anagraficaDto.setContratto(contratto);

				// SET COMMESSA
				Commessa commessa = new Commessa();
				if (result[63] != null)
					commessa.setId((Integer) result[63]);
				if (result[64] != null)
					commessa.setCliente((String) result[64]);
				if (result[65] != null)
					commessa.setClienteFinale((String) result[65]);
				if (result[66] != null)
					commessa.setTitoloPosizione((String) result[66]);
				if (result[67] != null)
					commessa.setDistacco((String) result[67]);
				if (result[68] != null)
					commessa.setDataInizio((Date) result[68]);
				if (result[69] != null)
					commessa.setDataFine((Date) result[69]);
				if (result[70] != null)
					commessa.setCostoMese((String) result[70]);
				if (result[71] != null)
					commessa.setTariffaGiornaliera((String) result[71]);
				if (result[72] != null)
					commessa.setNominativo((String) result[72]);
				if (result[73] != null)
					commessa.setAzienda((String) result[73]);
				if (result[74] != null)
					commessa.setAziendaDiFatturazioneInterna((String) result[74]);
				if (result[75] != null)
					commessa.setStato((Boolean) result[75]);
				if (result[76] != null)
					commessa.setAttesaLavori((String) result[76]);

				anagraficaDto.setCommessa(commessa);

			}
		} catch (Exception e) {
			throw new RepositoryException(e);
		}

		return anagraficaDto;
	}

}

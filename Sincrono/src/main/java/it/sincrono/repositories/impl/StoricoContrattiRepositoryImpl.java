package it.sincrono.repositories.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.TipoLivelloContratto;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoContratto;
import it.sincrono.repositories.StoricoContrattiCustomRepository;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class StoricoContrattiRepositoryImpl extends BaseRepositoryImpl implements StoricoContrattiCustomRepository {

	@Override
	public List<Contratto> getStoricoContratti(Integer id) throws RepositoryException {

		try {

			String queryString = SqlStrings.SQL_STORICO_CONTRATTI;

			queryString = queryString.replace("{0}", new String().valueOf(id));

			Query query = entityManager.createNativeQuery(queryString);

			List<Object> listFilter = query.getResultList();

			List<Contratto> listStoricoContratti = new ArrayList<Contratto>();

			for (Iterator<Object> it = listFilter.iterator(); it.hasNext();) {
				Object[] result = (Object[]) it.next();

				Contratto contratto = new Contratto();

				TipoContratto tipoContratto = new TipoContratto();

				if (result[37] != null)
					tipoContratto.setDescrizione((String) result[37]);

				contratto.setTipoContratto(tipoContratto);

				TipoLivelloContratto livelloContratto = new TipoLivelloContratto();
				if (result[39] != null)
					livelloContratto.setCcnl((String) result[39]);
				if (result[38] != null)
					livelloContratto.setLivello((String) result[38]);
				if (result[40] != null)
					livelloContratto.setMinimiRet23((String) result[40]);
				contratto.setLivelloContratto(livelloContratto);

				TipoAzienda tipoAzienda = new TipoAzienda();
				if (result[41] != null)
					tipoAzienda.setDescrizione((String) result[41]);
				contratto.setTipoAzienda(tipoAzienda);

				TipoCcnl ccnl = new TipoCcnl();

				if (result[42] != null)
					ccnl.setDescrizione((String) result[42]);
				contratto.setTipoCcnl(ccnl);

				if (result[6] != null)
					contratto.setQualifica((String) result[6]);
				if (result[7] != null)
					contratto.setSedeAssunzione((String) result[7]);
				if (result[8] != null)
					contratto.setDataAssunzione((Date) result[8]);
				if (result[9] != null)
					contratto.setDataInizioProva((Date) result[9]);
				if (result[10] != null)
					contratto.setDataFineProva((Date) result[10]);
				if (result[11] != null)
					contratto.setDataFineRapporto((Date) result[11]);
				if (result[12] != null)
					contratto.setMesiDurata((Integer) result[12]);
				if (result[13] != null)
					contratto.setLivelloAttuale((String) result[13]);
				if (result[14] != null)
					contratto.setLivelloFinale((String) result[14]);
				if (result[15] != null)
					contratto.setDimissioni((Boolean) result[15]);
				if (result[16] != null)
					contratto.setPartTime((Boolean) result[16]);
				if (result[17] != null)
					contratto.setPartTimeA((Boolean) result[17]);
				if (result[18] != null)
					contratto.setRetribuzioneMensileLorda((String) result[18]);
				if (result[19] != null)
					contratto.setSuperminimoMensile((String) result[19]);
				if (result[20] != null)
					contratto.setRalAnnua((String) result[20]);
				if (result[21] != null)
					contratto.setSuperminimoRal((String) result[21]);
				if (result[22] != null)
					contratto.setDiariaMese((String) result[22]);
				if (result[23] != null)
					contratto.setDiariaGg((String) result[23]);
				if (result[24] != null)
					contratto.setTicket((String) result[24]);
				if (result[25] != null)
					contratto.setValoreTicket((String) result[25]);
				if (result[26] != null)
					contratto.setCategoriaProtetta((Boolean) result[26]);
				if (result[27] != null)
					contratto.setTutor((String) result[27]);
				if (result[28] != null)
					contratto.setPfi((String) result[28]);
				if (result[29] != null)
					contratto.setCorsoSicurezza((Date) result[29]);
				if (result[30] != null)
					contratto.setMotivazioneFineRapporto((String) result[30]);
				if (result[31] != null)
					contratto.setPc((Boolean) result[31]);

				if (result[33] != null)
					contratto.setScattiAnzianita((String) result[33]);
				if (result[34] != null)
					contratto.setTariffaPartitaIva((String) result[34]);
				if (result[35] != null)
					contratto.setCanaleReclutamento((String) result[35]);
				if (result[36] != null)
					contratto.setAssicurazioneObbligatoria((String) result[36]);

				listStoricoContratti.add(contratto);

			}

			return listStoricoContratti;

		} catch (Exception e) {

			throw new RepositoryException(e);
		}

	}

}

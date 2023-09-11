package it.sincrono.repositories.impl;

import java.math.BigDecimal;

import it.sincrono.entities.TipoCanaleReclutamento;

import it.sincrono.entities.TipoCausaFineRapporto;



import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.sincrono.entities.Contratto;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.TipoLivelloContratto;
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

				if (result[42] != null)
					tipoContratto.setDescrizione((String) result[42]);

				contratto.setTipoContratto(tipoContratto);

				TipoLivelloContratto livelloContratto = new TipoLivelloContratto();
				if (result[44] != null)
					livelloContratto.setCcnl((String) result[44]);
				if (result[43] != null)
					livelloContratto.setLivello((String) result[43]);
				if (result[45] != null)
					livelloContratto.setMinimiRet23((Double.valueOf(((BigDecimal) result[45]).toString())));
				contratto.setTipoLivelloContratto(livelloContratto);

				TipoAzienda tipoAzienda = new TipoAzienda();
				if (result[46] != null)
					tipoAzienda.setDescrizione((String) result[46]);
				contratto.setTipoAzienda(tipoAzienda);

				TipoCcnl ccnl = new TipoCcnl();

				if (result[47] != null)
					ccnl.setDescrizione((String) result[47]);
				
				
				if (result[48] != null)
					ccnl.setNumeroMensilita((Integer) result[48]);
				
				
				contratto.setTipoCcnl(ccnl);
				
				
				
				TipoCanaleReclutamento tipoCanaleReclutamento = new TipoCanaleReclutamento();
				
				if (result[49] != null)
					tipoCanaleReclutamento.setDescrizione((String) result[49]);
				
				contratto.setTipoCanaleReclutamento(tipoCanaleReclutamento);
				
				TipoCausaFineRapporto TipoCausaFineRapporto = new TipoCausaFineRapporto();
				
				if (result[50] != null)
					TipoCausaFineRapporto.setDescrizione((String) result[50]);
				
				contratto.setTipoCausaFineRapporto(TipoCausaFineRapporto);
				
				

				if (result[8] != null)
					contratto.setQualifica((String) result[8]);
				if (result[9] != null)
					contratto.setSedeAssunzione((String) result[9]);
				if (result[10] != null)
					contratto.setDataAssunzione((Date) result[10]);
				if (result[11] != null)
					contratto.setDataInizioProva((Date) result[11]);
				if (result[12] != null)
					contratto.setDataFineProva((Date) result[12]);
				if (result[13] != null)
					contratto.setDataFineRapporto((Date) result[13]);
				if (result[14] != null)
					contratto.setMesiDurata((Integer) result[14]);
				if (result[15] != null)
					contratto.setLivelloAttuale((String) result[15]);
				if (result[16] != null)
					contratto.setLivelloFinale((String) result[16]);
				if (result[17] != null)
					contratto.setDimissioni((Boolean) result[17]);
				if (result[18] != null)
					contratto.setPartTime((Boolean) result[18]);
				if (result[19] != null)
					contratto.setPercentualePartTime((Double.valueOf(((BigDecimal) result[19]).toString())));
				if (result[20] != null)
					contratto.setRetribuzioneMensileLorda((Double.valueOf(((BigDecimal) result[20]).toString())));
				if (result[21] != null)
					contratto.setSuperminimoMensile((Double.valueOf(((BigDecimal) result[21]).toString())));
				if (result[22] != null)
					contratto.setRalAnnua((Double.valueOf(((BigDecimal) result[22]).toString())));
				if (result[23] != null)
					contratto.setSuperminimoRal((Double.valueOf(((BigDecimal) result[23]).toString())));
				if (result[24] != null)
					contratto.setDiariaMensile((Double.valueOf(((BigDecimal) result[24]).toString())));
				if (result[25] != null)
					contratto.setDiariaGiornaliera((Double.valueOf(((BigDecimal) result[275]).toString())));
				if (result[26] != null)
					contratto.setTicket((Boolean) result[26]);
				if (result[27] != null)
					contratto.setValoreTicket((Double.valueOf(((BigDecimal) result[27]).toString())));
				if (result[28] != null)
					contratto.setCategoriaProtetta((Boolean) result[28]);
				if (result[29] != null)
					contratto.setTutor((String) result[29]);
				if (result[30] != null)
					contratto.setPfi((String) result[30]);
				if (result[31] != null)
					contratto.setCorsoSicurezza((Boolean) result[31]);
				if (result[32] != null)
					contratto.setDataCorsoSicurezza((Date) result[32]);
				if (result[33] != null)
					contratto.setPc((Boolean) result[33]);
				if (result[34] != null)
					contratto.setVisitaMedica((Boolean) result[34]);
				if (result[35] != null)
					contratto.setDataVisitaMedica((Date) result[35]);
				if (result[36] != null)
					contratto.setScattiAnzianita((Double.valueOf(((BigDecimal) result[36]).toString())));
				if (result[37] != null)
					contratto.setTariffaPartitaIva((Double.valueOf(((BigDecimal) result[37]).toString())));
				if (result[38] != null)
					contratto.setAssicurazioneObbligatoria((Boolean) result[38]);
				if (result[39] != null)
					contratto.setRetribuzioneNettaGiornaliera((Double.valueOf(((BigDecimal) result[39]).toString())));
				if (result[40] != null)
					contratto.setRetribuzioneNettaMensile((Double.valueOf(((BigDecimal) result[40]).toString())));
				if (result[41] != null)
					contratto.setDiariaAnnua((Double.valueOf(((BigDecimal) result[41]).toString())));


				listStoricoContratti.add(contratto);

			}

			return listStoricoContratti;

		} catch (Exception e) {

			throw new RepositoryException(e);
		}

	}

}

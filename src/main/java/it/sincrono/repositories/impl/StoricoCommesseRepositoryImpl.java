package it.sincrono.repositories.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.sincrono.entities.Commessa;
import it.sincrono.entities.TipoAziendaCliente;
import it.sincrono.repositories.StoricoCommesseCustomRepository;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class StoricoCommesseRepositoryImpl extends BaseRepositoryImpl implements StoricoCommesseCustomRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Commessa> getStoricoCommesseByAnagrafica(Integer id) throws RepositoryException {
		List<Commessa> storicoCommesseList = new ArrayList<Commessa>();

		String queryString = SqlStrings.SQL_GET_STORICO_COMMESSE_BY_ID;
		queryString = queryString.replace("{0}", String.valueOf(id));

		Query query = entityManager.createNativeQuery(queryString);
		List<Object> queryList;
		queryList = query.getResultList();

		for (Iterator<Object> it = queryList.iterator(); it.hasNext();) {
			Object[] result = (Object[]) it.next();

			Commessa commessa = new Commessa();

			if (result[0] != null)
				commessa.setId((Integer) result[0]);
			TipoAziendaCliente tipoAziendaCliente = new TipoAziendaCliente();
			if (result[5] != null) {
				tipoAziendaCliente.setId((Integer) result[1]);
				tipoAziendaCliente.setDescrizione((String) result[12]);
			}
			commessa.setTipoAziendaCliente(tipoAziendaCliente);
			if (result[2] != null)
				commessa.setClienteFinale((String) result[2]);
			if (result[3] != null)
				commessa.setTitoloPosizione((String) result[3]);
			if (result[4] != null)
				commessa.setDistacco((Boolean) result[4]);
			if (result[5] != null)
				commessa.setDistaccoAzienda((String) result[5]);
			if (result[6] != null)
				commessa.setDistaccoData((Date) result[6]);
			if (result[7] != null)
				commessa.setDataInizio((Date) result[7]);
			if (result[8] != null)
				commessa.setDataFine((Date) result[8]);
			if (result[9] != null)
				commessa.setTariffaGiornaliera((String) result[9]);
			if (result[10] != null)
				commessa.setAziendaDiFatturazioneInterna((String) result[10]);
			if (result[11] != null)
				commessa.setAttivo((Boolean) result[11]);

			storicoCommesseList.add(commessa);

		}
		return storicoCommesseList;

	}
}

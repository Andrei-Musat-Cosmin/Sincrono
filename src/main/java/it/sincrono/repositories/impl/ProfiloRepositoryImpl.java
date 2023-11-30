package it.sincrono.repositories.impl;

import it.sincrono.repositories.ProfiloCustomRepository;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class ProfiloRepositoryImpl extends BaseRepositoryImpl implements ProfiloCustomRepository {

	@Override
	public Integer getidProfilo(Integer idAnagrafica) throws RepositoryException {

		String queryString = SqlStrings.SQL_GET_PROFILO;

		queryString = queryString.replace("{0}", new String().valueOf(idAnagrafica));

		Query query = entityManager.createNativeQuery(queryString);

		return (Integer) query.getSingleResult();
	}

}

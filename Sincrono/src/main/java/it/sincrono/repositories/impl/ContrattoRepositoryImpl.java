package it.sincrono.repositories.impl;

import java.util.List;

import it.sincrono.repositories.ContrattoCustomRepository;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class ContrattoRepositoryImpl extends BaseRepositoryImpl implements ContrattoCustomRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> search() throws RepositoryException {

		String queryString = SqlStrings.SQL_ORGANICO;

		Query query = entityManager.createNativeQuery(queryString);

		return query.getResultList();
	}

}

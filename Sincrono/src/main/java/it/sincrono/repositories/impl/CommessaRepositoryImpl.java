package it.sincrono.repositories.impl;

import java.util.List;

import it.sincrono.repositories.CommessaCustomRepository;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class CommessaRepositoryImpl extends BaseRepositoryImpl implements CommessaCustomRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> view() throws RepositoryException {

		String queryString = SqlStrings.SQL_DASHBOARD;

		Query query = entityManager.createNativeQuery(queryString);

		return query.getResultList();

	}
}
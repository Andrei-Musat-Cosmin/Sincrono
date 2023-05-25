package it.sincrono.repositories.impl;

import java.util.List;

import it.sincrono.entities.Funzione;
import it.sincrono.repositories.FunzioneCustomRepository;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class FunzioneRepositoryImpl extends BaseRepositoryImpl implements FunzioneCustomRepository {

	@Override
	public List<Funzione> funzioneTree() throws RepositoryException {

		return funzioneTree(null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Funzione> funzioneTree(Integer id) throws RepositoryException {

		List<Funzione> list;

		try {
			String queryString = SqlStrings.SQL_TREE_FUNZIONI;

			String subString;

			if (id == null) {
				subString = "AND a.funzione IS NULL";
			} else {
				subString = "AND a.funzione = " + id;
			}

			Query query = entityManager.createQuery(queryString.replace("{0}", subString));

			list = query.getResultList();

			if (list != null && !list.isEmpty()) {

				for (Funzione item : list) {

					item.setFunzione(id == null ? null : new Funzione(id));

					

					item.setFunzione(null);
					item.setFunzioni(funzioneTree(item.getId()));
				}

			} else {
				return null;
			}

		} catch (Exception e) {
			throw new RepositoryException(e);
		}

		return list;
	}

}

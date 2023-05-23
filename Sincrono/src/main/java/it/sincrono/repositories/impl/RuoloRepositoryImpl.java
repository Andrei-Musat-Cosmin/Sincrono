package it.sincrono.repositories.impl;

import java.util.List;

import it.sincrono.entities.Ruolo;
import it.sincrono.repositories.RuoloCustomRepository;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class RuoloRepositoryImpl extends BaseRepositoryImpl implements RuoloCustomRepository {

	@Override
	public List<Ruolo> tree() throws RepositoryException {
		return tree(null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Ruolo> tree(Integer ID) throws RepositoryException {

		List<Ruolo> list;

		try {

			String queryString = SqlStrings.SQL_TREE_RUOLI;

			String subString;

			if (ID == null) {
				subString = "AND a.ruolo IS NULL";
			} else {
				subString = "AND a.ruolo = " + ID;
			}

			queryString = queryString.replace("{0}", subString);

			Query query = entityManager.createQuery(queryString);

			list = query.getResultList();

			if (list != null && !list.isEmpty()) {
				for (Ruolo item : list) {
					item.setRuolo(null);
					item.setRuoli(tree(item.getId()));
				}
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new RepositoryException(e);
		}

		return list;
	}

	@Override
	public Integer getRuoloByUsername(String username) throws RepositoryException {

		Integer idRuolo = 0;
		String queryString = SqlStrings.SQL_GET_RUOLO_UTENTE;
		queryString = queryString.replace("{0}", username);

		Query query = entityManager.createNativeQuery(queryString);

		idRuolo = (Integer) query.getSingleResult();

		return idRuolo;

	}

	@Override
	public Ruolo getPadre(Integer id) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}
}

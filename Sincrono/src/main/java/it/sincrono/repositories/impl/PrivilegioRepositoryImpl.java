package it.sincrono.repositories.impl;

import java.util.Date;
import java.util.List;

import it.sincrono.entities.Funzione;
import it.sincrono.entities.Privilegio;
import it.sincrono.repositories.PrivilegioCustomRepository;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class PrivilegioRepositoryImpl extends BaseRepositoryImpl implements PrivilegioCustomRepository {

	

	
	@Override
	@SuppressWarnings("unchecked")
	public void insertPrivilegi(Privilegio privilegio) throws RepositoryException {
		
		
		try {
			
			Date data = new Date();
			
		
			
			Privilegio privilegioFunzionePadre = new Privilegio();
			
			privilegioFunzionePadre.setFunzione(privilegio.getFunzione().getFunzione());
			privilegioFunzionePadre.setRuolo(privilegio.getRuolo());
			privilegioFunzionePadre.setDataAggiornamento(data);
			
			
			
			Query query1 = entityManager.createQuery(SqlStrings.SQL_GET_PRIVILEGIO_ESISTENTE_PADRE);
			
			query1.setParameter("idpadre", privilegio.getFunzione().getFunzione().getId());
			query1.setParameter("idruolo", privilegio.getRuolo().getId());
			
			Boolean presente = (Boolean) query1.getSingleResult();
			
			if(!presente) {
				entityManager.persist(privilegioFunzionePadre);
			}
						
			privilegio.setDataAggiornamento(data);
			
			
			
			entityManager.persist(privilegio);

			Query query = entityManager.createQuery(SqlStrings.SQL_GET_FUNZIONI_FIGLIE);

			List<Funzione> list = query.getResultList();

			if(list != null && !list.isEmpty()) {
				for(Funzione item : list) {
					privilegio.setFunzione(item);
					insertPrivilegi(privilegio);
				}
			}

		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void deletePrivilegi(Privilegio privilegio) throws RepositoryException {

		try {
			entityManager.remove(privilegio);

			Query query = entityManager.createQuery(SqlStrings.SQL_GET_FUNZIONI_FIGLIE);

			List<Funzione> list = query.getResultList();

			if(list != null && !list.isEmpty()) {
				for(Funzione item : list) {
					privilegio.setFunzione(item);
					deletePrivilegi(privilegio);
				}
			}

		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public Privilegio getIdPrivilegio(Privilegio privilegio) throws RepositoryException {
			
		
		try {

			Query query = entityManager.createQuery(SqlStrings.SQL_GET_PRIVILEGIO);
			
			query.setParameter("id_ruolo", privilegio.getRuolo().getId());
			
			query.setParameter("id_funzione", privilegio.getFunzione().getId());
			
			privilegio = (Privilegio) query.getSingleResult();
			
			System.err.println(privilegio);
			
		} catch(Exception e) {
			throw new RepositoryException(e);
		}
		return privilegio;
		
		
	}

	@Override
	public void checkPrivilegioFunzionePadre(Integer idPadre) throws RepositoryException {
		
		try {
			
			Query query = entityManager.createQuery(SqlStrings.SQL_GET_FUNZIONI_FIGLIE);
			
			
			
			
		} catch(Exception e) {
			throw new RepositoryException(e);
		}
		
	}
}

package it.sincrono.repositories;

import it.sincrono.repositories.exceptions.RepositoryException;

public interface ProfiloCustomRepository extends BaseCustomRepository {

	public Integer getidProfilo(Integer idAnagrafica) throws RepositoryException;

}

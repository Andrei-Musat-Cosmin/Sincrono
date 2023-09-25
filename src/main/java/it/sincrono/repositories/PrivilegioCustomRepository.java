package it.sincrono.repositories;

import it.sincrono.entities.Privilegio;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface PrivilegioCustomRepository extends BaseCustomRepository {
	
	public void insertPrivilegi(Privilegio privilegio) throws RepositoryException;

	public void deletePrivilegi(Privilegio privilegio) throws RepositoryException;

	public Privilegio getIdPrivilegio(Privilegio privilegio) throws RepositoryException;

	public void checkPrivilegioFunzionePadre(Integer idPadre) throws RepositoryException;

}

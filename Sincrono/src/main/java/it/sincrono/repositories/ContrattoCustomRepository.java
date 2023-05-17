package it.sincrono.repositories;

import java.util.List;

import it.sincrono.repositories.exceptions.RepositoryException;

public interface ContrattoCustomRepository extends BaseCustomRepository {

	public List<Object> search() throws RepositoryException;

}
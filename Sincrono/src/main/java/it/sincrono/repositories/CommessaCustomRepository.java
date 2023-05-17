package it.sincrono.repositories;

import java.util.List;

import it.sincrono.repositories.exceptions.RepositoryException;

public interface CommessaCustomRepository extends BaseCustomRepository {

	public List<Object> view() throws RepositoryException;

}

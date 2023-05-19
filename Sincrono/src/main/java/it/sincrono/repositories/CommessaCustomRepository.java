package it.sincrono.repositories;

import java.util.List;

import it.sincrono.repositories.dto.CommessaDto;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface CommessaCustomRepository extends BaseCustomRepository {

	public List<CommessaDto> dashboard() throws RepositoryException;

}

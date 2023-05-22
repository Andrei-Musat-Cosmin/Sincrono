package it.sincrono.repositories;

import java.util.List;

import it.sincrono.repositories.dto.OrganicoDto;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface ContrattoCustomRepository extends BaseCustomRepository {

	public List<OrganicoDto> organico() throws RepositoryException;

}
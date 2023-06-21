package it.sincrono.repositories;

import java.util.List;

import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface AnagraficaCustomRepository extends BaseCustomRepository {

	public List<AnagraficaDto> listAnagraficaDto() throws RepositoryException;

	public AnagraficaDto getAnagraficaDto(Integer id) throws RepositoryException;
}
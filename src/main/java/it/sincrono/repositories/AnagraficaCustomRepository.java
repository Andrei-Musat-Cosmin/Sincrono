package it.sincrono.repositories;

import java.util.List;

import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import it.sincrono.requests.AnagraficaRequestDto;

public interface AnagraficaCustomRepository extends BaseCustomRepository {

	List<AnagraficaDto> filterListAnagraficaDto(AnagraficaRequestDto anagraficaRequestDto) throws RepositoryException;

	public List<AnagraficaDto> listAnagraficaDto() throws RepositoryException;

	public List<AnagraficaDto> listAnagraficaDtoContratti() throws RepositoryException;

	public AnagraficaDto getAnagraficaDto(Integer id) throws RepositoryException;

	public void deleteScattoContratti() throws RepositoryException;

	public AnagraficaDto getAnagraficaDtoByToken(String token) throws RepositoryException;

}
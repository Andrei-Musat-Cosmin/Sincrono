package it.sincrono.services;

import java.util.List;

import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.services.exceptions.ServiceException;

public interface AnagraficaService {

	// public List<Anagrafica> list() throws ServiceException;

	// public Anagrafica getById(Integer ID) throws ServiceException;

	// public void insert(Anagrafica anagrafica) throws ServiceException;

	// public void update(Anagrafica anagrafica) throws ServiceException;

	// public void delete(Integer ID) throws ServiceException;

	// public void insertAnagraficaDtoRelations(AnagraficaDto anagraficaDto) throws
	// ServiceException;

	public List<AnagraficaDto> anagraficaListContratti() throws ServiceException;

	public List<AnagraficaDto> filterListAnagraficaDto(AnagraficaRequestDto anagraficaRequestDto)
			throws ServiceException;

	public List<AnagraficaDto> listAnagraficaDto() throws ServiceException;

	public AnagraficaDto getAnagraficaDto(Integer id) throws ServiceException;

	public void insertAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException;

	public void updateAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException;

	public void deleteAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException;

	public void deleteScattoContratti() throws ServiceException;

	public AnagraficaDto getAnagraficaDtoByToken(String token) throws ServiceException;

	public void retainAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException;

}

package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Profilo;
import it.sincrono.repositories.dto.ProfiloDto;
import it.sincrono.services.exceptions.ServiceException;

public interface ProfiloService {
	public Profilo get(Integer ID) throws ServiceException;

	public List<ProfiloDto> getProfiloByAnagrafica(Integer id) throws ServiceException;

	public void insert(Profilo profili) throws ServiceException;

	public void update(Profilo profili) throws ServiceException;

	public void delete(Integer ID) throws ServiceException;
}

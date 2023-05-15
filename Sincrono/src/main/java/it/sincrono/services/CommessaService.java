package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Commessa;
import it.sincrono.services.exceptions.ServiceException;

public interface CommessaService {
	
	public List<Commessa> listCommessa() throws ServiceException;

	public Commessa getCommessaById(Long id) throws ServiceException;

	public void insert(Commessa Commessa) throws ServiceException;

	public void update(Commessa Commessa) throws ServiceException;

	public void delete(Long id) throws ServiceException;

}

package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Contratto;
import it.sincrono.services.exceptions.ServiceException;

public interface ContrattoSerivce {

	public List<Contratto> listContratto() throws ServiceException;

	public Contratto getContrattoById(Long id) throws ServiceException;

	public void insert(Contratto contratto) throws ServiceException;

	public void update(Contratto contratto) throws ServiceException;

	public void delete(Long id) throws ServiceException;

}

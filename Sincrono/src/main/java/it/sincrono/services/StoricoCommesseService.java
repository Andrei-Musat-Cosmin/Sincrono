package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Commessa;
import it.sincrono.entities.StoricoCommesse;
import it.sincrono.services.exceptions.ServiceException;

public interface StoricoCommesseService {

	public List<StoricoCommesse> listStoricoCommesse() throws ServiceException;

	public StoricoCommesse getStoricoCommesseById(Integer id) throws ServiceException;

	public void insert(StoricoCommesse storicoCommesse) throws ServiceException;

	public void update(StoricoCommesse storicoCommesse) throws ServiceException;

	public void delete(Integer id) throws ServiceException;
	
	public List<Commessa> getStoricoCommesseByAnagrafica(Integer id) throws ServiceException;

}

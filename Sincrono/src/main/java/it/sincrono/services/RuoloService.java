package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Ruolo;
import it.sincrono.services.exceptions.ServiceException;

public interface RuoloService {

	//public Ruolo get(Integer id) throws ServiceException;

	//public void insert(Ruolo ruolo) throws ServiceException;

	//public void update(Ruolo ruolo) throws ServiceException;

	//public void delete(Integer id) throws ServiceException;

	//public List<Ruolo> tree() throws ServiceException;

	public List<Ruolo> map() throws ServiceException;

	//public Ruolo getPadre(Integer id) throws ServiceException;

	//public Integer getRuoloByUsername(String username) throws ServiceException;

}

package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Utente;
import it.sincrono.services.exceptions.ServiceException;

public interface UtenteService {

	public List<Utente> list() throws ServiceException;

}

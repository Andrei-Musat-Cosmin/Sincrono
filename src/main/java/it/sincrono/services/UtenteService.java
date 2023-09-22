package it.sincrono.services;

import it.sincrono.requests.CambioPasswordRequest;
import it.sincrono.requests.ModificaPasswordRequest;
import it.sincrono.services.exceptions.ServiceException;

public interface UtenteService {

	// public List<Utente> list() throws ServiceException;

	// public Utente getById(Integer ID) throws ServiceException;

	public void updateUtente(ModificaPasswordRequest modificaUtenteRequest) throws ServiceException;

	public void updateUtente(CambioPasswordRequest cambioPasswordRequest) throws ServiceException;

}

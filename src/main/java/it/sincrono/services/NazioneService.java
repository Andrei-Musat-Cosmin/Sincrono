package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Nazione;
import it.sincrono.services.exceptions.ServiceException;

public interface NazioneService {
	public List<Nazione> getNazioniList() throws ServiceException;

}

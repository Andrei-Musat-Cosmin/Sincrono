package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Funzione;
import it.sincrono.services.exceptions.ServiceException;

public interface FunzioneService {

	public List<Funzione> funzioneTree(Integer id) throws ServiceException;

	public Integer getFunzioniDalRuolo(Integer id) throws ServiceException;

}

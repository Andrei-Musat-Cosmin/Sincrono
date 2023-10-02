package it.sincrono.services;


import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.services.exceptions.ServiceException;

public interface RapportinoService {


	public RapportinoDto getRapportino(String codiceFiscale) throws ServiceException;



}

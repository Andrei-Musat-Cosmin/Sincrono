package it.sincrono.services;

import it.sincrono.entities.RapportinoInviato;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.services.exceptions.ServiceException;

public interface RapportinoService {

	public RapportinoInviato findByData(RapportinoDto rapportinoDto) throws ServiceException;

	public RapportinoDto getRapportino(RapportinoRequestDto rapportinoRequestDto) throws ServiceException;

	public void updateRapportino(RapportinoRequestDto rapportinoRequestDto) throws ServiceException;

	public void insertRapportino(RapportinoInviato rapportinoInviato) throws ServiceException;

	public void updateFreeze(RapportinoInviato rapportinoInviato) throws ServiceException;

	public void delete(Integer id) throws ServiceException;

	public Boolean aggiungiNote(RapportinoRequestDto rapportinoRequestDto) throws ServiceException;

}

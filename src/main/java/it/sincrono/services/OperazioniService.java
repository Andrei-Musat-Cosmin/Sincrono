package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Operazione;
import it.sincrono.services.exceptions.ServiceException;

public interface OperazioniService {

	public List<Operazione> getOperazioniByFunzioni(Integer id) throws ServiceException;

}

package it.sincrono.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Utente;
import it.sincrono.repositories.UtenteRepository;
import it.sincrono.services.UtenteService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class UtenteServiceImpl extends BaseServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public List<Utente> list() throws ServiceException {

		List<Utente> list = null;

		try {
			list = utenteRepository.findAll();
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

}
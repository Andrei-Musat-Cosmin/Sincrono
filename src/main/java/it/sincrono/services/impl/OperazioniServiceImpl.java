package it.sincrono.services.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Operazione;
import it.sincrono.repositories.OperazioniRepository;
import it.sincrono.services.OperazioniService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class OperazioniServiceImpl extends BaseServiceImpl implements OperazioniService {
	private static final Logger LOGGER = LogManager.getLogger(OperazioniServiceImpl.class);

	@Autowired
	OperazioniRepository operazioniRepository;

	@Override
	public List<Operazione> getOperazioniByFunzioni(Integer id) throws ServiceException {

		List<Operazione> list = null;

		try {
			list = operazioniRepository.getOperazioniByFunzioni(id);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

}

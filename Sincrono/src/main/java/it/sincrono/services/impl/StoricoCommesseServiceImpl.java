package it.sincrono.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.sincrono.entities.StoricoCommesse;
import it.sincrono.repositories.StoricoCommesseRepository;
import it.sincrono.services.StoricoCommesseService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class StoricoCommesseServiceImpl implements StoricoCommesseService {

	@Autowired
	public StoricoCommesseRepository StoricoCommesseRepository;

	@Override
	public List<StoricoCommesse> listStoricoCommesse() throws ServiceException {

		List<StoricoCommesse> storicoCommesse = null;

		storicoCommesse = StoricoCommesseRepository.findAll();

		return storicoCommesse;
	}

	@Override
	public StoricoCommesse getStoricoCommesseById(Integer id) throws ServiceException {
		Optional<StoricoCommesse> storicoCommesse = StoricoCommesseRepository.findById(id);
		if (storicoCommesse.isPresent()) {
			return storicoCommesse.get();
		}
		return null;
	}

	@Override
	public void insert(StoricoCommesse storicoCommesse) throws ServiceException {
		StoricoCommesseRepository.saveAndFlush(storicoCommesse);

	}

	@Override
	public void update(StoricoCommesse storicoCommesse) throws ServiceException {
		try {

			StoricoCommesse currentStoricoCommesse = StoricoCommesseRepository.findById(storicoCommesse.getId()).get();
			currentStoricoCommesse.setId(storicoCommesse.getId());
			StoricoCommesseRepository.saveAndFlush(storicoCommesse);

		} catch (NoSuchElementException ne) {
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

	@Override
	public void delete(Integer id) throws ServiceException {

		try {
			StoricoCommesse storicoCommesse = StoricoCommesseRepository.findById(id).get();
			StoricoCommesseRepository.delete(storicoCommesse);
			StoricoCommesseRepository.flush();

		} catch (NoSuchElementException ne) {
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

}

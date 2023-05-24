package it.sincrono.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Profilo;
import it.sincrono.repositories.ProfiloRepository;
import it.sincrono.repositories.dto.ProfiloDto;
import it.sincrono.services.ProfiloService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.validator.ProfiloValidator;

@Service
public class ProfiloServiceImpl implements ProfiloService {

	@Autowired
	private ProfiloRepository profiloRepository;

	@Autowired
	private ProfiloValidator profiloValidator;

	@Override
	public Profilo get(Integer id) throws ServiceException {

		Profilo profilo = null;

		try {
			profilo = profiloRepository.findById(id).get();
		} catch (NoSuchElementException ne) {

			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (Exception e) {

			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return profilo;
	}

	@Override
	public List<ProfiloDto> getProfiloByAnagrafica(Integer id) throws ServiceException {

		List<ProfiloDto> utenteRuoli = null;

		try {
			utenteRuoli = profiloRepository.getProfiloByAnagrafica(id);
		} catch (Exception e) {

			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return utenteRuoli;
	}

	@Override

	public void insert(Profilo profilo) throws ServiceException {

		if (!profiloValidator.validate(profilo)) {

			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}

		try {
			profiloRepository.saveAndFlush(profilo);
		} catch (DataIntegrityViolationException de) {

			throw new ServiceException(ServiceMessages.RECORD_ESISTENTE);
		} catch (Exception e) {

			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override

	public void update(Profilo profilo) throws ServiceException {

		if (!profiloValidator.validate(profilo)) {

			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}

		try {
			Profilo currentProfilo = profiloRepository.findById(profilo.getId()).get();

			currentProfilo.setId(profilo.getId());
			currentProfilo.setRuolo(profilo.getRuolo());
			currentProfilo.setUtente(profilo.getUtente());
			currentProfilo.setDataInizio(profilo.getDataInizio());
			currentProfilo.setDataFine(profilo.getDataFine());
			currentProfilo.setUtenteAggiornamento(profilo.getUtenteAggiornamento());

			profiloRepository.saveAndFlush(currentProfilo);

		} catch (NoSuchElementException ne) {

			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {

			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {

			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

	@Override

	public void delete(Integer ID) throws ServiceException {

		try {
			Profilo profilo = profiloRepository.findById(ID).get();

			profiloRepository.delete(profilo);
			profiloRepository.flush();

		} catch (NoSuchElementException ne) {

			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {

			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {

			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

}

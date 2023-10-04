package it.sincrono.services.impl;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Utente;
import it.sincrono.repositories.UtenteRepository;
import it.sincrono.requests.CambioPasswordRequest;
import it.sincrono.requests.ModificaPasswordRequest;
import it.sincrono.services.UtenteService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class UtenteServiceImpl extends BaseServiceImpl implements UtenteService {
	private static final Logger LOGGER = LogManager.getLogger(UtenteServiceImpl.class);

	@Autowired
	private UtenteRepository utenteRepository;

//	@Override
//	public List<Utente> list() throws ServiceException {
//
//		List<Utente> list = null;
//
//		try {
//			list = utenteRepository.findAll();
//		} catch (Exception e) {
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//
//		return list;
//	}
//
//	@Override
//	public Utente getById(Integer ID) throws ServiceException {
//		Utente utente = null;
//
//		try {
//			utente = utenteRepository.findById(ID).get();
//		} catch (NoSuchElementException ne) {
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (Exception e) {
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//
//		return utente;
//	}

	@Override
	public void updateUtente(ModificaPasswordRequest modificaPasswordRequest) throws ServiceException {

		try {
			Utente currentUtente = utenteRepository.findById(modificaPasswordRequest.getId()).get();
			if (BCrypt.checkpw(modificaPasswordRequest.getPasswordVecchia(), currentUtente.getPassword())) {
				currentUtente.setPassword(BCrypt.hashpw(modificaPasswordRequest.getPasswordNuova(), BCrypt.gensalt()));
				currentUtente.setTokenPassword(null);
			} else {
				LOGGER.log(Level.ERROR, ServiceMessages.PASSWORD_INSERITA_UGUALE_ALLA_VECCHIA);
				throw new ServiceException(ServiceMessages.PASSWORD_INSERITA_UGUALE_ALLA_VECCHIA);
			}
			utenteRepository.saveAndFlush(currentUtente);

		} catch (NoSuchElementException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override
	public void updateUtente(CambioPasswordRequest cambioPasswordRequest) throws ServiceException {

		try {
			Utente currentUtente = utenteRepository.findByTokenPassword(cambioPasswordRequest.getToken()).get();
			currentUtente.setPassword(BCrypt.hashpw(cambioPasswordRequest.getPassword(), BCrypt.gensalt()));
			currentUtente.setTokenPassword(null);

			utenteRepository.saveAndFlush(currentUtente);

		} catch (NoSuchElementException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

}
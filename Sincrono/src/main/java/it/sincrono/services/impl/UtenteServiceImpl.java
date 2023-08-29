package it.sincrono.services.impl;

import java.util.NoSuchElementException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Utente;
import it.sincrono.repositories.ProfiloRepository;
import it.sincrono.repositories.UtenteRepository;
import it.sincrono.requests.CambioPasswordRequest;
import it.sincrono.requests.ModificaPasswordRequest;
import it.sincrono.services.UtenteService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.validator.UtenteValidator;

@Service
public class UtenteServiceImpl extends BaseServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private ProfiloRepository profiloRepository;

	@Autowired
	private UtenteValidator utenteValidator;

//	@Override
//	public List<Utente> list() throws ServiceException {
//
//		List<Utente> list = null;
//
//		try {
//			list = utenteRepository.findAll();
//		} catch (Exception e) {
//			System.out.println("Exception occurs {}");
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
//			System.out.println("Exception occurs {}, ID {}");
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (Exception e) {
//			System.out.println("Exception occurs {}");
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
				throw new ServiceException(ServiceMessages.PASSWORD_INSERITA_UGUALE_ALLA_VECCHIA);
			}
			utenteRepository.saveAndFlush(currentUtente);

		} catch (NoSuchElementException ne) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
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

		} catch (NoSuchElementException ne) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

}
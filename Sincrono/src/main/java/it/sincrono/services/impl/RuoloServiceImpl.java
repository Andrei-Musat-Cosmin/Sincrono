package it.sincrono.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Ruolo;
import it.sincrono.repositories.RuoloRepository;
import it.sincrono.services.RuoloService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.validator.RuoloValidator;

@Service
public class RuoloServiceImpl extends BaseServiceImpl implements RuoloService {

	@Autowired
	private RuoloRepository ruoloRepository;

	@Autowired
	private RuoloValidator ruoloValidator;

	@Override
	public List<Ruolo> map() throws ServiceException {

		List<Ruolo> map;

		try {
			map = ruoloRepository.map();
		} catch (Exception e) {

			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return map;
	}

//	@Override
//	public Ruolo get(Integer id) throws ServiceException {
//
//		Ruolo ruolo;
//
//		try {
//			ruolo = ruoloRepository.findById(id).get();
//		} catch (NoSuchElementException ne) {
//
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (Exception e) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//
//		return ruolo;
//	}

//	@Override
//
//	public void insert(Ruolo ruolo) throws ServiceException {
//
//		if (!ruoloValidator.validate(ruolo, true)) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
//		}
//
//		try {
//			ruoloRepository.saveAndFlush(ruolo);
//		} catch (DataIntegrityViolationException de) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch (Exception e) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//	}
//
//	@Override
//
//	public void update(Ruolo ruolo) throws ServiceException {
//
//		if (!ruoloValidator.validate(ruolo, false)) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
//		}
//
//		try {
//			Ruolo currentRuolo = ruoloRepository.findById(ruolo.getId()).get();
//
//			currentRuolo.setId(ruolo.getId());
//			currentRuolo.setRuolo(ruolo.getRuolo());
//			currentRuolo.setNome(ruolo.getNome());
//			currentRuolo.setDescrizione(ruolo.getDescrizione());
//
//			ruoloRepository.saveAndFlush(currentRuolo);
//
//		} catch (NoSuchElementException ne) {
//
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (DataIntegrityViolationException de) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch (Exception e) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//	}
//
//	@Override
//
//	public void delete(Integer id) throws ServiceException {
//
//		try {
//			Ruolo ruolo = ruoloRepository.findById(id).get();
//
//			if (!ruoloValidator.validate(ruolo, false))
//				throw new ServiceException(ServiceMessages.ERRORE_FIGLI);
//
//			ruoloRepository.delete(ruolo);
//			ruoloRepository.flush();
//
//		} catch (NoSuchElementException ne) {
//
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (DataIntegrityViolationException de) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch (Exception e) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//	}
//
//	@Override
//	public List<Ruolo> tree() throws ServiceException {
//
//		List<Ruolo> tree;
//
//		try {
//			tree = ruoloRepository.tree();
//		} catch (Exception e) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//
//		return tree;
//	}

//	@Override
//	public Ruolo getPadre(Integer id) throws ServiceException {
//		// TODO Auto-generated method stub
//		Ruolo list;
//		try {
//			list = ruoloRepository.getPadre(id);
//			return list;
//
//		} catch (RepositoryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//
//	}

//	@Override
//	public Integer getRuoloByUsername(String username) throws ServiceException {
//
//		Integer idRuolo = null;
//
//		try {
//			idRuolo = ruoloRepository.getRuoloByUsername(username);
//		} catch (NoSuchElementException ne) {
//
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (Exception e) {
//
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//
//		return idRuolo;
//	}

}

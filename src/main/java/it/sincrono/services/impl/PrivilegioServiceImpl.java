package it.sincrono.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.repositories.PrivilegioRepository;
import it.sincrono.services.PrivilegioService;
import it.sincrono.services.validator.PrivilegioValidator;

/**
 * POSSIBILE RIMOZIONE
 *
 */

@Service
public class PrivilegioServiceImpl extends BaseServiceImpl implements PrivilegioService {

	@Autowired
	private PrivilegioRepository privilegioRepository;

	@Autowired
	private PrivilegioValidator privilegioValidator;

//	@Override
//	@Transactional(rollbackOn = ServiceException.class)
//	public void insert(Privilegio privilegio) throws ServiceException {
//
//		if(!privilegioValidator.validate(privilegio)) {
//			
//			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
//		}
//
//		try {
//			System.err.println(privilegio);
//			
//			privilegioRepository.insertPrivilegi(privilegio);
//			
//			System.err.println(privilegio);
//			
//			privilegioRepository.flush();
//		} catch(DataIntegrityViolationException de) {
//			
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch(Exception e) {
//		
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//	}

//	@Override
//	@Transactional(rollbackOn = ServiceException.class)
//	public void delete(Integer id) throws ServiceException {
//
//		try {
//			Privilegio privilegio = privilegioRepository.findById(id).get();
//
//			privilegioRepository.deletePrivilegi(privilegio);
//			privilegioRepository.flush();
//
//		} catch(NoSuchElementException ne) {
//			
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch(DataIntegrityViolationException de) {
//			
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch(Exception e) {
//			
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//	}
//
//	@Override
//	public Privilegio getIdPrivilegio(Privilegio privilegio) throws ServiceException {
//		
//		try {
//			 
//			privilegio = privilegioRepository.getIdPrivilegio(privilegio);
//
//
//		} catch(NoSuchElementException ne) {
//			
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch(Exception e) {
//			
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//		
//		return privilegio;
//		
//	}

//	@Override
//	public void checkPrivilegioFunzionePadre(Integer idPadre) throws ServiceException {
//		// TODO Auto-generated method stub
//		
//	}
}

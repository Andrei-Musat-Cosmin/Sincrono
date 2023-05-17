package it.sincrono.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import it.sincrono.entities.StoricoContratti;
import it.sincrono.repositories.StoricoContrattiRepository;
import it.sincrono.services.StoricoContrattiService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.validator.StoricoContrattiValidator;


public class StoricoContrattiServiceImpl implements StoricoContrattiService{
	
	@Autowired
	public StoricoContrattiRepository storicoContrattiRepository;
	
	@Autowired
	private StoricoContrattiValidator storicoContrattiValidator;


	@Override
	public List<StoricoContratti> listStoricoContratti() throws ServiceException {
		
		List<StoricoContratti> list = null;

		try {
			list = storicoContrattiRepository.findAll();
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}
	

	@Override
	public StoricoContratti getStoricoContrattiById(Integer ID) throws ServiceException {
		
		StoricoContratti storicoContratti = null;

		try {
			storicoContratti = storicoContrattiRepository.findById(ID).get();
		} catch (NoSuchElementException ne) {
		System.out.println("Exception occurs {}, ID {}");
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return storicoContratti;
	}

	@Override
	public void insert(StoricoContratti storicoContratti) throws ServiceException {
		
		if(!storicoContrattiValidator.validate(storicoContratti,true)) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}

		try {
			storicoContrattiRepository.saveAndFlush(storicoContratti);
		} catch(DataIntegrityViolationException de) {
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch(Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
		
	}

	

	@Override
	public void update(StoricoContratti StoricoContratti) throws ServiceException {
		try {

			StoricoContratti currentStoricoContratti = storicoContrattiRepository.findById(StoricoContratti.getId()).get();
			currentStoricoContratti.setId(StoricoContratti.getId());
			storicoContrattiRepository.saveAndFlush(StoricoContratti);

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
			StoricoContratti storicoContratti = storicoContrattiRepository.findById(id).get();
			storicoContrattiRepository.delete(storicoContratti);
			storicoContrattiRepository.flush();

		} catch (NoSuchElementException ne) {
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

}

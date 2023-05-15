package it.sincrono.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Commessa;
import it.sincrono.repositories.CommessaRepository;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class CommessaServiceImpl {
	@Autowired
	private CommessaRepository CommessaRepository;

	@Override
	public List<Commessa> listCommessa() throws ServiceException {
		List<Commessa> Commessa = CommessaRepository.findAll();
		return Commessa;
	}

	@Override
	public Commessa getCommessaById(Long id) throws ServiceException {
		Optional<Commessa> Commessa = CommessaRepository.findById(id);
		if (Commessa.isPresent()) {
			return Commessa.get();
		}
		return null;
	}

	@Override
	public void insert(Commessa Commessa) {
		CommessaRepository.saveAndFlush(Commessa);
	}

	@Override
	public void update(Commessa Commessa) throws ServiceException {
		try {

			Commessa currentCommessa = CommessaRepository.findById(Commessa.getId()).get();

			currentCommessa.setId(Commessa.getId());
			currentCommessa.setNome(Commessa.getNome());

			CommessaRepository.saveAndFlush(currentCommessa);

		} catch (NoSuchElementException ne) {
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

	public void delete(Long id) throws ServiceException {

		try {
			Commessa Commessa = CommessaRepository.findById(id).get();

			CommessaRepository.delete(Commessa);
			CommessaRepository.flush();

		} catch (NoSuchElementException ne) {
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

}

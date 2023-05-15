package it.sincrono.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import it.sincrono.entities.Contratto;
import it.sincrono.repositories.ContrattoRepository;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@
public class ContrattoServiceImpl implements ContrattoService {

	@Autowired
	private ContrattoRepository contrattoRepository;

	@Override
	public List<Contratto> listContratto() throws ServiceException {
		List<Contratto> contratto = contrattoRepository.findAll();
		return contratto;
	}

	@Override
	public Contratto getContrattoById(Long id) {
		Optional<Contratto> contratto = contrattoRepository.findById(id);
		if (contratto.isPresent()) {
			return contratto.get();
		}
		return null;
	}

	@Override
	public void insert(Contratto contratto) {
		contrattoRepository.saveAndFlush(contratto);
	}

	@Override
	public void update(Contratto contratto) throws ServiceException {
		try {

			Contratto currentContratto = contrattoRepository.findById(contratto.getId()).get();

			currentContratto.setId(contratto.getId());
			currentContratto.setNome(contratto.getNome());

			contrattoRepository.saveAndFlush(currentContratto);

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
			Contratto contratto = contrattoRepository.findById(id).get();

			contrattoRepository.delete(contratto);
			contrattoRepository.flush();

		} catch (NoSuchElementException ne) {
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

}
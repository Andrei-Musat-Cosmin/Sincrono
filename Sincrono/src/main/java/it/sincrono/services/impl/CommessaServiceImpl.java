package it.sincrono.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Commessa;
import it.sincrono.repositories.CommessaRepository;
import it.sincrono.services.CommessaService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class CommessaServiceImpl implements CommessaService {
	

		@Autowired
		private CommessaRepository CommessaRepository;
		
		@Override
		public List<Commessa> listCommessa() throws ServiceException {
			List<Commessa> commessa = CommessaRepository.findAll();
			return commessa;
		}

		@Override
		public Commessa getCommessaById(Integer id) throws ServiceException {
			Optional<Commessa> commessa = CommessaRepository.findById(id);
			if (commessa.isPresent()) {
				return commessa.get();
			}
			return null;
		}

		@Override
		public void insert(Commessa commessa) {
			CommessaRepository.saveAndFlush(commessa);
		}

		@Override
		public void update(Commessa commessa) throws ServiceException {
			try {

				Commessa currentCommessa = CommessaRepository.findById(commessa.getId()).get();

				currentCommessa.setId(commessa.getId());

				CommessaRepository.saveAndFlush(currentCommessa);

			} catch (NoSuchElementException ne) {
				throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
			} catch (DataIntegrityViolationException de) {
				throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
			} catch (Exception e) {
				throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
			}
		}

		public void delete(Integer id) throws ServiceException {

			try {
				Commessa commessa = CommessaRepository.findById(id).get();

				CommessaRepository.delete(commessa);
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

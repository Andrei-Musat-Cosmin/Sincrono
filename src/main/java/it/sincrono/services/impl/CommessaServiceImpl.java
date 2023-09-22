package it.sincrono.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Commessa;
import it.sincrono.repositories.CommessaRepository;
import it.sincrono.repositories.dto.CommessaDto;
import it.sincrono.services.CommessaService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class CommessaServiceImpl implements CommessaService {

	@Autowired
	private CommessaRepository commessaRepository;

//	@Override
//	public List<Commessa> listCommessa() throws ServiceException {
//		List<Commessa> commessa = commessaRepository.findAll();
//		return commessa;
//	}
//
//	@Override
//	public Commessa getCommessaById(Integer id) throws ServiceException {
//		Optional<Commessa> commessa = commessaRepository.findById(id);
//		if (commessa.isPresent()) {
//			return commessa.get();
//		}
//		return null;
//	}
//
//	@Override
//	public void insert(Commessa commessa) {
//		commessaRepository.saveAndFlush(commessa);
//	}

	@Override
	public void update(Commessa commessa) throws ServiceException {

		try {

			Commessa currentCommessa = commessaRepository.findById(commessa.getId()).get();

			currentCommessa.setAttivo(false);

			currentCommessa.setId(commessa.getId());

			commessaRepository.saveAndFlush(currentCommessa);

		} catch (NoSuchElementException ne) {
			System.out.println("Exception occurs { RECORD_NON_TROVATO }\n");
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			System.out.println("Exception occurs { ERRORE_INTEGRITA_DATI }\n");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			System.out.println("Exception occurs { ERRORE_GENERICO }\n");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

	public void delete(Integer id) throws ServiceException {

		try {
			Commessa commessa = commessaRepository.findById(id).get();

			commessaRepository.delete(commessa);
			commessaRepository.flush();

		} catch (NoSuchElementException ne) {
			System.out.println("Exception occurs { RECORD_NON_TROVATO }\n");
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			System.out.println("Exception occurs { ERRORE_INTEGRITA_DATI }\n");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			System.out.println("Exception occurs { ERRORE_GENERICO }\n");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

	@Override
	public List<CommessaDto> dashboard() throws ServiceException {
		List<CommessaDto> dashboard;
		try {
			dashboard = commessaRepository.dashboard();
		} catch (Exception e) {
			System.out.println("Exception occurs { ERRORE_GENERICO }\n");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
		return dashboard;
	}

	@Override
	public void retainCommessa(Integer id) throws ServiceException {

		try {
			Commessa commessa = commessaRepository.getById(id);
			commessa.setAttivo(true);
			// commessa.setAttesaLavori(false);
			commessaRepository.saveAndFlush(commessa);

		} catch (Exception e) {
			System.out.println("Exception occurs { ERRORE_GENERICO }\n");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}
}

package it.sincrono.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Nazione;
import it.sincrono.repositories.NazioneRepository;
import it.sincrono.services.NazioneService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class NazioneServiceImpl extends BaseServiceImpl implements NazioneService {
	
	@Autowired
	private NazioneRepository nazioneRepository;
	
	@Override
	public List<Nazione> getNazioniList() throws ServiceException {
		List<Nazione> list = null;
		try {
			list = nazioneRepository.getNazioniList();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;

	}
}

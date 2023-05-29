package it.sincrono.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Funzione;
import it.sincrono.repositories.FunzioneRepository;
import it.sincrono.services.FunzioneService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class FunzioneServiceImpl extends BaseServiceImpl implements FunzioneService {
	@Autowired
	FunzioneRepository funzioneRepository;

	@Override
	public List<Funzione> funzioneTree(Integer id) throws ServiceException {

		List<Funzione> tree;

		try {
			tree = funzioneRepository.funzioneTree();
		} catch (Exception e) {

			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return tree;
	}

}

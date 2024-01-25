package it.sincrono.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Comune;
import it.sincrono.repositories.ComuneRepository;
import it.sincrono.services.ComuneService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
@Service
public class ComuneServiceImpl extends BaseServiceImpl implements ComuneService {

	@Autowired
	private ComuneRepository comuneRepository;
	
	@Override
	public List<Comune> getComuniMap() throws ServiceException {
		List<Comune> list = null;
		try {
			list = comuneRepository.getComuniMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;

	}

	
}

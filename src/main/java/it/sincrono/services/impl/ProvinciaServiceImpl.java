package it.sincrono.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Provincia;
import it.sincrono.repositories.ProvinciaRepository;
import it.sincrono.services.ProvinciaService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class ProvinciaServiceImpl extends BaseServiceImpl implements ProvinciaService {

	@Autowired
	private ProvinciaRepository provinciaRepository;
		
	@Override
	public List<Provincia> getProvinceMap() throws ServiceException {
		List<Provincia> list = null;
		try {
			list = provinciaRepository.getProvinceMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;

	}

}

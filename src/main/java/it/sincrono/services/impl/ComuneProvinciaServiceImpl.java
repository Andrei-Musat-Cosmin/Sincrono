package it.sincrono.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.sincrono.entities.Comune;
import it.sincrono.entities.Provincia;
import it.sincrono.repositories.ComuneProvinciaRepository;
import it.sincrono.services.ComuneProvinciaService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

public class ComuneProvinciaServiceImpl extends BaseServiceImpl implements ComuneProvinciaService {

	@Autowired
	private ComuneProvinciaRepository comuneProvinciaRepository;
	
	@Override
	public List<Comune> getComuniMap() throws ServiceException {
		List<Comune> list = null;
		try {
			list = comuneProvinciaRepository.getComuniMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;

	}

	@Override
	public List<Provincia> getProvinceMap() throws ServiceException {
		List<Provincia> list = null;
		try {
			list = comuneProvinciaRepository.getProvinceMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;

	}
}

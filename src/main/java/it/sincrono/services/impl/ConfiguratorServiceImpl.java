package it.sincrono.services.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Configurator;
import it.sincrono.repositories.ConfiguratorRepository;
import it.sincrono.services.ConfiguratorService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class ConfiguratorServiceImpl extends BaseServiceImpl implements ConfiguratorService {

	private static final Logger LOGGER = LogManager.getLogger(ConfiguratorServiceImpl.class);

	@Autowired
	private ConfiguratorRepository configuratorRepository;

	@Override
	public List<Configurator> listConfigurator() throws ServiceException {

		List<Configurator> list = null;

		try {

			list = configuratorRepository.listConfigurator();

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

}

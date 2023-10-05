package it.sincrono.controllers;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Configurator;
import it.sincrono.responses.ConfiguratorListResponse;
import it.sincrono.services.ConfiguratorService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class ConfiguratorController {
	private static final Logger LOGGER = LogManager.getLogger(ConfiguratorController.class);

	@Autowired
	private ConfiguratorService configuratorService;

	@GetMapping("/get-list-configurator")
	public @ResponseBody HttpEntity<ConfiguratorListResponse> listConfigurator() {

		HttpEntity<ConfiguratorListResponse> httpEntity = null;

		ConfiguratorListResponse configuratorListResponse = new ConfiguratorListResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo listConfigurator");

			List<Configurator> configurators = configuratorService.listConfigurator();

			configuratorListResponse.setList(configurators);
			configuratorListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<ConfiguratorListResponse>(configuratorListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			configuratorListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<ConfiguratorListResponse>(configuratorListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo listConfigurator\n");

		return httpEntity;
	}

}

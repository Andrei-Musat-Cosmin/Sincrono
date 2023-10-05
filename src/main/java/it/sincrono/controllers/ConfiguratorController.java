package it.sincrono.controllers;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Configurator;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.responses.AnagraficaDtoListResponse;
import it.sincrono.responses.AnagraficaDtoResponse;
import it.sincrono.responses.ConfiguratorListResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.services.AnagraficaService;
import it.sincrono.services.ConfiguratorService;
import it.sincrono.services.UtenteService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class ConfiguratorController {
	private static final Logger LOGGER = LogManager.getLogger(ConfiguratorController.class);

	@Autowired
	private ConfiguratorService configuratorService;


	@GetMapping("/get-list-configurator")
	public @ResponseBody HttpEntity<ConfiguratorListResponse> listAnagraficaDto() {

		HttpEntity<ConfiguratorListResponse> httpEntity = null;

		ConfiguratorListResponse configuratorListResponse = new ConfiguratorListResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo listAnagraficaDto");

			List<Configurator> configurators = configuratorService.listConfigurator();

			configuratorListResponse.setList(configurators);
			configuratorListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<ConfiguratorListResponse>(configuratorListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			configuratorListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<ConfiguratorListResponse>(configuratorListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo listAnagraficaDto\n");

		return httpEntity;
	}



}

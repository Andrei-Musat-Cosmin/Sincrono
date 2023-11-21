package it.sincrono.controllers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.RichiestaDto;
import it.sincrono.requests.RichiestaRequest;
import it.sincrono.responses.RichiestaResponse;
import it.sincrono.services.RichiestaService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class RichiestaController {

	private static final Logger LOGGER = LogManager.getLogger(RichiestaController.class);

	@Autowired
	RichiestaService richiestaService;

	@PostMapping("/get-richiesta")
	public @ResponseBody HttpEntity<RichiestaResponse> getRichiesta(@RequestBody RichiestaRequest richiestaRequest) {

		HttpEntity<RichiestaResponse> httpEntity = null;

		RichiestaResponse richiestaResponse = new RichiestaResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getRichiesta");

			RichiestaDto richiestaDto = richiestaService.getRichiesta(richiestaRequest);

			richiestaResponse.setRichiestaDto(richiestaDto);
			richiestaResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<RichiestaResponse>(richiestaResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			richiestaResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<RichiestaResponse>(richiestaResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getRapportino\n");

		return httpEntity;
	}
}

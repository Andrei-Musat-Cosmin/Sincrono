package it.sincrono.controllers;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Operazione;
import it.sincrono.responses.OperazioniListResponse;
import it.sincrono.services.OperazioniService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class OperazioniController {
	private static final Logger LOGGER = LogManager.getLogger(OperazioniController.class);

	@Autowired
	private OperazioniService operazioniService;

	@GetMapping("/operazioni/{id}")
	public @ResponseBody HttpEntity<OperazioniListResponse> getOperazioniById(@PathVariable("id") Integer ID) {

		HttpEntity<OperazioniListResponse> httpEntity = null;

		OperazioniListResponse operazioniListResponse = new OperazioniListResponse();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al metodo getOperazioniById");

			List<Operazione> list = operazioniService.getOperazioniByFunzioni(ID);

			operazioniListResponse.setList(list);

			operazioniListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<OperazioniListResponse>(operazioniListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			operazioniListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<OperazioniListResponse>(operazioniListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al metodo getOperazioniById\n");

		return httpEntity;
	}

}

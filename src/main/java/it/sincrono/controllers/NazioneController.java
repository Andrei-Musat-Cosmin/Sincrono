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
import it.sincrono.entities.Nazione;
import it.sincrono.responses.NazioneListResponse;
import it.sincrono.services.NazioneService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class NazioneController {

	private static final Logger LOGGER = LogManager.getLogger(NazioneController.class);

	@Autowired
	private NazioneService nazioneService;

	@GetMapping("/nazioni-list")
	public @ResponseBody HttpEntity<NazioneListResponse> getNazioniList() {

		HttpEntity<NazioneListResponse> httpEntity = null;

		NazioneListResponse nazioneListResponse = new NazioneListResponse();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getNazioniList");

			List<Nazione> list = nazioneService.getNazioniList();

			nazioneListResponse.setList(list);
			nazioneListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<NazioneListResponse>(nazioneListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			nazioneListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<NazioneListResponse>(nazioneListResponse);

		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getNazioniList\n");

		return httpEntity;

	}
}

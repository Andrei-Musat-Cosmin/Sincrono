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
import it.sincrono.entities.Comune;
import it.sincrono.entities.Provincia;
import it.sincrono.responses.TipologicheListResponse;
import it.sincrono.services.ComuneProvinciaService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class ComuneProvinciaController {
	private static final Logger LOGGER = LogManager.getLogger(TipologicheController.class);
	
	@Autowired
	private ComuneProvinciaService comuneProvinciaService;

	@GetMapping("/comuni-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<Comune>> getComuniMap() {

		HttpEntity<TipologicheListResponse<Comune>> httpEntity = null;

		TipologicheListResponse<Comune> comuniListResponse = new TipologicheListResponse<Comune>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getComuniMap");

			List<Comune> list = comuneProvinciaService.getComuniMap();

			comuniListResponse.setList(list);
			comuniListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<Comune>>(comuniListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			comuniListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<Comune>>(comuniListResponse);

		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getComuniMap\n");

		return httpEntity;

	}

	@GetMapping("/province-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<Provincia>> getProvinceMap() {

		HttpEntity<TipologicheListResponse<Provincia>> httpEntity = null;

		TipologicheListResponse<Provincia> comuniListResponse = new TipologicheListResponse<Provincia>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getComuniMap");

			List<Provincia> list = comuneProvinciaService.getProvinceMap();

			comuniListResponse.setList(list);
			comuniListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<Provincia>>(comuniListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			comuniListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<Provincia>>(comuniListResponse);

		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getComuniMap\n");

		return httpEntity;

	}
}

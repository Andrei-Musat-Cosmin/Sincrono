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
import it.sincrono.entities.Comune;
import it.sincrono.entities.Provincia;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoAziendaCliente;
import it.sincrono.entities.TipoCanaleReclutamento;
import it.sincrono.entities.TipoCausaFineContratto;
import it.sincrono.entities.TipoCausaFineRapporto;
import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.TipoLivelloContratto;
import it.sincrono.responses.TipologicheListResponse;
import it.sincrono.services.TipologicheService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class TipologicheController {
	private static final Logger LOGGER = LogManager.getLogger(TipologicheController.class);

	@Autowired
	private TipologicheService tipologicheService;

	@GetMapping("/tipo-azienda-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoAzienda>> getAziendeMap() {

		HttpEntity<TipologicheListResponse<TipoAzienda>> httpEntity = null;

		TipologicheListResponse<TipoAzienda> aziendeListResponse = new TipologicheListResponse<TipoAzienda>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getAziendeMap");

			List<TipoAzienda> list = tipologicheService.getTipoAziendaMap();

			aziendeListResponse.setList(list);
			aziendeListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoAzienda>>(aziendeListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			aziendeListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoAzienda>>(aziendeListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getAziendeMap\n");

		return httpEntity;
	}

	@GetMapping("/tipo-contratto-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoContratto>> getTipoContrattoMap() {

		HttpEntity<TipologicheListResponse<TipoContratto>> httpEntity = null;

		TipologicheListResponse<TipoContratto> tipoCotnrattoListResponse = new TipologicheListResponse<TipoContratto>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getTipoContrattoMap");

			List<TipoContratto> list = tipologicheService.getTipoContrattoMap();

			tipoCotnrattoListResponse.setList(list);
			tipoCotnrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoContratto>>(tipoCotnrattoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			tipoCotnrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoContratto>>(tipoCotnrattoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getTipoContrattoMap\n");

		return httpEntity;
	}

	@GetMapping("/tipo-ccnl-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoCcnl>> getCcnlMap() {

		HttpEntity<TipologicheListResponse<TipoCcnl>> httpEntity = null;

		TipologicheListResponse<TipoCcnl> ccnlListResponse = new TipologicheListResponse<TipoCcnl>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getCcnlMap");

			List<TipoCcnl> list = tipologicheService.getTipoCcnlMap();

			ccnlListResponse.setList(list);
			ccnlListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoCcnl>>(ccnlListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			ccnlListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoCcnl>>(ccnlListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getCcnlMap\n");

		return httpEntity;
	}

	@GetMapping("/tipo-livelli-contrattuali-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoLivelloContratto>> getTipoLivelloContrattoMap() {

		HttpEntity<TipologicheListResponse<TipoLivelloContratto>> httpEntity = null;

		TipologicheListResponse<TipoLivelloContratto> livelloContrattoListResponse = new TipologicheListResponse<TipoLivelloContratto>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getTipoLivelloContrattoMap");

			List<TipoLivelloContratto> list = tipologicheService.getTipoLivelloContrattoMap();

			livelloContrattoListResponse.setList(list);
			livelloContrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoLivelloContratto>>(livelloContrattoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			livelloContrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoLivelloContratto>>(livelloContrattoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getTipoLivelloContrattoMap\n");

		return httpEntity;
	}

	@GetMapping("/tipo-causa-fine-rapporto-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoCausaFineRapporto>> getTipoCausaFineRapportoMap() {

		HttpEntity<TipologicheListResponse<TipoCausaFineRapporto>> httpEntity = null;

		TipologicheListResponse<TipoCausaFineRapporto> livelloContrattoListResponse = new TipologicheListResponse<TipoCausaFineRapporto>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getTipoCausaFineRapportoMap");

			List<TipoCausaFineRapporto> list = tipologicheService.getTipoCausaFineRapportoMap();

			livelloContrattoListResponse.setList(list);
			livelloContrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoCausaFineRapporto>>(livelloContrattoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			livelloContrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoCausaFineRapporto>>(livelloContrattoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getTipoCausaFineRapportoMap\n");

		return httpEntity;
	}

	@GetMapping("/tipo-canale-reclutamento-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoCanaleReclutamento>> getTipoCanaleReclutamentoMap() {

		HttpEntity<TipologicheListResponse<TipoCanaleReclutamento>> httpEntity = null;

		TipologicheListResponse<TipoCanaleReclutamento> livelloContrattoListResponse = new TipologicheListResponse<TipoCanaleReclutamento>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getTipoCanaleReclutamentoMap");

			List<TipoCanaleReclutamento> list = tipologicheService.getTipoCanaleReclutamentoMap();

			livelloContrattoListResponse.setList(list);
			livelloContrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoCanaleReclutamento>>(livelloContrattoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			livelloContrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoCanaleReclutamento>>(livelloContrattoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getTipoCanaleReclutamentoMap\n");

		return httpEntity;
	}

	@GetMapping("/tipo-azienda-cliente-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoAziendaCliente>> getTipoAziendaClienteMap() {

		HttpEntity<TipologicheListResponse<TipoAziendaCliente>> httpEntity = null;

		TipologicheListResponse<TipoAziendaCliente> livelloContrattoListResponse = new TipologicheListResponse<TipoAziendaCliente>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getTipoLivelloContrattoMap");

			List<TipoAziendaCliente> list = tipologicheService.getTipoAziendaClienteMap();

			livelloContrattoListResponse.setList(list);
			livelloContrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoAziendaCliente>>(livelloContrattoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			livelloContrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoAziendaCliente>>(livelloContrattoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getTipoLivelloContrattoMap\n");

		return httpEntity;
	}

	@GetMapping("/livelli-by-ccnl/{ccnl}")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoLivelloContratto>> getTipoLivelloContrattoMap(
			@PathVariable("ccnl") String ccnl) {

		HttpEntity<TipologicheListResponse<TipoLivelloContratto>> httpEntity = null;

		TipologicheListResponse<TipoLivelloContratto> livelloContrattoListResponse = new TipologicheListResponse<TipoLivelloContratto>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getTipoLivelloContrattoMap");

			List<TipoLivelloContratto> list = tipologicheService.getTipoLivelloContrattoMap(ccnl);

			livelloContrattoListResponse.setList(list);
			livelloContrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoLivelloContratto>>(livelloContrattoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			livelloContrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoLivelloContratto>>(livelloContrattoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getTipoLivelloContrattoMap\n");

		return httpEntity;
	}

	@GetMapping("/tipo-causa-fine-contratto-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoCausaFineContratto>> getTipoCausaFineContratto() {

		HttpEntity<TipologicheListResponse<TipoCausaFineContratto>> httpEntity = null;

		TipologicheListResponse<TipoCausaFineContratto> tipoCausaFineContrattoListResponse = new TipologicheListResponse<TipoCausaFineContratto>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getTipoLivelloContrattoMap");

			List<TipoCausaFineContratto> list = tipologicheService.getTipoCausaFineContratto();

			tipoCausaFineContrattoListResponse.setList(list);
			tipoCausaFineContrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoCausaFineContratto>>(
					tipoCausaFineContrattoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			tipoCausaFineContrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoCausaFineContratto>>(
					tipoCausaFineContrattoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getTipoLivelloContrattoMap\n");

		return httpEntity;
	}

	@GetMapping("/comuni-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<Comune>> getComuniMap() {

		HttpEntity<TipologicheListResponse<Comune>> httpEntity = null;

		TipologicheListResponse<Comune> comuniListResponse = new TipologicheListResponse<Comune>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getComuniMap");

			List<Comune> list = tipologicheService.getComuniMap();

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

			List<Provincia> list = tipologicheService.getProvinceMap();

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

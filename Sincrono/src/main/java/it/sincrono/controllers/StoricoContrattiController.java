package it.sincrono.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoContratti;
import it.sincrono.requests.StoricoContrattiRequest;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.StoricoContrattiListResponse;
import it.sincrono.responses.StoricoContrattiResponse;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.StoricoContrattiService;

@RestController
public class StoricoContrattiController {

	@Autowired
	private StoricoContrattiService storicoContrattiService;

	@GetMapping("/Storico-Contratti")
	public @ResponseBody HttpEntity<StoricoContrattiListResponse> fetchAllStoricoContratti() {
		HttpEntity<StoricoContrattiListResponse> httpEntity;

		StoricoContrattiListResponse StoricoContrattiListResponse = new StoricoContrattiListResponse();

		try {
			List<StoricoContratti> contratti = storicoContrattiService.listStoricoContratti();

			StoricoContrattiListResponse.setList(contratti);
			StoricoContrattiListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<StoricoContrattiListResponse>(StoricoContrattiListResponse);
			System.out.println("bau");
		} catch (Exception e) {
			StoricoContrattiListResponse.setEsito(new Esito(404, e.getMessage(), null));
			httpEntity = new HttpEntity<StoricoContrattiListResponse>(StoricoContrattiListResponse);
		}
		return httpEntity;

	}

	@GetMapping("/Storico-Contratti/{id}")
	public @ResponseBody HttpEntity<StoricoContrattiResponse> getStoricoContrattiById(@PathVariable Integer id) {

		HttpEntity<StoricoContrattiResponse> httpEntity;

		StoricoContrattiResponse StoricoContrattiResponse = new StoricoContrattiResponse();

		try {
			StoricoContratti storicoContratti = storicoContrattiService.getStoricoContrattiById(id);

			StoricoContrattiResponse.setStoricoContratti(storicoContratti);
			StoricoContrattiResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<StoricoContrattiResponse>(StoricoContrattiResponse);
			System.out.println("ciao");
		} catch (Exception e) {
			StoricoContrattiResponse.setEsito(new Esito(404, e.getMessage(), new String[] { String.valueOf(id) }));
			httpEntity = new HttpEntity<StoricoContrattiResponse>(StoricoContrattiResponse);
		}
		return httpEntity;
	}

	@PostMapping("/Storico-Contratti")
	public @ResponseBody HttpEntity<GenericResponse> saveStoricoContratti(
			@RequestBody StoricoContrattiRequest StoricoContrattiRequest) {
		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			storicoContrattiService.insert(StoricoContrattiRequest.getStoricoContratti());
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
			System.out.println("ciao");
		} catch (Exception e) {
			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		return httpEntity;
	}

	@PutMapping("/Storico-Contratti")
	public @ResponseBody HttpEntity<GenericResponse> updateStoricoContratti(
			@RequestBody StoricoContrattiRequest StoricoContrattiRequest) {
		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			storicoContrattiService.update(StoricoContrattiRequest.getStoricoContratti());
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
			System.out.println("ciao");
		} catch (Exception e) {
			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		return httpEntity;
	}

	@DeleteMapping("/StoricoContratti/{id}")
	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer id) {

		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {

			storicoContrattiService.delete(id);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(id) }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

}

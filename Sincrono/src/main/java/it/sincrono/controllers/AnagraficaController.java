package it.sincrono.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.responses.AnagraficaDtoListResponse;
import it.sincrono.responses.AnagraficaDtoResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.services.AnagraficaService;
import it.sincrono.services.UtenteService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class AnagraficaController {

	@Autowired
	private AnagraficaService anagraficaService;

	@Autowired
	private UtenteService utenteService;
//
//	@GetMapping("/anagrafica/{id}")
//	public @ResponseBody HttpEntity<AnagraficaResponse> getById(@PathVariable Integer id) {
//
//		HttpEntity<AnagraficaResponse> httpEntity;
//
//		AnagraficaResponse anagraficaResponse = new AnagraficaResponse();
//
//		try {
//			System.out.println("START invocation getAll() of controller layer");
//
//			Anagrafica anagrafica = anagraficaService.getById(id);
//
//			anagraficaResponse.setEsito(new Esito());
//			anagraficaResponse.setAnagrafica(anagrafica);
//
//			httpEntity = new HttpEntity<AnagraficaResponse>(anagraficaResponse);
//
//			System.out.println("END invocation getAll() of controller layer");
//
//		} catch (ServiceException e) {
//			anagraficaResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<AnagraficaResponse>(anagraficaResponse);
//		}
//
//		return httpEntity;
//	}

//	@GetMapping("/anagrafica-list")
//	public @ResponseBody HttpEntity<AnagraficaListResponse> getAll() {
//
//		HttpEntity<AnagraficaListResponse> httpEntity = null;
//
//		AnagraficaListResponse anagraficaListResponse = new AnagraficaListResponse();
//
//		try {
//			System.out.println("START invocation getAll() of controller layer");
//
//			List<Anagrafica> anagrafiche = anagraficaService.list();
//
//			anagraficaListResponse.setList(anagrafiche);
//			anagraficaListResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<AnagraficaListResponse>(anagraficaListResponse);
//
//			System.out.println("END invocation getAll() of controller layer");
//
//		} catch (ServiceException e) {
//			anagraficaListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<AnagraficaListResponse>(anagraficaListResponse);
//		}
//
//		return httpEntity;
//	}

//	@PostMapping("/anagrafica")
//	public @ResponseBody HttpEntity<GenericResponse> insert(@RequestBody AnagraficaRequest anagraficaRequest) {
//
//		HttpEntity<GenericResponse> httpEntity = null;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			System.out.println("START invocation insert(anagrafica) of controller layer");
//
//			anagraficaService.insert(anagraficaRequest.getAnagrafica());
//
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//
//			System.out.println("END invocation insert(anagrafica) of controller layer");
//
//		} catch (ServiceException e) {
//			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//
//		return httpEntity;
//	}

//	@PutMapping("/anagrafica")
//	public @ResponseBody HttpEntity<GenericResponse> update(@RequestBody AnagraficaRequest anagraficaRequest) {
//
//		HttpEntity<GenericResponse> httpEntity = null;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			System.out.println("START invocation insert(anagrafica) of controller layer");
//
//			anagraficaService.update(anagraficaRequest.getAnagrafica());
//
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//
//			System.out.println("END invocation insert(anagrafica) of controller layer");
//
//		} catch (ServiceException e) {
//			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//
//		return httpEntity;
//	}

//	@DeleteMapping("/anagrafica/{id}")
//	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer ID) {
//
//		HttpEntity<GenericResponse> httpEntity = null;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			System.out.println("START invocation insert(anagrafica) of controller layer");
//
//			anagraficaService.delete(ID);
//
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//
//			System.out.println("END invocation insert(anagrafica) of controller layer");
//
//		} catch (ServiceException e) {
//			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//
//		return httpEntity;
//	}

	@PostMapping("/filter")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> filterListAnagraficaDto(
			@RequestBody AnagraficaRequestDto anagraficaRequestDto) {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			System.out.println("START invocation of filterListAnagraficaDto");

			List<AnagraficaDto> anagrafiche = anagraficaService
					.filterListAnagraficaDto(anagraficaRequestDto);

			anagraficaDtoListResponse.setList(anagrafiche);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

			System.out.println("END invocation of filterListAnagraficaDto");

		} catch (ServiceException e) {
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}

		return httpEntity;
	}

	@GetMapping("/list")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> listAnagraficaDto() {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			System.out.println("START invocation of listAnagraficaDto");

			List<AnagraficaDto> anagrafiche = anagraficaService.listAnagraficaDto();

			anagraficaDtoListResponse.setList(anagrafiche);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

			System.out.println("END invocation of listAnagraficaDto");

		} catch (ServiceException e) {
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}

		return httpEntity;
	}

	@GetMapping("/dettaglio/{id}")
	public @ResponseBody HttpEntity<AnagraficaDtoResponse> dettaglioAnagraficaDto(@PathVariable("id") Integer id) {
		HttpEntity<AnagraficaDtoResponse> httpEntity = null;
		AnagraficaDtoResponse anagraficaDtoResponse = new AnagraficaDtoResponse();
		try {
			System.out.println("START invocation dettaglioAnagraficaDto of controller layer");
			AnagraficaDto anagraficaDto = anagraficaService.getAnagraficaDto(id);
			anagraficaDtoResponse.setAnagraficaDto(anagraficaDto);
			anagraficaDtoResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoResponse>(anagraficaDtoResponse);
			System.out.println("END invocation dettaglioAnagraficaDto of controller layer");

		} catch (ServiceException e) {
			anagraficaDtoResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoResponse>(anagraficaDtoResponse);
		}
		return httpEntity;
	}

	@PostMapping("/inserisci")
	public @ResponseBody HttpEntity<GenericResponse> insertAnagraficaDto(
			@RequestBody AnagraficaRequestDto anagraficaRequestDto) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			System.out.println("START invocation insertAnagraficaDto");

			anagraficaService.insertAnagraficaDto(anagraficaRequestDto.getAnagraficaDto());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			System.out.println("END invocation insertAnagraficaDto");

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	@PutMapping("/modifica")
	public @ResponseBody HttpEntity<GenericResponse> updateAnagraficadto(
			@RequestBody AnagraficaRequestDto anagraficaRequestDto) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("START invocation updateAnagraficadto");

			anagraficaService.updateAnagraficaDto(anagraficaRequestDto.getAnagraficaDto());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			System.out.println("END invocation updateAnagraficadto");

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	/*
	 * @PostMapping("/aggiungi-contratto-commessa") public @ResponseBody
	 * HttpEntity<GenericResponse> insertAnagraficaDtoRelations(
	 * 
	 * @RequestBody AnagraficaRequestDto anagraficaRequestDto) {
	 * 
	 * HttpEntity<GenericResponse> httpEntity = null;
	 * 
	 * GenericResponse genericResponse = new GenericResponse();
	 * 
	 * try {
	 * System.out.println("START invocation insert(anagrafica) of controller layer"
	 * );
	 * 
	 * anagraficaService.insertAnagraficaDtoRelations(anagraficaRequestDto.
	 * getAnagraficaDto());
	 * 
	 * genericResponse.setEsito(new Esito());
	 * 
	 * httpEntity = new HttpEntity<GenericResponse>(genericResponse);
	 * 
	 * System.out.println("END invocation insert(anagrafica) of controller layer");
	 * 
	 * } catch (ServiceException e) { genericResponse.setEsito(new
	 * Esito(e.getCode(), e.getMessage(), null)); httpEntity = new
	 * HttpEntity<GenericResponse>(genericResponse); }
	 * 
	 * return httpEntity; }
	 */

	@PutMapping("/delete")
	public @ResponseBody HttpEntity<GenericResponse> deleteAnagraficaDto(
			@RequestBody AnagraficaRequestDto anagraficaRequestDto) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("START invocation deleteAnagraficaDto");

			anagraficaService.deleteAnagraficaDto(anagraficaRequestDto.getAnagraficaDto());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			System.out.println("END invocation deleteAnagraficaDto");

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}
	
	@PutMapping("/retain")
	public @ResponseBody HttpEntity<GenericResponse> retainAnagraficaDto(
			@RequestBody AnagraficaRequestDto anagraficaRequestDto) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("START invocation deleteAnagraficaDto");

			anagraficaService.retainAnagraficaDto(anagraficaRequestDto.getAnagraficaDto());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			System.out.println("END invocation deleteAnagraficaDto");

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

//	@GetMapping("/anagrafica-list-contratti")
//	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> anagraficaListContratti() {
//		
//		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;
//
//		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
//		try {
//			System.out.println("START invocation getAll() of controller layer");
//
//			List<AnagraficaDto> anagrafiche = anagraficaService.anagraficaListContratti();
//
//			anagraficaDtoListResponse.setList(anagrafiche);
//			anagraficaDtoListResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
//
//			System.out.println("END invocation getAll() of controller layer");
//
//		} catch (ServiceException e) {
//			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
//		}
//
//		return httpEntity;
//	}

//	@DeleteMapping("/anagraficaDeleteScattoContratti")
//	public @ResponseBody HttpEntity<GenericResponse> deleteScattiContratto() {
//
//		HttpEntity<GenericResponse> httpEntity = null;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			System.out.println("START invocation insert(anagrafica) of controller layer");
//
//			anagraficaService.deleteScattoContratti();
//
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//
//			System.out.println("END invocation insert(anagrafica) of controller layer");
//
//		} catch (ServiceException e) {
//			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//
//		return httpEntity;
//	}

	@GetMapping("/dettaglio-token/{token}")
	public @ResponseBody HttpEntity<AnagraficaDtoResponse> dettaglioAnagraficaDtoByToken(
			@PathVariable("token") String token) {
		HttpEntity<AnagraficaDtoResponse> httpEntity = null;
		AnagraficaDtoResponse anagraficaDtoResponse = new AnagraficaDtoResponse();
		try {
			System.out.println("START invocation dettaglioAnagraficaDtoByToken");
			AnagraficaDto anagraficaDto = anagraficaService.getAnagraficaDtoByToken(token);
			anagraficaDtoResponse.setAnagraficaDto(anagraficaDto);
			anagraficaDtoResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoResponse>(anagraficaDtoResponse);
			System.out.println("END invocation dettaglioAnagraficaDtoByToken");

		} catch (ServiceException e) {
			anagraficaDtoResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoResponse>(anagraficaDtoResponse);
		}
		return httpEntity;
	}

}

package it.sincrono.services.validator;

import org.apache.logging.log4j.Level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Contratto;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.DocumentRequest;
import it.sincrono.services.utils.MapperCustom;

@Component
public class DocumentValidator {
	private static final Logger LOGGER = LogManager.getLogger(DocumentValidator.class);

	@Autowired
	MapperCustom mapperCustom;

	@Autowired
	AnagraficaRepository anagraficaRepository;

	public Boolean getValidate(DocumentRequest documentRequest) {

		if (documentRequest.getCodiceFiscale() == null && documentRequest.equals("")) {

			return false;

		}

		Anagrafica anagrafica = anagraficaRepository.findByCodiceFiscale(documentRequest.getCodiceFiscale());

		if (anagrafica == null) {

			return false;
		}
		
		
		return true;

	}

	public Boolean addValidate(DocumentRequest documentRequest) {

		if ((documentRequest.getCodiceFiscale() == null && documentRequest.equals("")) 
				|| 
				(documentRequest.getBase64()==null && documentRequest.getBase64().equals(""))) {

			return false;

		}
		
		
		return true;
		

	}

}

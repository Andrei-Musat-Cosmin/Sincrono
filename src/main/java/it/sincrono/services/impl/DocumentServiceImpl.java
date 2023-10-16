package it.sincrono.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Rapportino;
import it.sincrono.entities.RapportinoInviato;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.RapportinoInviatoRepository;
import it.sincrono.repositories.RapportinoRepository;
import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.DocumentRequest;
import it.sincrono.requests.RapportinoRequest;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.responses.DocumentResponse;
import it.sincrono.services.DocumentService;
import it.sincrono.services.RapportinoService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.utils.ExcelUtil;
import it.sincrono.services.utils.FileUtil;
import it.sincrono.services.utils.FilterCustom;
import it.sincrono.services.utils.RapportinoUtil;
import it.sincrono.services.validator.DocumentValidator;
import it.sincrono.services.validator.RapportinoValidator;

@Service
public class DocumentServiceImpl extends BaseServiceImpl implements DocumentService {

	@Value("${anagrafiche-profili.path-prefix}")
	private String PREFIX;

	@Value("${anagrafiche-profili.anagrafiche-profili-documenti.path-prefix-documenti}")
	private String DOCUMENT;

	private static final Logger LOGGER = LogManager.getLogger(DocumentServiceImpl.class);

	@Autowired
	FileUtil fileUtil;

	@Autowired
	DocumentValidator documentValidator;
	

	@Override
	public void addImage(DocumentRequest documentRequest) throws ServiceException {
		
		if (!documentValidator.addValidate(documentRequest)) {
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di documentRequest");
		}
		

		try {

			fileUtil.saveFileImage(PREFIX + documentRequest.getCodiceFiscale() + DOCUMENT, documentRequest.getBase64());

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, ServiceMessages.ERRORE_VALIDAZIONE);
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public DocumentResponse getImage(DocumentRequest documentRequest) throws ServiceException {
		
		if (!documentValidator.getValidate(documentRequest)) {
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di documentRequest");
		}

		DocumentResponse documentResponse = new DocumentResponse();

		try {

			documentResponse = fileUtil.readFileImage(PREFIX + documentRequest.getCodiceFiscale() + DOCUMENT);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, ServiceMessages.ERRORE_VALIDAZIONE);
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

		return documentResponse;

	}

}

package it.sincrono.services.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.sincrono.requests.DocumentRequest;
import it.sincrono.responses.DocumentResponse;
import it.sincrono.services.DocumentService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.utils.FileUtil;
import it.sincrono.services.validator.DocumentValidator;

@Service
public class DocumentServiceImpl extends BaseServiceImpl implements DocumentService {

	@Value("${anagrafiche-profili.path-prefix}")
	private static String PREFIX;

	@Value("${anagrafica-profili.destinazione}")
	private static String DESTINAZIONE;

	@Value("${anagrafiche-profili.anagrafiche-profili-documenti.path-prefix-documenti}")
	private static String DOCUMENT;

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

			fileUtil.saveFileImage(PREFIX + DESTINAZIONE + documentRequest.getCodiceFiscale() + DOCUMENT,
					documentRequest.getBase64());

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

			documentResponse = fileUtil
					.readFileImage(PREFIX + DESTINAZIONE + documentRequest.getCodiceFiscale() + DOCUMENT);

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

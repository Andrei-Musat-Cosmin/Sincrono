package it.sincrono.services.impl;

import java.time.LocalDate;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.RapportinoRequest;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.services.RapportinoService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.utils.DateUtil;
import it.sincrono.services.utils.FileUtil;
import it.sincrono.services.utils.RapportinoUtil;
import it.sincrono.services.validator.RapportinoValidator;

@Service
public class RapportinoServiceImpl extends BaseServiceImpl implements RapportinoService {

	@Value("${anagrafiche-profili.path-prefix}")
	private String PREFIX;

	private static final Logger LOGGER = LogManager.getLogger(RapportinoServiceImpl.class);

	@Autowired
	FileUtil fileUtil;

	@Autowired
	RapportinoValidator rapportinoValidator;

	@Autowired
	RapportinoUtil rapportinoUtil;

	@Override
	public RapportinoDto getRapportino(RapportinoRequestDto rapportinoRequestDto) throws ServiceException {

		RapportinoDto rapportinoDto = new RapportinoDto();

		try {

			if (!rapportinoValidator.validate(rapportinoRequestDto.getRapportinoDto())) {
				throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di rapportinoDto");
			}

			rapportinoDto = fileUtil
					.readFile(PREFIX + rapportinoRequestDto.getRapportinoDto().getAnagrafica().getCodiceFiscale() + "/"
							+ rapportinoRequestDto.getRapportinoDto().getAnnoRequest() + "/"
							+ rapportinoRequestDto.getRapportinoDto().getMeseRequest() + ".txt");

			/*rapportinoDto.setAnnoRequest(rapportinoRequestDto.getRapportinoDto().getAnnoRequest());
			
			rapportinoDto.setMeseRequest(rapportinoRequestDto.getRapportinoDto().getMeseRequest());

			rapportinoUtil.calcoloRapportinoGiorniUtiliLavoro(rapportinoDto);

			rapportinoUtil.calcoloRapportinoDtoGiorniLavorati(rapportinoDto);*/

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, ServiceMessages.ERRORE_VALIDAZIONE);
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return rapportinoDto;
	}

	@Override
	public RapportinoDto updateRapportino(RapportinoRequestDto rapportinoRequestDto) throws ServiceException {

		LocalDate oggi = LocalDate.now();
		String filePath = PREFIX + rapportinoRequestDto.getRapportinoDto().getAnagrafica().getCodiceFiscale() + "/"
				+ oggi.getYear() + "/" + oggi.getMonthValue() + ".txt";
		RapportinoDto rapportinoDto = new RapportinoDto();

		try {

			fileUtil.saveFile(filePath, rapportinoRequestDto);
		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getCause());
		}

		return rapportinoDto;
	}

}

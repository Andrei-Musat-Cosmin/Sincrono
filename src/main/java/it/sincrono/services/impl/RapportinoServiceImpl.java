package it.sincrono.services.impl;

import java.time.LocalDate;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.services.RapportinoService;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.utils.FileUtil;

@Service
public class RapportinoServiceImpl extends BaseServiceImpl implements RapportinoService {

	@Value("${anagrafiche-profili.path-prefix}")
	private String PREFIX;

	private static final Logger LOGGER = LogManager.getLogger(RapportinoServiceImpl.class);

	@Autowired
	FileUtil fileUtil;

	@Override
	public RapportinoDto getRapportino(String codiceFiscale) throws ServiceException {

		RapportinoDto rapportinoDto = new RapportinoDto();

		try {

			LocalDate oggi = LocalDate.now();

			rapportinoDto = fileUtil
					.readFile(PREFIX + codiceFiscale + "/" + oggi.getYear() + "/" + oggi.getMonthValue() + ".txt");

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

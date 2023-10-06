package it.sincrono.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.services.DashboardService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.utils.FilterCustom;
import it.sincrono.services.utils.MapperCustom;

@Service
public class DashboardServiceImpl extends BaseServiceImpl implements DashboardService {
	private static final Logger LOGGER = LogManager.getLogger(DashboardServiceImpl.class);

	@Autowired
	private AnagraficaRepository anagraficaRepository;
	@Autowired
	private MapperCustom mapper;
	@Autowired
	private FilterCustom filter;

	@Override
	public List<AnagraficaDto> getCommesseInscadenza() throws ServiceException {

		List<AnagraficaDto> list = new ArrayList<AnagraficaDto>();

		try {

			for (AnagraficaDto anagraficaDto : anagraficaRepository.findAllId().stream().map(mapper::toAnagraficaDto)
					.collect(Collectors.toList())) {

				anagraficaDto.setCommesse(anagraficaDto.getCommesse().stream()
						.filter(commessa -> filter.checkCommesseInScadenza(commessa)).collect(Collectors.toList()));

				list.add(anagraficaDto);
			}

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<AnagraficaDto> getContrattiInscadenza() throws ServiceException {
		List<AnagraficaDto> list = null;

		try {
			list = anagraficaRepository.findAllId().stream().map(mapper::toAnagraficaDto).collect(Collectors.toList())
					.stream().filter(anagraficaDto -> filter.checkContrattiInScadenza(anagraficaDto.getContratto()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<AnagraficaDto> listCommesse(AnagraficaRequestDto anagraficaRequestDto) throws ServiceException {
		List<AnagraficaDto> list = null;

		try {
			list = this.listAllCommesse().stream()
					.filter(anagraficaDto -> filter.toFilterAnagraficaDto(anagraficaDto, anagraficaRequestDto))
					.collect(Collectors.toList());
			for(AnagraficaDto anagraficaDto: list)
				anagraficaDto.getCommesse().stream().filter(commessa->filter.toFilterCommesse(commessa,anagraficaRequestDto));
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<AnagraficaDto> listAllCommesse() throws ServiceException {
		List<AnagraficaDto> list = new ArrayList<AnagraficaDto>();

		try {
			for (AnagraficaDto anagraficaDto : anagraficaRepository.findAllId().stream().map(mapper::toAnagraficaDto)
					.collect(Collectors.toList())) {

				anagraficaDto.setCommesse(anagraficaDto.getCommesse().stream()
						.filter(commessa -> filter.checkScaduta(commessa)).collect(Collectors.toList()));

				list.add(anagraficaDto);
			}
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}
}

package it.sincrono.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Richieste;
import it.sincrono.entities.TipoRichieste;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.RichiestaRepository;
import it.sincrono.repositories.TipoRichiestaRepository;
import it.sincrono.repositories.dto.DuplicazioniRichiestaDto;
import it.sincrono.repositories.dto.RichiestaDto;
import it.sincrono.requests.RichiestaRequest;
import it.sincrono.services.RichiestaService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.utils.ConvertInDto;

@Service
public class RichiestaServiceImpl extends BaseServiceImpl implements RichiestaService {

	@Autowired
	RichiestaRepository richiestaRepository;

	@Autowired
	AnagraficaRepository anagraficaRepository;

	@Autowired
	TipoRichiestaRepository tipoRichiestaRepository;

	@Autowired
	ConvertInDto convertInDto;

	private static final Logger LOGGER = LogManager.getLogger(RichiestaServiceImpl.class);

	@Override
	public RichiestaDto getRichiesta(Integer id) throws ServiceException {

		try {

			RichiestaDto richiestaDto = new RichiestaDto();

			List<TipoRichieste> tipoRichieste = richiestaRepository.getRichiesta(id);

			convertInDto.convertInRichiestaDto(richiestaDto, tipoRichieste);

			return richiestaDto;

		} catch (Exception e) {

			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override
	public void insertRichiesta(RichiestaDto richiestaDto) throws ServiceException {

		try {

			Integer idRichiesta = richiestaRepository.saveAndFlush(
					new Richieste(null, anagraficaRepository.findByCodiceFiscale(richiestaDto.getCodiceFiscale()),
							richiestaDto.getAnno(), richiestaDto.getMese(), null))
					.getId();

			tipoRichiestaRepository.saveAllAndFlush(convertInDto.convertInTipoRichieste(richiestaDto, idRichiesta));

		} catch (Exception e) {

			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override
	public List<RichiestaDto> listRichiesteDto(RichiestaDto richiestaDto) throws ServiceException {

		try {

			List<RichiestaDto> listRichiestaDto = null;

			Anagrafica anagrafica = anagraficaRepository.findByCodiceFiscale(richiestaDto.getCodiceFiscale());

			List<TipoRichieste> tipoRichieste = tipoRichiestaRepository.getRichieste(richiestaDto.getAnno(),
					richiestaDto.getMese(), anagrafica.getId());

			if (tipoRichieste != null && tipoRichieste.size() > 0)
				listRichiestaDto = convertInDto.convertInDifferentRichiestaDto(tipoRichieste);

			return listRichiestaDto;

		} catch (Exception e) {

			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

}

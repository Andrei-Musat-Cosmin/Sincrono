package it.sincrono.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Rapportino;
import it.sincrono.entities.RapportinoInviato;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.RapportinoInviatoRepository;
import it.sincrono.repositories.RapportinoRepository;
import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.RapportinoRequest;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.services.RapportinoService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.utils.ExcelUtil;
import it.sincrono.services.utils.FileUtil;
import it.sincrono.services.utils.FilterCustom;
import it.sincrono.services.utils.RapportinoUtil;
import it.sincrono.services.validator.RapportinoValidator;
import java.util.ArrayList;

@Service
public class RapportinoServiceImpl extends BaseServiceImpl implements RapportinoService {

	@Value("${anagrafiche-profili.path-prefix}")
	private String PREFIX;

	@Value("${anagrafiche-profili.anagrafiche-profili-rapportini.path-prefix-rapportini}")
	private String RAPPORTINI;

	private static final Logger LOGGER = LogManager.getLogger(RapportinoServiceImpl.class);

	@Autowired
	FileUtil fileUtil;

	@Autowired
	RapportinoInviatoRepository rapportinoInviatoRepository;

	@Autowired
	RapportinoRepository rapportinoRepository;

	@Autowired
	AnagraficaRepository anagraficaRepository;

	@Autowired
	RapportinoValidator rapportinoValidator;

	@Autowired
	RapportinoUtil rapportinoUtil;

	@Autowired
	ExcelUtil excelUtil;

	@Autowired
	FilterCustom filterCustom;

	@Override
	public RapportinoDto getRapportino(RapportinoRequestDto rapportinoRequestDto) throws ServiceException {

		RapportinoDto rapportinoDto = new RapportinoDto();

		try {

			if (!rapportinoValidator.getValidate(rapportinoRequestDto.getRapportinoDto())) {
				throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di rapportinoDto");
			}

			rapportinoDto = fileUtil
					.readFile(PREFIX + rapportinoRequestDto.getRapportinoDto().getAnagrafica().getCodiceFiscale()
							+ RAPPORTINI + +rapportinoRequestDto.getRapportinoDto().getAnnoRequest() + "/"
							+ rapportinoRequestDto.getRapportinoDto().getMeseRequest() + ".txt");

			rapportinoDto.setAnnoRequest(rapportinoRequestDto.getRapportinoDto().getAnnoRequest());

			rapportinoDto.setMeseRequest(rapportinoRequestDto.getRapportinoDto().getMeseRequest());

			rapportinoUtil.calcoloRapportinoGiorniUtiliLavoro(rapportinoDto);

			rapportinoUtil.calcoloRapportinoDtoGiorniLavorati(rapportinoDto);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, ServiceMessages.ERRORE_VALIDAZIONE);
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

		return rapportinoDto;
	}

	@Override
	public void updateRapportino(RapportinoRequestDto rapportinoRequestDto) throws ServiceException {

		if (!rapportinoValidator.updateValidate(rapportinoRequestDto.getRapportinoDto(), anagraficaRepository
				.findByCodiceFiscale(rapportinoRequestDto.getRapportinoDto().getAnagrafica().getCodiceFiscale()))) {
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di rapportinoDto");
		}

		String filePath = PREFIX + rapportinoRequestDto.getRapportinoDto().getAnagrafica().getCodiceFiscale()
				+ RAPPORTINI + +rapportinoRequestDto.getRapportinoDto().getAnnoRequest() + "/"
				+ rapportinoRequestDto.getRapportinoDto().getMeseRequest() + ".txt";

		try {

			fileUtil.saveFile(filePath, rapportinoRequestDto);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, ServiceMessages.ERRORE_VALIDAZIONE);
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public Boolean aggiungiNote(RapportinoRequestDto rapportinoRequestDto) throws ServiceException {

		String filePath = PREFIX + rapportinoRequestDto.getRapportinoDto().getAnagrafica().getCodiceFiscale()
				+ RAPPORTINI + +rapportinoRequestDto.getRapportinoDto().getAnnoRequest() + "/"
				+ rapportinoRequestDto.getRapportinoDto().getMeseRequest() + ".txt";

		try {

			fileUtil.appendNote(filePath, rapportinoRequestDto.getRapportinoDto().getNote());

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

		return true;
	}

	@Override
	public void insertRapportino(RapportinoInviato rapportinoInviato) throws ServiceException {
		try {
			rapportinoInviatoRepository.saveAndFlush(rapportinoInviato);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateFreeze(RapportinoInviato rapportinoInviato) throws ServiceException {
		try {
			RapportinoInviato currentRapportinoInviato = rapportinoInviatoRepository.findById(rapportinoInviato.getId())
					.get();
			if (rapportinoInviato.getCheckFreeze()) {

				currentRapportinoInviato.setCheckFreeze(rapportinoInviato.getCheckFreeze());

				rapportinoInviatoRepository.saveAndFlush(currentRapportinoInviato);
			} else {
				if (currentRapportinoInviato.getCheckFreeze())
					rapportinoInviatoRepository.delete(rapportinoInviato);
			}
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		try {
			rapportinoInviatoRepository.deleteById(id);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public RapportinoInviato findByData(RapportinoDto rapportinoDto) throws ServiceException {
		return rapportinoInviatoRepository.findByData(rapportinoDto.getAnagrafica().getCodiceFiscale(),
				rapportinoDto.getAnnoRequest(), rapportinoDto.getMeseRequest());
	}

	@Override
	public List<RapportinoInviato> getRapportiniNotFreeze() throws ServiceException {
		List<RapportinoInviato> list = null;

		try {
			list = rapportinoInviatoRepository.getRapportiniNotFreeze();
			list = rapportinoInviatoRepository.getRapportiniNotFreeze();
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public void addRapportinoInDatabase(RapportinoRequest rapportinoRequest) throws ServiceException {

		RapportinoDto rapportinoDto = new RapportinoDto();

		List<Rapportino> rapportini = new ArrayList<>();

		Anagrafica anagrafica = anagraficaRepository.findByCodiceFiscale(rapportinoRequest.getCodiceFiscale());

		try {
			rapportinoDto = fileUtil.readFile(PREFIX + rapportinoRequest.getCodiceFiscale() + RAPPORTINI
					+rapportinoRequest.getAnno() + "/" + rapportinoRequest.getMese() + ".txt");

			for (GiornoDto giornoDto : rapportinoDto.getMese().getGiorni()) {
				Rapportino rapportino = new Rapportino();
				if (giornoDto.getGiorno() != null)
					rapportino.setGiorno(giornoDto.getGiorno());
				if (giornoDto.getOreOrdinarie() != null)
					rapportino.setOre(giornoDto.getOreOrdinarie().stream().mapToDouble(Double::doubleValue).sum());

				rapportino.setMese(rapportinoRequest.getMese());

				rapportino.setAnno(rapportinoRequest.getAnno());

				rapportino.setAnagrafica(anagrafica);

				rapportini.add(rapportino);
			}
			rapportinoRepository.saveAllAndFlush(rapportini);

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	@Transactional
	public void deleteRapportinoInDatabase(RapportinoRequest rapportinoRequest) throws ServiceException {

		try {

			Anagrafica anagrafica = anagraficaRepository.findByCodiceFiscale(rapportinoRequest.getCodiceFiscale());

			rapportinoRepository.deleteByMeseAndAnnoAndId(rapportinoRequest.getMese(), rapportinoRequest.getAnno(),
					anagrafica.getId());

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<RapportinoInviato> getRapportiniFreezeFilter(RapportinoInviato rapportinoInviato)
			throws ServiceException {

		List<RapportinoInviato> list = null;

		try {
			list = rapportinoInviatoRepository.getRapportiniFreeze().stream()
					.filter(rapportino -> filterCustom.toFilterRapportino(rapportino, rapportinoInviato))
					.collect(Collectors.toList());

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<RapportinoInviato> getRapportiniNotFreezeFilter(RapportinoInviato rapportinoInviato)
			throws ServiceException {

		List<RapportinoInviato> list = null;

		try {
			list = rapportinoInviatoRepository.getRapportiniFreeze().stream()
					.filter(rapportino -> filterCustom.toFilterRapportino(rapportino, rapportinoInviato))
					.collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<RapportinoInviato> getRapportiniFreeze() throws ServiceException {
		List<RapportinoInviato> list = null;

		try {
			list = rapportinoInviatoRepository.getRapportiniFreeze();
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public String getRapportinoB64(Integer anno, Integer mese) throws ServiceException {
		try {
			List<Rapportino> rapportini = rapportinoRepository.findByMeseAndAnno(anno, mese);
			return excelUtil.toExcel(rapportini);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

	@Override
	public boolean getCheckRapportinoInviato(RapportinoRequest rapportinoRequest) throws ServiceException {
		try {
			return rapportinoInviatoRepository.checkInviato(rapportinoRequest.getCodiceFiscale(),
					rapportinoRequest.getAnno(), rapportinoRequest.getMese());
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

}

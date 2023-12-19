package it.sincrono.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Rapportino;
import it.sincrono.entities.RapportinoInviato;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.RapportinoInviatoRepository;
import it.sincrono.repositories.RapportinoRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.dto.DuplicazioniGiornoDto;
import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.AnagraficaFilterRequestDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.requests.RapportinoRequest;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.services.RapportinoService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.utils.ExcelUtil;
import it.sincrono.services.utils.FileUtil;
import it.sincrono.services.utils.FilterCustom;
import it.sincrono.services.utils.MapperCustom;
import it.sincrono.services.utils.RapportinoUtil;
import it.sincrono.services.validator.RapportinoValidator;

@Service
public class RapportinoServiceImpl extends BaseServiceImpl implements RapportinoService {

	@Value("${anagrafiche-profili.path-prefix}")
	private String PREFIX;

	@Value("${anagrafiche-profili.destinazione}")
	private String DESTINAZIONE;

	@Value("${anagrafiche-profili.anagrafiche-profili-rapportini.path-prefix-rapportini}")
	private String RAPPORTINI;

	public String EXCELPATH = "provaSalvataggioExcel.xlsx";

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

	@Autowired
	private MapperCustom mapper;

	@Override
	public RapportinoDto getRapportino(RapportinoRequestDto rapportinoRequestDto) throws ServiceException {

		RapportinoDto rapportinoDto = new RapportinoDto();
		String msg = null;

		try {

			if ((msg = rapportinoValidator.validateFieldsForPath(rapportinoRequestDto.getRapportinoDto())) != null) {
				throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, msg);
			}

			rapportinoDto = fileUtil.readFile(
					PREFIX + DESTINAZIONE + rapportinoRequestDto.getRapportinoDto().getAnagrafica().getCodiceFiscale()
							+ RAPPORTINI + rapportinoRequestDto.getRapportinoDto().getAnnoRequest() + "/"
							+ rapportinoRequestDto.getRapportinoDto().getMeseRequest() + ".txt");

			rapportinoDto.setAnnoRequest(rapportinoRequestDto.getRapportinoDto().getAnnoRequest());

			rapportinoDto.setMeseRequest(rapportinoRequestDto.getRapportinoDto().getMeseRequest());

			rapportinoUtil.calcoloRapportinoGiorniUtiliLavoro(rapportinoDto);

			rapportinoUtil.calcoloRapportinoDtoGiorniLavorati(rapportinoDto);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

		return rapportinoDto;
	}

	@Override
	public void updateRapportino(RapportinoRequestDto rapportinoRequestDto) throws ServiceException {
		String msg = null;

		if ((msg = rapportinoValidator.validateGiornoDto(rapportinoRequestDto.getRapportinoDto())) != null) {
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, msg);
		}

		String filePath = PREFIX + DESTINAZIONE
				+ rapportinoRequestDto.getRapportinoDto().getAnagrafica().getCodiceFiscale() + RAPPORTINI
				+ rapportinoRequestDto.getRapportinoDto().getAnnoRequest() + "/"
				+ rapportinoRequestDto.getRapportinoDto().getMeseRequest() + ".txt";

		try {

			fileUtil.saveFile(filePath, rapportinoRequestDto);

		} catch (HttpMessageNotReadableException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.FORMATO_INCORRETTO);
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_SALVATAGGIO_FILE);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public Boolean aggiungiNote(RapportinoRequestDto rapportinoRequestDto) throws ServiceException {
		String msg = null;

		if ((msg = rapportinoValidator.validateNote(rapportinoRequestDto.getRapportinoDto())) != null) {
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, msg);
		}

		String filePath = PREFIX + DESTINAZIONE
				+ rapportinoRequestDto.getRapportinoDto().getAnagrafica().getCodiceFiscale() + RAPPORTINI
				+ rapportinoRequestDto.getRapportinoDto().getAnnoRequest() + "/"
				+ rapportinoRequestDto.getRapportinoDto().getMeseRequest() + ".txt";

		try {

			fileUtil.appendNote(filePath, rapportinoRequestDto.getRapportinoDto().getNote());

		} catch (HttpMessageNotReadableException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.FORMATO_INCORRETTO);
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_SALVATAGGIO_FILE);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

		return true;
	}
	
	

	@Override
	public Boolean aggiungiNoteDipendente(RapportinoRequestDto rapportinoRequestDto) throws ServiceException {
		
		String msg = null;

		if ((msg = rapportinoValidator.validateNoteDipendente(rapportinoRequestDto.getRapportinoDto())) != null) {
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, msg);
		}

		String filePath = PREFIX + DESTINAZIONE
				+ rapportinoRequestDto.getRapportinoDto().getAnagrafica().getCodiceFiscale() + RAPPORTINI
				+ rapportinoRequestDto.getRapportinoDto().getAnnoRequest() + "/"
				+ rapportinoRequestDto.getRapportinoDto().getMeseRequest() + ".txt";

		try {

			fileUtil.appendNoteDipendente(filePath, rapportinoRequestDto.getRapportinoDto().getNoteDipendente());

		} catch (HttpMessageNotReadableException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.FORMATO_INCORRETTO);
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_SALVATAGGIO_FILE);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}

		return true;
	}

	@Override
	public void inviaRapportino(RapportinoInviato rapportinoInviato) throws ServiceException {
		String msg = null;

		if ((msg = rapportinoValidator.validateRapportiniInviati(rapportinoInviato)) != null) {
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, msg);
		}
		try {
			rapportinoInviato.setCheckFreeze(false);
			rapportinoInviatoRepository.saveAndFlush(rapportinoInviato);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getCause());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateFreeze(RapportinoInviato rapportinoInviato) throws ServiceException {
		String msg = null;

		if ((msg = rapportinoValidator.validateFreeze(rapportinoInviato)) != null) {
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, msg);
		}

		try {
			RapportinoInviato currentRapportinoInviato = rapportinoInviatoRepository.findByCodiceFiscaleAnnoMese(
					rapportinoInviato.getCodiceFiscale(), rapportinoInviato.getAnno(), rapportinoInviato.getMese());
			currentRapportinoInviato.setCheckFreeze(rapportinoInviato.getCheckFreeze());
			rapportinoInviatoRepository.saveAndFlush(currentRapportinoInviato);
			if (rapportinoInviato.getCheckFreeze()) {
				RapportinoRequest rapportinoRequest = new RapportinoRequest(rapportinoInviato.getAnno(),
						rapportinoInviato.getMese(), rapportinoInviato.getCodiceFiscale());
				addRapportinoInDatabase(rapportinoRequest);
			} else {
				rapportinoRepository.deleteByMeseAndAnnoAndId(rapportinoInviato.getAnno(), rapportinoInviato.getMese(),
						anagraficaRepository.findByCodiceFiscale(rapportinoInviato.getCodiceFiscale()).getId());
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
	public List<AnagraficaDto> getRapportiniNotFreeze(AnagraficaFilterRequestDto anagraficaFilterRequestDto)
			throws ServiceException {

		List<AnagraficaDto> listAnagraficheUnion = new ArrayList<>();

		try {

			final List<RapportinoInviato> list = rapportinoInviatoRepository
					.getRapportiniNotFreeze(anagraficaFilterRequestDto.getMese(), anagraficaFilterRequestDto.getAnno());

			List<AnagraficaDto> listAnagraficheNotInList = null;

			List<AnagraficaDto> listAnagraficheInList = null;

			List<AnagraficaDto> listAnagrafiche = null;

			listAnagrafiche = anagraficaRepository.findAllactiveId().stream().map(mapper::toAnagraficaDto)
					.collect(Collectors.toList());

			listAnagraficheNotInList = new ArrayList<>();

			listAnagraficheInList = new ArrayList<>();

			listAnagraficheNotInList.addAll(listAnagrafiche);

			listAnagraficheInList.addAll(listAnagrafiche);

			listAnagraficheInList = listAnagraficheInList.stream().filter(elem -> filterCustom.checkInList(elem, list))
					.collect(Collectors.toList()).stream().map(elem -> mapper.setCheckInviato(elem, true))
					.collect(Collectors.toList()).stream()
					.map(elem -> mapper.setAnnoMese(elem, anagraficaFilterRequestDto)).collect(Collectors.toList());

			listAnagraficheNotInList = listAnagraficheNotInList.stream()
					.filter(elem -> filterCustom.checkNotInList(elem, list)).collect(Collectors.toList()).stream()
					.map(elem -> mapper.setCheckInviato(elem, false)).collect(Collectors.toList());

			listAnagraficheUnion.addAll(listAnagraficheInList);

			listAnagraficheUnion.addAll(listAnagraficheNotInList);

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return listAnagraficheUnion;
	}

	@Override
	public void addRapportinoInDatabase(RapportinoRequest rapportinoRequest) throws ServiceException {
		String msg = null;
		if ((msg = rapportinoValidator.validateFieldsForPath(rapportinoRequest)) != null) {
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, msg);
		}

		RapportinoDto rapportinoDto = new RapportinoDto();

		List<Rapportino> rapportini = new ArrayList<>();

		Anagrafica anagrafica = anagraficaRepository.findByCodiceFiscale(rapportinoRequest.getCodiceFiscale());

		try {
			rapportinoDto = fileUtil.readFile(PREFIX + DESTINAZIONE + rapportinoRequest.getCodiceFiscale() + RAPPORTINI
					+ rapportinoRequest.getAnno() + "/" + rapportinoRequest.getMese() + ".txt");

			for (GiornoDto giornoDto : rapportinoDto.getMese().getGiorni()) {
System.out.println("conteggio"+giornoDto.getNumeroGiorno());
				Rapportino rapportino = new Rapportino();

				if (giornoDto.getDuplicazioniGiornoDto() != null && giornoDto.getDuplicazioniGiornoDto().size() > 0) {

					if (giornoDto.getNumeroGiorno() != null)
						rapportino.setGiorno(giornoDto.getNumeroGiorno());

					Double sum = giornoDto.getDuplicazioniGiornoDto().stream()
							.filter(dto -> dto.getOreOrdinarie() != null)
							.mapToDouble(DuplicazioniGiornoDto::getOreOrdinarie).sum();

					rapportino.setOre(sum == Double.parseDouble("0") ? null : sum);

					rapportino.setFascia1(
							giornoDto.getDuplicazioniGiornoDto().stream().filter(dto -> dto.getFascia1() != null)
									.mapToDouble(DuplicazioniGiornoDto::getFascia1).sum());

					rapportino.setFascia2(
							giornoDto.getDuplicazioniGiornoDto().stream().filter(dto -> dto.getFascia2() != null)
									.mapToDouble(DuplicazioniGiornoDto::getFascia2).sum());

					rapportino.setFascia3(
							giornoDto.getDuplicazioniGiornoDto().stream().filter(dto -> dto.getFascia3() != null)
									.mapToDouble(DuplicazioniGiornoDto::getFascia3).sum());

					rapportino.setMese(rapportinoRequest.getMese());

					rapportino.setAnno(rapportinoRequest.getAnno());

					if (giornoDto.getFerie() != null)
						rapportino.setFerie(giornoDto.getFerie());

					if (giornoDto.getMalattie() != null)
						rapportino.setMalattie(giornoDto.getMalattie());

					if (giornoDto.getPermessi() != null)
						rapportino.setPermessi(giornoDto.getPermessi());

					rapportino.setAnagrafica(anagrafica);

					rapportini.add(rapportino);

				}
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
	public List<AnagraficaDto> getRapportiniFreezeFilter(AnagraficaFilterRequestDto anagraficaFilterRequestDto)
			throws ServiceException {

		List<AnagraficaDto> list = null;

		try {

			list = getRapportiniFreeze(anagraficaFilterRequestDto).stream()
					.filter(anagraficaDto -> filterCustom.toFilter(anagraficaDto,
							new AnagraficaRequestDto(anagraficaFilterRequestDto.getAnagraficaDto())))
					.collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<AnagraficaDto> getRapportiniNotFreezeFilter(AnagraficaFilterRequestDto anagraficaFilterRequestDto)
			throws ServiceException {

		List<AnagraficaDto> list = null;

		try {

			list = getRapportiniNotFreeze(anagraficaFilterRequestDto).stream()
					.filter(anagraficaDto -> filterCustom.toFilter(anagraficaDto,
							new AnagraficaRequestDto(anagraficaFilterRequestDto.getAnagraficaDto())))
					.collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<AnagraficaDto> getRapportiniFreeze(AnagraficaFilterRequestDto anagraficaFilterRequestDto)
			throws ServiceException {

		List<AnagraficaDto> listAnagrafiche = null;

		try {

			final List<RapportinoInviato> list = rapportinoInviatoRepository
					.getRapportiniFreeze(anagraficaFilterRequestDto.getMese(), anagraficaFilterRequestDto.getAnno());

			listAnagrafiche = anagraficaRepository.findAllactiveId().stream().map(mapper::toAnagraficaDto)
					.collect(Collectors.toList()).stream().filter(elem -> filterCustom.checkInList(elem, list))
					.collect(Collectors.toList());

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return listAnagrafiche;
	}
//prende tutti i rapportini da oggi a 3 mesi fa 
	@Override
	public String getRapportinoB64(Integer anno, Integer mese) throws ServiceException {
		int valAnno = anno.intValue();
		int valMese = mese.intValue();
		int rowNum = 0;
		try {
			List<Rapportino> rapportini = rapportinoRepository.findByMeseAndAnno(valAnno, valMese);
			rowNum = excelUtil.toExcel(rapportini, rowNum, false, PREFIX + EXCELPATH);

			rapportini = rapportinoRepository.findByMeseAndAnno(valAnno, ++valMese);
			if (!rapportini.isEmpty())
				rowNum = excelUtil.toExcel(rapportini, ++rowNum, true, PREFIX + EXCELPATH);

			rapportini = rapportinoRepository.findByMeseAndAnno(valAnno, ++valMese);
			if (!rapportini.isEmpty())
				rowNum = excelUtil.toExcel(rapportini, ++rowNum, true, PREFIX + EXCELPATH);

			return ExcelUtil.excelToBase64(PREFIX + EXCELPATH);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override
	public boolean getCheckRapportinoInviato(RapportinoRequest rapportinoRequest) throws ServiceException {
		try {

			List<Boolean> listCheckInviato = rapportinoInviatoRepository.checkInviato(
					rapportinoRequest.getCodiceFiscale(), rapportinoRequest.getAnno(), rapportinoRequest.getMese());

			Boolean check=false;

			if (listCheckInviato != null && listCheckInviato.size() > 0) {

				check = listCheckInviato.stream().filter(elem -> elem == true).collect(Collectors.toList()).size() > 0
						? true
						: false;

			} else {

				check = false;

			}

			return check;

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}
	
	
	


}

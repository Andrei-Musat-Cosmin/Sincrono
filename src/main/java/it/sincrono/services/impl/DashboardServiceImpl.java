package it.sincrono.services.impl;

import java.util.List;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import it.sincrono.services.utils.FilterCustom;
import it.sincrono.services.utils.MapperCustom;
import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.Profilo;
import it.sincrono.entities.Ruolo;
import it.sincrono.entities.StoricoCommesse;
import it.sincrono.entities.StoricoContratti;
import it.sincrono.entities.Utente;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.CommessaRepository;
import it.sincrono.repositories.ContrattoRepository;
import it.sincrono.repositories.DashboardRepository;
import it.sincrono.repositories.ProfiloRepository;
import it.sincrono.repositories.StoricoCommesseRepository;
import it.sincrono.repositories.StoricoContrattiRepository;
import it.sincrono.repositories.UtenteRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.services.AnagraficaService;
import it.sincrono.services.DashboardService;
import it.sincrono.services.EmailService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.utils.ObjectCompare;
import it.sincrono.services.utils.TokenGenerator;
import it.sincrono.services.validator.AnagraficaValidator;
import it.sincrono.services.validator.CommessaValidator;
import it.sincrono.services.validator.CommessaValidatorList;
import it.sincrono.services.validator.ContrattoValidator;
import it.sincrono.services.validator.RuoloValidator;
import jakarta.transaction.Transactional;

@Service
public class DashboardServiceImpl extends BaseServiceImpl implements DashboardService {

	@Autowired
	private DashboardRepository dashboardRepository;
	@Autowired
	private AnagraficaRepository anagraficaRepository;
	@Autowired
	private MapperCustom mapper;
	@Autowired
	private FilterCustom filter;

	@Override
	public List<AnagraficaDto> getCommesseInscadenza() throws ServiceException {

		List<AnagraficaDto> list = null;

		try {
			list = anagraficaRepository.findAllId().stream().map(mapper::toAnagraficaDto).collect(Collectors.toList()).stream()
					.filter(anagraficaDto -> filter.checkListCommesse(anagraficaDto.getCommesse().stream().filter(
							commessa ->filter.checkCommesseInScadenza(commessa)).collect(Collectors.toList())))
					.collect(Collectors.toList());
			
			
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<AnagraficaDto> getContrattiInscadenza() throws ServiceException {
		List<AnagraficaDto> list = null;

		try {
			list = anagraficaRepository.findAllId().stream().map(mapper::toAnagraficaDto).collect(Collectors.toList()).stream()
					.filter(anagraficaDto -> 
					filter.checkContrattiInScadenza(anagraficaDto.getContratto())).collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<AnagraficaDto> listCommesse(AnagraficaRequestDto anagraficaRequestDto) throws ServiceException {
		List<AnagraficaDto> list = null;

		try {
			list = this.listAllCommesse().stream().filter(
					anagraficaDto -> filter.toFilterCommesse(anagraficaDto, anagraficaRequestDto)).collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<AnagraficaDto> listAllCommesse() throws ServiceException {
		List<AnagraficaDto> list = null;

		try {
			list = anagraficaRepository.findAllId().stream().map(mapper::toAnagraficaDto).collect(Collectors.toList()).stream()
					.filter(anagraficaDto -> filter.checkListCommesse(anagraficaDto.getCommesse().stream().filter(
							commessa ->filter.checkScaduta(commessa)).collect(Collectors.toList())))
					.collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}
}

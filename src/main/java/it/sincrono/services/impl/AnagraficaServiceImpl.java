package it.sincrono.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.Profilo;
import it.sincrono.entities.Ruolo;
import it.sincrono.entities.StoricoCommesse;
import it.sincrono.entities.StoricoContratti;
import it.sincrono.entities.TipoCanaleReclutamento;
import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.TipoLivelloContratto;
import it.sincrono.entities.Utente;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.CommessaRepository;
import it.sincrono.repositories.ComuneRepository;
import it.sincrono.repositories.ContrattoRepository;
import it.sincrono.repositories.ProfiloRepository;
import it.sincrono.repositories.ProvinciaRepository;
import it.sincrono.repositories.StoricoCommesseRepository;
import it.sincrono.repositories.StoricoContrattiRepository;
import it.sincrono.repositories.TipologicheRepository;
import it.sincrono.repositories.UtenteRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.services.AnagraficaService;
import it.sincrono.services.EmailService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.utils.ExcelUtilAnagrafica;
import it.sincrono.services.utils.FileUtil;
import it.sincrono.services.utils.FilterCustom;
import it.sincrono.services.utils.MapperCustom;
import it.sincrono.services.utils.ObjectCompare;
import it.sincrono.services.utils.TokenGenerator;
import it.sincrono.services.validator.AnagraficaValidator;
import it.sincrono.services.validator.CommessaValidator;
import it.sincrono.services.validator.CommessaValidatorList;
import it.sincrono.services.validator.ContrattoValidator;
import it.sincrono.services.validator.RuoloValidator;
import jakarta.transaction.Transactional;

@Service
public class AnagraficaServiceImpl extends BaseServiceImpl implements AnagraficaService {

	@Value("${anagrafiche-profili.path-prefix}")
	private String PREFIX;

	@Value("${anagrafiche-profili.destinazione}")
	private String DESTINAZIONE;

	@Value("${anagrafiche-profili.anagrafiche-profili-rapportini.path-prefix-rapportini}")
	private String RAPPORTINI;

	private static final Logger LOGGER = LogManager.getLogger(AnagraficaServiceImpl.class);

	@Autowired
	private AnagraficaRepository anagraficaRepository;
	@Autowired
	private ContrattoRepository contrattoRepository;
	@Autowired
	private CommessaRepository commesseRepository;
	@Autowired
	private StoricoCommesseRepository storicoCommessaRepository;
	@Autowired
	private StoricoContrattiRepository storicoContrattiRepository;
	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private ProfiloRepository profiloRepository;
	@Autowired
	private TipologicheRepository tipologicheContrattoRepository;
	@Autowired
	private ComuneRepository comuneRepository;
	@Autowired
	private ProvinciaRepository provinciaRepository;
	@Autowired
	private EmailService emailService;

	@Autowired
	private AnagraficaValidator anagraficaValidator;

	@Autowired
	private ContrattoValidator contrattoValidator;

	@Autowired
	private CommessaValidator commessaValidator;

	@Autowired
	private RuoloValidator ruoloValidator;

	@Autowired
	private ObjectCompare objectCompare;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private CommessaValidatorList commessaValidatorList;

	@Autowired
	private MapperCustom mapper;

	@Autowired
	private FilterCustom filter;

	@Autowired
	FileUtil fileUtil;

	@Autowired
	ExcelUtilAnagrafica excelUtilAnagrafica;

	@Override
	public List<AnagraficaDto> filterListAnagraficaDto(AnagraficaRequestDto anagraficaRequestDto)
			throws ServiceException {

		List<AnagraficaDto> list = null;

		try {

			list = anagraficaRepository.findAllId().stream().map(mapper::toAnagraficaDto).collect(Collectors.toList())
					.stream().filter(anagraficaDto -> filter.toFilter(anagraficaDto, anagraficaRequestDto))
					.collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<AnagraficaDto> listAnagraficaDto() throws ServiceException {

		List<AnagraficaDto> list = null;

		try {
			list = anagraficaRepository.findAllactiveId().stream().map(mapper::toAnagraficaDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public AnagraficaDto getAnagraficaDto(Integer id) throws ServiceException {
		AnagraficaDto anagraficaDto = null;

		try {
			anagraficaDto = mapper.toAnagraficaDto(id);
			calcoloRalPartTime(anagraficaDto);
			calcoloTipoLivello(anagraficaDto);
		} catch (NoSuchElementException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return anagraficaDto;
	}

	@Override
	@Transactional(rollbackOn = ServiceException.class)
	public void insertAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException {

		try {
			if (!anagraficaValidator.validate(anagraficaDto.getAnagrafica(), true)) {
				throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di anagrafica");
			}

			anagraficaDto.getAnagrafica().setComuneDiNascita(anagraficaDto.getAnagrafica().getComuneDiNascita());
			anagraficaDto.getAnagrafica().setComuneResidenza(anagraficaDto.getAnagrafica().getComuneResidenza());
			anagraficaDto.getAnagrafica().setComuneDomicilio(anagraficaDto.getAnagrafica().getComuneDomicilio());
			anagraficaDto.getAnagrafica().setProvinciaDiNascita(anagraficaDto.getAnagrafica().getProvinciaDiNascita());
			anagraficaDto.getAnagrafica().setProvinciaResidenza(anagraficaDto.getAnagrafica().getProvinciaResidenza());
			anagraficaDto.getAnagrafica().setProvinciaDomicilio(anagraficaDto.getAnagrafica().getProvinciaDomicilio());
			String passwordUtente = new TokenGenerator().nextToken();
			Utente utente = new Utente(anagraficaDto.getAnagrafica().getMailAziendale(), true,
					BCrypt.hashpw(passwordUtente, BCrypt.gensalt()));
			anagraficaDto.getAnagrafica().setUtente(utente);
			Integer idUtente = utenteRepository.saveAndFlush(anagraficaDto.getAnagrafica().getUtente()).getId();
			anagraficaDto.getAnagrafica().getUtente().setId(idUtente);
			anagraficaDto.getAnagrafica().setAttivo(true);

			Integer idAnagrafica = anagraficaRepository.saveAndFlush(anagraficaDto.getAnagrafica()).getId();
			storicoCommessaRepository.saveAndFlush(new StoricoCommesse(new Anagrafica(idAnagrafica), new Commessa(0)));
			storicoContrattiRepository
					.saveAndFlush(new StoricoContratti(new Anagrafica(idAnagrafica), new Contratto(0)));

			if (anagraficaDto.getRuolo() != null) {

				if (!ruoloValidator.validate(anagraficaDto.getRuolo(), false)) {
					throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di ruolo");
				}

				profiloRepository
						.saveAndFlush(new Profilo(new Ruolo(anagraficaDto.getRuolo().getId()), new Utente(idUtente)));
			} else {

				profiloRepository.saveAndFlush(new Profilo(new Ruolo(3), new Utente(idUtente)));

			}

			if (anagraficaDto.getCommesse() != null && anagraficaDto.getCommesse().size() != 0) {

				if (!commessaValidatorList.validate(anagraficaDto.getCommesse(), false, true)) {
					throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di commesse");
				}

				for (Commessa commessa : anagraficaDto.getCommesse()) {

					commessa.setAttivo(true);
					Integer idCommessa = commesseRepository.saveAndFlush(commessa).getId();
					storicoCommessaRepository
							.saveAndFlush(new StoricoCommesse(new Anagrafica(idAnagrafica), new Commessa(idCommessa)));

				}
			}

			if (anagraficaDto.getContratto() != null) {
				if (!contrattoValidator.validate(anagraficaDto.getContratto(), true)) {
					throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di contratto");
				}

				CalcoloDataFineRapporto(anagraficaDto, true);
				CalcoloTipoCcnl(anagraficaDto);
				calcoloTipoLivello(anagraficaDto);

				anagraficaDto.getContratto()
						.setTipoAzienda(anagraficaDto.getAnagrafica().getTipoAzienda() != null
								? anagraficaDto.getAnagrafica().getTipoAzienda()
								: null);
				anagraficaDto.getContratto().setAttivo(true);
				Integer idContratto = contrattoRepository.saveAndFlush(anagraficaDto.getContratto()).getId();
				storicoContrattiRepository
						.saveAndFlush(new StoricoContratti(new Anagrafica(idAnagrafica), new Contratto(idContratto)));
			}

			LocalDate oggi = LocalDate.now();

			fileUtil.creatFolder(PREFIX + DESTINAZIONE + anagraficaDto.getAnagrafica().getCodiceFiscale() + RAPPORTINI
					+ oggi.getYear() + "/" + oggi.getMonthValue() + ".txt");

			emailService.sendMail(null, anagraficaDto.getAnagrafica().getMailAziendale(), null, "CREAZIONE UTENZA",
					"username: " + anagraficaDto.getAnagrafica().getUtente().getUsername() + "\n" + "password: "
							+ passwordUtente);

			LOGGER.log(Level.INFO, "Password " + passwordUtente);

		} catch (DataIntegrityViolationException e) {
			LOGGER.log(Level.ERROR, ServiceMessages.ERRORE_INTEGRITA_DATI);
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, ServiceMessages.ERRORE_VALIDAZIONE);
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	@Transactional(rollbackOn = ServiceException.class)
	public void updateAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException {

		// TransactionStatus status = null;

		try {

			// status = transactionManager.getTransaction(new
			// DefaultTransactionDefinition());

			if (!anagraficaValidator.validate(anagraficaDto.getAnagrafica(), false)) {
				throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di anagrafica");
			}
			anagraficaDto.getAnagrafica().setComuneDiNascita(anagraficaDto.getAnagrafica().getComuneDiNascita());
			anagraficaDto.getAnagrafica().setComuneResidenza(anagraficaDto.getAnagrafica().getComuneResidenza());
			anagraficaDto.getAnagrafica().setComuneDomicilio(anagraficaDto.getAnagrafica().getComuneDomicilio());
			anagraficaDto.getAnagrafica().setProvinciaDiNascita(anagraficaDto.getAnagrafica().getProvinciaDiNascita());
			anagraficaDto.getAnagrafica().setProvinciaResidenza(anagraficaDto.getAnagrafica().getProvinciaResidenza());
			anagraficaDto.getAnagrafica().setProvinciaDomicilio(anagraficaDto.getAnagrafica().getProvinciaDomicilio());
			anagraficaDto.getAnagrafica().setAttivo(true);
			anagraficaDto.getAnagrafica()
					.setUtente(anagraficaRepository.findById(anagraficaDto.getAnagrafica().getId()).get().getUtente());
			anagraficaRepository.saveAndFlush(anagraficaDto.getAnagrafica());
			Integer idAnagrafica = anagraficaDto.getAnagrafica().getId();

			if (anagraficaDto.getRuolo() != null) {

				if (!ruoloValidator.validate(anagraficaDto.getRuolo(), false)) {
					throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di ruolo");
				}

				Integer idProfilo = profiloRepository.getidProfilo(idAnagrafica);
				profiloRepository.saveAndFlush(
						new Profilo(idProfilo, anagraficaDto.getRuolo(), anagraficaDto.getAnagrafica().getUtente()));

			}

			if (anagraficaDto.getCommesse() != null && anagraficaDto.getCommesse().size() != 0) {

				if (!commessaValidatorList.validate(anagraficaDto.getCommesse(), true, false)) {
					throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di commesse");
				}

				for (Commessa commessa : anagraficaDto.getCommesse()) {

					if (commessa.getId() != null) {

						Commessa commessaDb = commesseRepository.findById(commessa.getId()).get();
						if (!objectCompare.Compare(commessa, commessaDb)) {
							commessa.setId(null);
							commessa.setAttivo(true);
							Integer idCommessa = commesseRepository.saveAndFlush(commessa).getId();
							storicoCommessaRepository.saveAndFlush(
									new StoricoCommesse(new Anagrafica(idAnagrafica), new Commessa(idCommessa)));
							if (commessaDb.getId() != 0)
								commessaDb.setAttivo(false);
							commesseRepository.saveAndFlush(commessaDb);
						}

					} else {

						commessa.setAttivo(true);
						Integer idCommessa = commesseRepository.saveAndFlush(commessa).getId();
						storicoCommessaRepository.saveAndFlush(
								new StoricoCommesse(new Anagrafica(idAnagrafica), new Commessa(idCommessa)));

					}
				}

			}

			if (anagraficaDto.getContratto() != null) {

				if (!contrattoValidator.validateUpdate(anagraficaDto.getContratto())) {
					throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE, " per i dati di contratto");
				}

				if (anagraficaDto.getContratto().getId() != null) {

					Contratto contratto = contrattoRepository.findById(anagraficaDto.getContratto().getId()).get();
					if (!objectCompare.Compare(anagraficaDto.getContratto(), contratto)) {

						if (anagraficaDto.getContratto().getTipoCausaFineRapporto() == null) {

							CalcoloDataFineRapporto(anagraficaDto, true);

						}
						anagraficaDto.getContratto()
								.setTipoAzienda(anagraficaDto.getAnagrafica().getTipoAzienda() != null
										? anagraficaDto.getAnagrafica().getTipoAzienda()
										: null);
						CalcoloTipoCcnl(anagraficaDto);
						calcoloTipoLivello(anagraficaDto);
						anagraficaDto.getContratto().setId(null);
						anagraficaDto.getContratto().setAttivo(true);
						Integer idContratto = contrattoRepository.saveAndFlush(anagraficaDto.getContratto()).getId();
						storicoContrattiRepository.saveAndFlush(
								new StoricoContratti(new Anagrafica(idAnagrafica), new Contratto(idContratto)));
						if (contratto.getId() != 0)
							contratto.setAttivo(false);
						contrattoRepository.saveAndFlush(contratto);
					}

					if (anagraficaDto.getContratto().getTipoCausaFineRapporto() != null) {

						CalcoloDataFineRapporto(anagraficaDto, false);

						deleteAnagraficaDto(anagraficaDto);

						return;

					}

				} else {

					CalcoloDataFineRapporto(anagraficaDto, true);
					anagraficaDto.getContratto()
							.setTipoAzienda(anagraficaDto.getAnagrafica().getTipoAzienda() != null
									? anagraficaDto.getAnagrafica().getTipoAzienda()
									: null);
					CalcoloTipoCcnl(anagraficaDto);
					anagraficaDto.getContratto().setAttivo(true);
					Integer idContratto = contrattoRepository.saveAndFlush(anagraficaDto.getContratto()).getId();
					storicoContrattiRepository.saveAndFlush(
							new StoricoContratti(new Anagrafica(idAnagrafica), new Contratto(idContratto)));

				}

			}

			// transactionManager.commit(status);

		} catch (DataIntegrityViolationException e) {
			/*
			 * if (status != null) { transactionManager.rollback(status); }
			 */
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);

		} catch (Exception e) {
			/*
			 * if (status != null) { transactionManager.rollback(status); }
			 */
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override
	@Transactional(rollbackOn = ServiceException.class)
	public void deleteAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException {

		try {

			Anagrafica anagrafica = anagraficaRepository.findById(anagraficaDto.getAnagrafica().getId()).get();
			anagrafica.setAttivo(false);
			anagraficaRepository.saveAndFlush(anagrafica);

			if (anagraficaDto.getCommesse() != null && anagraficaDto.getCommesse().size() != 0) {

				for (Commessa commessa : anagraficaDto.getCommesse()) {

					if (commessa.getId() != 0) {

						Commessa commessaDb = commesseRepository.findById(commessa.getId()).get();
						commessaDb.setAttivo(false);
						commesseRepository.saveAndFlush(commessaDb);

					}

				}

			}

			if (anagraficaDto.getContratto().getId() != 0 && anagraficaDto.getContratto() != null) {

				Contratto contratto = contrattoRepository.findById(anagraficaDto.getContratto().getId()).get();
				contratto.setAttivo(false);
				contrattoRepository.saveAndFlush(contratto);

			}

			Utente utente = utenteRepository.findById(anagrafica.getUtente().getId()).get();
			utente.setAttivo(false);
			utenteRepository.saveAndFlush(utente);

		} catch (NoSuchElementException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override
	public List<AnagraficaDto> anagraficaListContratti() throws ServiceException {
		List<AnagraficaDto> list = null;

		try {
			list = anagraficaRepository.findAnagraficaByContrattoId(anagraficaRepository.getIdContrattiScattiLivello())
					.stream().map(mapper::toAnagraficaDto).collect(Collectors.toList());

		} catch (Exception e) {
			System.out.println("Exception occurs { ERRORE_GENERICO }");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public void deleteScattoContratti() throws ServiceException {

		try {
			anagraficaRepository.deleteScattoContratti();
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override
	public AnagraficaDto getAnagraficaDtoByToken(String token) throws ServiceException {
		AnagraficaDto anagraficaDto = null;

		try {
			anagraficaDto = mapper.toAnagraficaDto(token);
		} catch (NoSuchElementException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return anagraficaDto;
	}

	@Override
	@Transactional(rollbackOn = ServiceException.class)
	public void retainAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException {

		try {

			Anagrafica anagrafica = anagraficaRepository.findById(anagraficaDto.getAnagrafica().getId()).get();
			anagrafica.setAttivo(true);
			anagraficaRepository.saveAndFlush(anagrafica);

			if (anagraficaDto.getContratto().getId() != 0) {

				Contratto contratto = contrattoRepository.findById(anagraficaDto.getContratto().getId()).get();
				contratto.setAttivo(true);
				contrattoRepository.saveAndFlush(contratto);

			}

			Utente utente = utenteRepository.findById(anagrafica.getUtente().getId()).get();
			utente.setAttivo(true);
			utenteRepository.saveAndFlush(utente);

		} catch (NoSuchElementException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	private void CalcoloTipoCcnl(AnagraficaDto anagraficaDto) throws Exception {

		Contratto contratto = anagraficaDto.getContratto();

		if (contratto.getTipoContratto().getId() != 1) {

			TipoCcnl tipoCcnl = tipologicheContrattoRepository.getCcnlMapById(contratto.getTipoCcnl().getId());

			if (contratto.getRetribuzioneMensileLorda() != null) {

				Double superMinimoMensile = contratto.getSuperminimoMensile() == null ? 0
						: contratto.getSuperminimoMensile();

				Double scattiAnzianita = contratto.getScattiAnzianita() == null ? 0 : contratto.getScattiAnzianita();

				contratto.setRalAnnua((contratto.getRetribuzioneMensileLorda() + superMinimoMensile + scattiAnzianita)
						* tipoCcnl.getNumeroMensilita());
			}

		}

		if (contratto.getDiariaGiornaliera() != null) {
			contratto.setDiariaAnnua(contratto.getDiariaGiornaliera() * 20 * 12);
		} else if (contratto.getDiariaMensile() != null) {
			contratto.setDiariaAnnua(contratto.getDiariaMensile() * 12);
		}

		anagraficaDto.setContratto(contratto);
	}

	private void calcoloRalPartTime(AnagraficaDto anagraficaDto) throws Exception {

		Contratto contratto = anagraficaDto.getContratto();

		if (contratto != null) {

			if (contratto.getPercentualePartTime() != null && contratto.getRalAnnua() != null) {
				contratto.setRalPartTime((contratto.getPercentualePartTime() / 100) * contratto.getRalAnnua());
			}

		}

		anagraficaDto.setContratto(contratto);
	}

	private void CalcoloDataFineRapporto(AnagraficaDto anagraficaDto, boolean check) throws Exception {

		Calendar calendar = Calendar.getInstance();

		if (check) {

			if (anagraficaDto.getContratto().getDataAssunzione() != null
					&& anagraficaDto.getContratto().getMesiDurata() != null
					&& anagraficaDto.getContratto().getDataFineContratto() == null) {

				calendar.setTime(anagraficaDto.getContratto().getDataAssunzione());
				calendar.add(Calendar.MONTH, anagraficaDto.getContratto().getMesiDurata());
				anagraficaDto.getContratto().setDataFineContratto(calendar.getTime());

			}

		} else {

			if (anagraficaDto.getContratto().getDataFineContratto() == null) {

				anagraficaDto.getContratto().setDataFineContratto(calendar.getTime());

			}
		}

	}

	private void calcoloTipoLivello(AnagraficaDto anagraficaDto) throws Exception {

		if (anagraficaDto.getContratto() != null) {

			List<TipoLivelloContratto> listLivelli = tipologicheContrattoRepository.getTipoLivelloContrattoMap();

			anagraficaDto.getContratto().setLivelloAttuale(listLivelli.stream().filter(
					livello -> livello.getId() == anagraficaDto.getContratto().getTipoLivelloContratto().getId())
					.toList().get(0).getLivello());

		}
	}

	@Override
	@Transactional(rollbackOn = ServiceException.class)
	public List<AnagraficaDto> insertAnagraficaDtoExcel(String base64) throws ServiceException {

		try {

			List<AnagraficaDto> listAnagrafiche = excelUtilAnagrafica.createAnagraficaDtoExcel(base64);
			List<AnagraficaDto> listAnagraficheNotInsert = new ArrayList<>();

			// List<AnagraficaDto> listAnagraficheInDatabase = listAnagraficaDto();

			TipoCanaleReclutamento tipoCanaleReclutamento = new TipoCanaleReclutamento();

			tipoCanaleReclutamento.setId(1);

			// Boolean checkIsInDatabase = false;

			for (AnagraficaDto anagraficaDto : listAnagrafiche) {

				/*
				 * checkIsInDatabase = listAnagraficheInDatabase.stream() .filter(elem ->
				 * elem.getAnagrafica().getCodiceFiscale()
				 * .equals(anagraficaDto.getAnagrafica().getCodiceFiscale()))
				 * .collect(Collectors.toList()).size() > 0 ? true : false;
				 */

				anagraficaDto.getAnagrafica().setTipoCanaleReclutamento(tipoCanaleReclutamento);

				if (anagraficaValidator.validate(anagraficaDto.getAnagrafica(), true)
						&& contrattoValidator.validate(anagraficaDto.getContratto(), true)
						&& commessaValidatorList.validate(anagraficaDto.getCommesse(), false, true)
				/* && !checkIsInDatabase */) {

					insertAnagraficaDto(anagraficaDto);

				} else {

					listAnagraficheNotInsert.add(anagraficaDto);

				}

			}

			/*
			 * List<AnagraficaDto> listAnagraficheNotInsert = new ArrayList<>();
			 * 
			 * Boolean check = false;
			 * 
			 * for (AnagraficaDto anagraficaDtoExcel : listAnagrafiche) {
			 * 
			 * for (AnagraficaDto anagraficaDto : listAnagraficheInDatabase) {
			 * 
			 * if (anagraficaDtoExcel.getAnagrafica().getCodiceFiscale()
			 * .equals(anagraficaDto.getAnagrafica().getCodiceFiscale())) {
			 * 
			 * check = true;
			 * 
			 * }
			 * 
			 * }
			 * 
			 * if (!check) listAnagraficheNotInsert.add(anagraficaDtoExcel);
			 * 
			 * check=false;
			 * 
			 * }
			 */

			return listAnagraficheNotInsert;

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e);
		}

	}

	/*
	 * 
	 * List<AnagraficaDto> list = new ArrayList<>(); try { List<Anagrafica>
	 * listaAnagrafiche = anagraficaRepository.getAllActive(); for (Anagrafica
	 * currentAnagrafica : listaAnagrafiche) {
	 * 
	 * List<Commessa> listaCommesse= new ArrayList<>(); AnagraficaDto
	 * currentAnagraficaDto = new AnagraficaDto();
	 * currentAnagraficaDto.setAnagrafica(currentAnagrafica);
	 * currentAnagraficaDto.setContratto(contrattoRepository.getById(
	 * currentAnagrafica.getId()));
	 * 
	 * for() {
	 * 
	 * listaCommesse.add(commessaRepository.getById()); } list.add(anagraficaDto); }
	 * ServiceException(ServiceMessages.ERRORE_GENERICO); }
	 * 
	 * return list;
	 */
	/*
	 * @Override public void updateAnagraficaDto(AnagraficaDto anagraficaDto) throws
	 * ServiceException { if
	 * (!anagraficaValidator.validate(anagraficaDto.getAnagrafica(), false)) {
	 * ServiceException(ServiceMessages.ERRORE_VALIDAZIONE); } if
	 * (!commessaValidator.validate(anagraficaDto.getCommessa(), false)) {
	 * ServiceException(ServiceMessages.ERRORE_VALIDAZIONE); } if
	 * (!contrattoValidator.validate(anagraficaDto.getContratto(), false)) {
	 * ServiceException(ServiceMessages.ERRORE_VALIDAZIONE); }
	 * 
	 * try {
	 * anagraficaRepository.findById(anagraficaDto.getAnagrafica().getId()).get();
	 * anagraficaRepository.saveAndFlush(anagraficaDto.getAnagrafica());
	 * contrattoRepository.findById(anagraficaDto.getContratto().getId()).get();
	 * contrattoRepository.saveAndFlush(anagraficaDto.getContratto());
	 * commesseRepository.findById(anagraficaDto.getCommessa().getId()).get();
	 * commesseRepository.saveAndFlush(anagraficaDto.getCommessa());
	 * 
	 * } catch (NoSuchElementException ne) {
	 * ServiceException(ServiceMessages.RECORD_NON_TROVATO); } catch
	 * (DataIntegrityViolationException de) {
	 * ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI); } catch (Exception
	 * ServiceException(ServiceMessages.ERRORE_GENERICO); }
	 * 
	 * }
	 */
//	@Override
//	public List<Anagrafica> list() throws ServiceException {
//
//		List<Anagrafica> list = null;
//
//		try {
//			list = anagraficaRepository.findAll();
//		} catch (Exception e) {
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//
//		return list;
//	}
//
//	@Override
//	public Anagrafica getById(Integer ID) throws ServiceException {
//		Anagrafica anagrafica = null;
//
//		try {
//			anagrafica = anagraficaRepository.findById(ID).get();
//		} catch (NoSuchElementException ne) {
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (Exception e) {
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//
//		return anagrafica;
//	}
//
//	@Override
//	public void insert(Anagrafica anagrafica) throws ServiceException {
//
//		if (!anagraficaValidator.validate(anagrafica, true)) {
//			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
//		}
//
//		try {
//			anagraficaRepository.saveAndFlush(anagrafica);
//		} catch (DataIntegrityViolationException de) {
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch (Exception e) {
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//
//	}
//
//	@Override
//	public void update(Anagrafica anagrafica) throws ServiceException {
//		if (!anagraficaValidator.validate(anagrafica, false)) {
//			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
//		}
//
//		try {
//			Anagrafica Currentanagrafica = anagraficaRepository.findById(anagrafica.getId()).get();
////			Currentanagrafica.setId(anagrafica.getId());
//			Currentanagrafica.setAltriTitoli(anagrafica.getAltriTitoli());
//			Currentanagrafica.setCellulareAziendale(anagrafica.getCellulareAziendale());
//			Currentanagrafica.setCellularePrivato(anagrafica.getCellularePrivato());
//			Currentanagrafica.setCodiceFiscale(anagrafica.getCodiceFiscale());
//			Currentanagrafica.setCognome(anagrafica.getCognome());
//			Currentanagrafica.setComuneDiNascita(anagrafica.getComuneDiNascita());
//			Currentanagrafica.setConiugato(anagrafica.getConiugato());
//			Currentanagrafica.setDataDiNascita(anagrafica.getDataDiNascita());
//			Currentanagrafica.setDomicilio(anagrafica.getDomicilio());
//			Currentanagrafica.setFigliACarico(anagrafica.getFigliACarico());
//			Currentanagrafica.setMailAziendale(anagrafica.getMailAziendale());
//			Currentanagrafica.setMailPec(anagrafica.getMailPec());
//			Currentanagrafica.setMailPrivata(anagrafica.getMailPrivata());
//			Currentanagrafica.setNome(anagrafica.getNome());
//			Currentanagrafica.setResidenza(anagrafica.getResidenza());
//			Currentanagrafica.setTitoliDiStudio(anagrafica.getTitoliDiStudio());
//
//			anagraficaRepository.saveAndFlush(Currentanagrafica);
//
//		} catch (NoSuchElementException ne) {
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (DataIntegrityViolationException de) {
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch (Exception e) {
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//
//	}
//
//	@Override
//	public void delete(Integer ID) throws ServiceException {
//		try {
//			Anagrafica anagrafica = anagraficaRepository.findById(ID).get();
//
//			anagraficaRepository.delete(anagrafica);
//			anagraficaRepository.flush();
//
//		} catch (NoSuchElementException ne) {
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (DataIntegrityViolationException de) {
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch (Exception e) {
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//	}
}

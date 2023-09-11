package it.sincrono.services.impl;

import java.util.List;

import java.util.NoSuchElementException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.Utente;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.CommessaRepository;
import it.sincrono.repositories.ContrattoRepository;
import it.sincrono.repositories.ProfiloRepository;
import it.sincrono.repositories.StoricoCommesseRepository;
import it.sincrono.repositories.StoricoContrattiRepository;
import it.sincrono.repositories.TipologicheContrattoRepository;
import it.sincrono.repositories.UtenteRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import it.sincrono.services.AnagraficaService;
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
public class AnagraficaServiceImpl extends BaseServiceImpl implements AnagraficaService {

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
	private TipologicheContrattoRepository TipologicheContrattoRepository;

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

//	@Override
//	public List<Anagrafica> list() throws ServiceException {
//
//		List<Anagrafica> list = null;
//
//		try {
//			list = anagraficaRepository.findAll();
//		} catch (Exception e) {
//			System.out.println("Exception occurs {}");
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
//			System.out.println("Exception occurs {}, ID {}");
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (Exception e) {
//			System.out.println("Exception occurs {}");
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
//			System.out.println("Exception occurs {}");
//			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
//		}
//
//		try {
//			anagraficaRepository.saveAndFlush(anagrafica);
//		} catch (DataIntegrityViolationException de) {
//			System.out.println("Exception occurs {}");
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch (Exception e) {
//			System.out.println("Exception occurs {}");
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//
//	}
//
//	@Override
//	public void update(Anagrafica anagrafica) throws ServiceException {
//		if (!anagraficaValidator.validate(anagrafica, false)) {
//			System.out.println("Exception occurs {}");
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
//			System.out.println("Exception occurs {}");
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (DataIntegrityViolationException de) {
//			System.out.println("Exception occurs {}");
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch (Exception e) {
//			System.out.println("Exception occurs {}");
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
//			System.out.println("Exception occurs {}, id {}");
//			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
//		} catch (DataIntegrityViolationException de) {
//			System.out.println("Exception occurs {}");
//			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
//		} catch (Exception e) {
//			System.out.println("Exception occurs {}");
//			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
//		}
//	}

	@Override
	public List<AnagraficaDto> filterListAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException {

		List<AnagraficaDto> list = null;

		try {
			list = anagraficaRepository.filterListAnagraficaDto(anagraficaDto);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
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
	 * } catch (Exception e) { System.out.println("Exception occurs {}"); throw new
	 * ServiceException(ServiceMessages.ERRORE_GENERICO); }
	 * 
	 * return list;
	 */
	@Override
	public List<AnagraficaDto> listAnagraficaDto() throws ServiceException {

		List<AnagraficaDto> list = null;

		try {
			list = anagraficaRepository.listAnagraficaDto();
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public AnagraficaDto getAnagraficaDto(Integer id) throws ServiceException {
		AnagraficaDto anagraficaDto = null;

		try {
			anagraficaDto = anagraficaRepository.getAnagraficaDto(id);
		} catch (NoSuchElementException ne) {
			System.out.println("Exception occurs {}, ID {}");
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return anagraficaDto;
	}

	@Override
	@Transactional(rollbackOn = ServiceException.class)
	public void insertAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException {

		try {
			if (!anagraficaValidator.validate(anagraficaDto.getAnagrafica(), true)) {
				System.out.println("Exception occurs {}");
				throw new ServiceException();
			}

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
					System.out.println("Exception occurs {}");
					throw new ServiceException();
				}

				profiloRepository
						.saveAndFlush(new Profilo(new Ruolo(anagraficaDto.getRuolo().getId()), new Utente(idUtente)));
			} else {

				profiloRepository.saveAndFlush(new Profilo(new Ruolo(2), new Utente(idUtente)));

			}

			if (anagraficaDto.getCommesse() != null) {

				if (!commessaValidatorList.validate(anagraficaDto.getCommesse(), false, true)) {
					System.out.println("Exception occurs {}");
					throw new ServiceException();
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
					System.out.println("Exception occurs {}");
					throw new ServiceException();
				}
				CalcoloTipoCcnl(anagraficaDto);
				anagraficaDto.getContratto().setAttivo(true);
				Integer idContratto = contrattoRepository.saveAndFlush(anagraficaDto.getContratto()).getId();
				storicoContrattiRepository
						.saveAndFlush(new StoricoContratti(new Anagrafica(idAnagrafica), new Contratto(idContratto)));
			}

//			emailService.sendMail(null, anagraficaDto.getAnagrafica().getMailAziendale(), null,"CREAZIONE UTENZA","username:"+
//					anagraficaDto.getAnagrafica().getUtente().getUsername()+"\n"+
//					"password:"+passwordUtente);

			System.out.println("Password " + passwordUtente);

		} catch (DataIntegrityViolationException de) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (ServiceException e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	/*
	 * @Override public void updateAnagraficaDto(AnagraficaDto anagraficaDto) throws
	 * ServiceException { if
	 * (!anagraficaValidator.validate(anagraficaDto.getAnagrafica(), false)) {
	 * System.out.println("Exception occurs {}"); throw new
	 * ServiceException(ServiceMessages.ERRORE_VALIDAZIONE); } if
	 * (!commessaValidator.validate(anagraficaDto.getCommessa(), false)) {
	 * System.out.println("Exception occurs {}"); throw new
	 * ServiceException(ServiceMessages.ERRORE_VALIDAZIONE); } if
	 * (!contrattoValidator.validate(anagraficaDto.getContratto(), false)) {
	 * System.out.println("Exception occurs {}"); throw new
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
	 * System.out.println("Exception occurs {}"); throw new
	 * ServiceException(ServiceMessages.RECORD_NON_TROVATO); } catch
	 * (DataIntegrityViolationException de) {
	 * System.out.println("Exception occurs {}"); throw new
	 * ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI); } catch (Exception
	 * e) { System.out.println("Exception occurs {}"); throw new
	 * ServiceException(ServiceMessages.ERRORE_GENERICO); }
	 * 
	 * }
	 */

	@Override
	@Transactional(rollbackOn = ServiceException.class)
	public void updateAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException {

		// TransactionStatus status = null;

		try {

			if (!anagraficaValidator.validate(anagraficaDto.getAnagrafica(), false)) {
				System.out.println("Exception occurs {}");
				throw new ServiceException();
			}
			if (!commessaValidatorList.validate(anagraficaDto.getCommesse(), true, false)) {
				System.out.println("Exception occurs {}");
				throw new ServiceException();
			}

			if (!contrattoValidator.validate(anagraficaDto.getContratto(), false)) {
				System.out.println("Exception occurs {}");
				throw new ServiceException();
			}

			// status = transactionManager.getTransaction(new
			// DefaultTransactionDefinition());
			if(anagraficaDto.getContratto().getTipoCausaFineRapporto()==null) {

				anagraficaDto.getAnagrafica().setAttivo(true);
				anagraficaDto.getAnagrafica()
						.setUtente(anagraficaRepository.findById(anagraficaDto.getAnagrafica().getId()).get().getUtente());
				anagraficaRepository.saveAndFlush(anagraficaDto.getAnagrafica());
				Integer idAnagrafica = anagraficaDto.getAnagrafica().getId();
			
				if (anagraficaDto.getRuolo() != null) {
			
					if (!ruoloValidator.validate(anagraficaDto.getRuolo(), false)) {
						System.out.println("Exception occurs {}");
						throw new ServiceException();
					}
			
					Integer idProfilo = profiloRepository.getidProfilo(idAnagrafica);
					profiloRepository.saveAndFlush(
							new Profilo(idProfilo, anagraficaDto.getRuolo(), anagraficaDto.getAnagrafica().getUtente()));
			
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
							if(commessaDb.getId()!=0)commessaDb.setAttivo(false);
							commesseRepository.saveAndFlush(commessaDb);
						}
			
					} else {
			
						Integer idCommessa = commesseRepository.saveAndFlush(commessa).getId();
						storicoCommessaRepository
								.saveAndFlush(new StoricoCommesse(new Anagrafica(idAnagrafica), new Commessa(idCommessa)));
			
					}
				}
			
				Contratto contratto = contrattoRepository.findById(anagraficaDto.getContratto().getId()).get();
				if (!objectCompare.Compare(anagraficaDto.getContratto(), contratto)) {
					CalcoloTipoCcnl(anagraficaDto);
					anagraficaDto.getContratto().setId(null);
					anagraficaDto.getContratto().setAttivo(true);
					Integer idContratto = contrattoRepository.saveAndFlush(anagraficaDto.getContratto()).getId();
					contrattoRepository.saveAndFlush(anagraficaDto.getContratto());
					storicoContrattiRepository
							.saveAndFlush(new StoricoContratti(new Anagrafica(idAnagrafica), new Contratto(idContratto)));
					if(contratto.getId()!=0)contratto.setAttivo(false);
					contrattoRepository.saveAndFlush(contratto);
				}
				
				
				
			}else {
				
				anagraficaDto.getContratto().setDimissioni(true);
				deleteAnagraficaDto(anagraficaDto);
				
			}

			// transactionManager.commit(status);

		} catch (DataIntegrityViolationException de) {
			/*
			 * if (status != null) { transactionManager.rollback(status); }
			 */
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (ServiceException e) {
			/*
			 * if (status != null) { transactionManager.rollback(status); }
			 */
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		} catch (Exception e) {
			/*
			 * if (status != null) { transactionManager.rollback(status); }
			 */
			System.out.println("Exception occurs {}");
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

			for (Commessa commessa : anagraficaDto.getCommesse()) {

				if (commessa.getId() != 0) {

					Commessa commessaDb = commesseRepository.findById(commessa.getId()).get();
					commessaDb.setAttivo(false);
					commesseRepository.saveAndFlush(commessaDb);

				}

			}

			if (anagraficaDto.getContratto().getId() != 0) {

				Contratto contratto = contrattoRepository.findById(anagraficaDto.getContratto().getId()).get();
				contratto.setAttivo(false);
				contrattoRepository.saveAndFlush(contratto);

			}

			Utente utente = utenteRepository.findById(anagrafica.getUtente().getId()).get();
			utente.setAttivo(false);
			utenteRepository.saveAndFlush(utente);

		} catch (NoSuchElementException ne) {
			System.out.println("Exception occurs {}, id {}");
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (DataIntegrityViolationException de) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override
	public List<AnagraficaDto> anagraficaListContratti() throws ServiceException {
		List<AnagraficaDto> list = null;

		try {
			list = anagraficaRepository.listAnagraficaDtoContratti();
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public void deleteScattoContratti() throws ServiceException {

		try {
			anagraficaRepository.deleteScattoContratti();
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override
	public AnagraficaDto getAnagraficaDtoByToken(String token) throws ServiceException {
		AnagraficaDto anagraficaDto = null;

		try {
			anagraficaDto = anagraficaRepository.getAnagraficaDtoByToken(token);
		} catch (NoSuchElementException ne) {
			System.out.println("Exception occurs {}, ID {}");
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return anagraficaDto;
	}

	private void CalcoloTipoCcnl(AnagraficaDto anagraficaDto) throws Exception {
		
		
		Contratto contratto = anagraficaDto.getContratto();

		if ((contratto.getRetribuzioneMensileLorda() != null && contratto.getRalAnnua() == null)
				|| (contratto.getRetribuzioneMensileLorda() == null && contratto.getRalAnnua() != null)) {

			TipoCcnl tipoCcnl = TipologicheContrattoRepository.getCcnlMapById(contratto.getTipoCcnl().getId());

			if (contratto.getRetribuzioneMensileLorda() != null) {
				contratto.setRalAnnua(contratto.getRetribuzioneMensileLorda() * tipoCcnl.getNumeroMensilita());
			} else {
				contratto.setRetribuzioneMensileLorda(contratto.getRalAnnua() / tipoCcnl.getNumeroMensilita());
			}
		}

		if (contratto.getDiariaGiornaliera() != null) {
			contratto.setDiariaAnnua(contratto.getDiariaGiornaliera() * 20 * 12);
		} else if (contratto.getDiariaMensile() != null) {
			contratto.setDiariaAnnua(contratto.getDiariaMensile() * 12);
		}

		if (contratto.getPercentualePartTime() != null) {
			contratto.setRalPartTime((contratto.getPercentualePartTime() / 100) * contratto.getRalAnnua());
		}
	}

}

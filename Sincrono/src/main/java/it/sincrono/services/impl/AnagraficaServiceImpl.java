package it.sincrono.services.impl;

import java.util.List;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.StoricoCommesse;
import it.sincrono.entities.StoricoContratti;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.CommessaRepository;
import it.sincrono.repositories.ContrattoRepository;
import it.sincrono.repositories.StoricoCommesseRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.services.AnagraficaService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.validator.AnagraficaValidator;
import it.sincrono.services.validator.CommessaValidator;
import it.sincrono.services.validator.ContrattoValidator;
import it.sincrono.repositories.StoricoContrattiRepository;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;

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
	private AnagraficaValidator anagraficaValidator;
	
	@Autowired
	private ContrattoValidator contrattoValidator;
	
	@Autowired
	private CommessaValidator commessaValidator;



	@Override
	public List<Anagrafica> list() throws ServiceException {

		List<Anagrafica> list = null;

		try {
			list = anagraficaRepository.findAll();
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public Anagrafica getById(Integer ID) throws ServiceException {
		Anagrafica anagrafica = null;

		try {
			anagrafica = anagraficaRepository.findById(ID).get();
		} catch (NoSuchElementException ne) {
			System.out.println("Exception occurs {}, ID {}");
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return anagrafica;
	}

	@Override
	public void insert(Anagrafica anagrafica) throws ServiceException {

		if (!anagraficaValidator.validate(anagrafica, true)) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}

		try {
			anagraficaRepository.saveAndFlush(anagrafica);
		} catch (DataIntegrityViolationException de) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	@Override
	public void update(Anagrafica anagrafica) throws ServiceException {
		if (!anagraficaValidator.validate(anagrafica, false)) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}

		try {
			Anagrafica Currentanagrafica = anagraficaRepository.findById(anagrafica.getId()).get();
			Currentanagrafica.setId(anagrafica.getId());
			Currentanagrafica.setAltriTitoli(anagrafica.getAltriTitoli());
			Currentanagrafica.setCelllulareAziendale(anagrafica.getCelllulareAziendale());
			Currentanagrafica.setCellularePrivato(anagrafica.getCellularePrivato());
			Currentanagrafica.setCodiceFiscale(anagrafica.getCodiceFiscale());
			Currentanagrafica.setCognome(anagrafica.getCognome());
			Currentanagrafica.setComuneDiNascita(anagrafica.getComuneDiNascita());
			Currentanagrafica.setConiugato(anagrafica.getConiugato());
			Currentanagrafica.setDataDiNascita(anagrafica.getDataDiNascita());
			Currentanagrafica.setDomicilio(anagrafica.getDomicilio());
			Currentanagrafica.setFigliaCario(anagrafica.getFigliaCario());
			Currentanagrafica.setMailAziendale(anagrafica.getMailAziendale());
			Currentanagrafica.setMailPec(anagrafica.getMailPec());
			Currentanagrafica.setMailPrivata(anagrafica.getMailPrivata());
			Currentanagrafica.setNome(anagrafica.getNome());
			Currentanagrafica.setResidenza(anagrafica.getResidenza());
			Currentanagrafica.setTitoliDiStudio(anagrafica.getTitoliDiStudio());

			anagraficaRepository.saveAndFlush(Currentanagrafica);

		} catch (NoSuchElementException ne) {
			System.out.println("Exception occurs {}");
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
	public void delete(Integer ID) throws ServiceException {
		try {
			Anagrafica anagrafica = anagraficaRepository.findById(ID).get();

			anagraficaRepository.delete(anagrafica);
			anagraficaRepository.flush();

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
	public List<AnagraficaDto> search(AnagraficaDto anagraficaDto) throws ServiceException {

		List<AnagraficaDto> list = null;

		try {
			list = anagraficaRepository.search(anagraficaDto);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}
	
	
	@Override
	public void insertAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException {

		try {
			if (!anagraficaValidator.validate(anagraficaDto.getAnagrafica(), true)) {
				System.out.println("Exception occurs {}");
				throw new ServiceException();
			}
			Integer idAnagrafica=anagraficaRepository.getLastId()+1;
			anagraficaRepository.saveAndFlush(anagraficaDto.getAnagrafica());
			storicoCommessaRepository.saveAndFlush(new StoricoCommesse(new Anagrafica(idAnagrafica),
					new Commessa(0)));
			storicoContrattiRepository.saveAndFlush(new StoricoContratti(new Anagrafica(idAnagrafica),
					new Contratto(0)));
			
			
			if(anagraficaDto.getCommessa()!=null) {
				if (!commessaValidator.validate(anagraficaDto.getCommessa(), true)) {
					System.out.println("Exception occurs {}");
					throw new ServiceException();
				}
				commesseRepository.saveAndFlush(anagraficaDto.getCommessa());
				storicoCommessaRepository.saveAndFlush(new StoricoCommesse(new Anagrafica(idAnagrafica),
						new Commessa(commesseRepository.getLastId()+1)));
			}
			
			if(anagraficaDto.getContratto()!=null) {
				if (!contrattoValidator.validate(anagraficaDto.getContratto(), true)) {
					System.out.println("Exception occurs {}");
					throw new ServiceException();
				}
				contrattoRepository.saveAndFlush(anagraficaDto.getContratto());
				storicoContrattiRepository.saveAndFlush(new StoricoContratti(new Anagrafica(idAnagrafica),
						new Contratto(contrattoRepository.getLastId()+1)));
			}

		} catch (DataIntegrityViolationException de) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (ServiceException e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}
	
	@Override
	public void updateAnagraficaDto(AnagraficaDto anagraficaDto) throws ServiceException {
		if (!anagraficaValidator.validate(anagraficaDto.getAnagrafica(), false)) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}
		if (!commessaValidator.validate(anagraficaDto.getCommessa(), false)) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}
		if (!contrattoValidator.validate(anagraficaDto.getContratto(), false)) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}

		try {
			anagraficaRepository.findById(anagraficaDto.getAnagrafica().getId()).get();
			anagraficaRepository.saveAndFlush(anagraficaDto.getAnagrafica());
			contrattoRepository.findById(anagraficaDto.getContratto().getId()).get();
			contrattoRepository.saveAndFlush(anagraficaDto.getContratto());
			commesseRepository.findById(anagraficaDto.getCommessa().getId()).get();
			commesseRepository.saveAndFlush(anagraficaDto.getCommessa());

		} catch (NoSuchElementException ne) {
			System.out.println("Exception occurs {}");
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
	public void insertAnagraficaDtoRelations(AnagraficaDto anagraficaDto) throws ServiceException {

		try {

			Integer idAnagrafica;
			
			if(anagraficaDto.getAnagrafica().getId()!=null) {
				idAnagrafica=anagraficaDto.getAnagrafica().getId();
			}else {
				
				throw new ServiceException();
			}
			
			if(anagraficaDto.getCommessa()!=null) {
				if (!commessaValidator.validate(anagraficaDto.getCommessa(), true)) {
					System.out.println("Exception occurs {}");
					throw new ServiceException();
				}
				commesseRepository.saveAndFlush(anagraficaDto.getCommessa());
				storicoCommessaRepository.saveAndFlush(new StoricoCommesse(new Anagrafica(idAnagrafica),
						new Commessa(commesseRepository.getLastId()+1)));
			}
			
			if(anagraficaDto.getContratto()!=null) {
				if (!contrattoValidator.validate(anagraficaDto.getContratto(), true)) {
					System.out.println("Exception occurs {}");
					throw new ServiceException();
				}
				contrattoRepository.saveAndFlush(anagraficaDto.getContratto());
				storicoContrattiRepository.saveAndFlush(new StoricoContratti(new Anagrafica(idAnagrafica),
						new Contratto(contrattoRepository.getLastId()+1)));
			}

		} catch (DataIntegrityViolationException de) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch (ServiceException e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

}
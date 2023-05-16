package it.sincrono.services.impl;

import java.util.List;



import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.services.AnagraficaService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.validator.AnagraficaValidator;




@Service
public class AnagraficaServiceImpl extends BaseServiceImpl implements AnagraficaService {

	@Autowired
	private AnagraficaRepository anagraficaRepository;
	
	@Autowired
	private AnagraficaValidator anagraficaValidator;

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
		
		if(!anagraficaValidator.validate(anagrafica,true)) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}

		try {
			anagraficaRepository.saveAndFlush(anagrafica);
		} catch(DataIntegrityViolationException de) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch(Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
		
	}

	@Override
	public void update(Anagrafica anagrafica) throws ServiceException {
		if (!anagraficaValidator.validate(anagrafica,true)) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
		}

		try {
			Anagrafica Currentanagrafica = anagraficaRepository.findById(anagrafica.getId()).get();
			Currentanagrafica.setId(anagrafica.getId());
			Currentanagrafica.setAltriTitoli(anagrafica.getAltriTitoli());
			Currentanagrafica.setAttivo(anagrafica.getAttivo());
			Currentanagrafica.setAzienda_tipo(anagrafica.getAziendaTipo());
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

		} catch(NoSuchElementException ne) {
			System.out.println("Exception occurs {}, id {}");
			throw new ServiceException(ServiceMessages.RECORD_NON_TROVATO);
		} catch(DataIntegrityViolationException de) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_INTEGRITA_DATI);
		} catch(Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

	

	@Override
	public List<Anagrafica> search(AnagraficaDto anagraficaDto) throws ServiceException {

		List<Anagrafica> list = null;

		try {
			list = anagraficaRepository.search(anagraficaDto);
		} catch (Exception e) {
			System.out.println("Exception occurs {}");
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}


	
}
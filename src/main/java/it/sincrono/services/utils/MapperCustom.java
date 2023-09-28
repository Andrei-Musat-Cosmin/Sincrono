package it.sincrono.services.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.CommessaRepository;
import it.sincrono.repositories.ContrattoRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequestDto;

@Component
public class MapperCustom {

	@Autowired
	ContrattoRepository contrattoRepository;
	@Autowired
	AnagraficaRepository anagraficaRepository;
	@Autowired
	CommessaRepository commessaRepository;

	public AnagraficaDto toAnagraficaDto(Integer id) {
		AnagraficaDto anagraficaDto = new AnagraficaDto();

		Anagrafica anagrafica = anagraficaRepository.findById(id).get();

		anagrafica.getUtente().setPassword(null);
		anagrafica.getUtente().setTokenPassword(null);
		anagrafica.getUtente().setUsername(null);

		anagraficaDto.setAnagrafica(anagrafica);

		anagraficaDto.setContratto(toContratto(id));

		anagraficaDto.setCommesse(toCommessaArray(id));

		return anagraficaDto;
	}

	public AnagraficaDto toAnagraficaDto(String token) {
		AnagraficaDto anagraficaDto = new AnagraficaDto();

		Anagrafica anagrafica = anagraficaRepository.findByToken(token);

		anagrafica.getUtente().setPassword(null);
		anagrafica.getUtente().setTokenPassword(null);
		anagrafica.getUtente().setUsername(null);

		anagraficaDto.setAnagrafica(anagrafica);

		anagraficaDto.setContratto(toContratto(anagrafica.getId()));

		anagraficaDto.setCommesse(toCommessaArray(anagrafica.getId()));

		return anagraficaDto;
	}

	public Contratto toContratto(Integer id) {
		return contrattoRepository.findByIdAnagrafica(id);
	}

	public List<Commessa> toCommessaArray(Integer id) {
		return commessaRepository.findByIdAnagrafica(id);
	}

	
}

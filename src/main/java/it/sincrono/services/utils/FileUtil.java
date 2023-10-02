package it.sincrono.services.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.MeseDto;
import it.sincrono.repositories.dto.RapportinoDto;

@Component
public class FileUtil {

	public RapportinoDto readFile(String percorso) {
		
		RapportinoDto rapportinoDto = new RapportinoDto();

		try (BufferedReader reader = new BufferedReader(new FileReader(percorso))) {
			StringBuilder fileStringBuilder = new StringBuilder();
			String linea;

			// Leggi e costruisci la stringa del file
			while ((linea = reader.readLine()) != null) {
				fileStringBuilder.append(linea).append("\n");
			}

			String fileString = fileStringBuilder.toString();
			
			rapportinoDto= covertStrinInRapportinoDto(fileString);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rapportinoDto;
	}

	private RapportinoDto covertStrinInRapportinoDto(String fileString) {

		RapportinoDto rapportinoDto = new RapportinoDto();

		rapportinoDto.setMese(new MeseDto());

		List<GiornoDto> mese = new ArrayList<GiornoDto>();

		for (String giornoNotSplit : fileString.split(";")) {

			String[] giornoSplit = giornoNotSplit.split(",");

			GiornoDto giornoDto = new GiornoDto();

			giornoDto.setGiorno(Integer.parseInt(giornoSplit[0]));

			giornoDto.setCliente(giornoSplit[1]);

			giornoDto.setOreOrdinarie(Double.parseDouble(giornoSplit[2]));

			mese.add(giornoDto);

		}

		rapportinoDto.getMese().setMese(mese);

		return rapportinoDto;

	}

}

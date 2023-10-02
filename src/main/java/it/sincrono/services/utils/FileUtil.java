package it.sincrono.services.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import java.io.File;

import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.MeseDto;
import it.sincrono.repositories.dto.RapportinoDto;

@Component
public class FileUtil {

	public RapportinoDto readFile(String percorso) throws Exception {

		RapportinoDto rapportinoDto = new RapportinoDto();

		creatFolder(percorso);

		try (BufferedReader reader = new BufferedReader(new FileReader(percorso))) {
			StringBuilder fileStringBuilder = new StringBuilder();
			String linea;

			while ((linea = reader.readLine()) != null) {
				fileStringBuilder.append(linea).append("\n");
			}

			String fileString = fileStringBuilder.toString();

			rapportinoDto = covertStringInRapportinoDto(fileString);

		} catch (Exception e) {
			throw new Exception();
		}

		return rapportinoDto;
	}

	private RapportinoDto covertStringInRapportinoDto(String fileString) {

		RapportinoDto rapportinoDto = new RapportinoDto();

		rapportinoDto.setMese(new MeseDto());

		List<GiornoDto> mese = new ArrayList<GiornoDto>();

		if (fileString != null && !fileString.isEmpty()) {

			for (String giornoNotSplit : fileString.split(";")) {

				String[] giornoSplit = giornoNotSplit.split(",");

				GiornoDto giornoDto = new GiornoDto();

				if (giornoSplit[0] != null)
					giornoDto.setGiorno(Integer.parseInt(giornoSplit[0]));

				if (giornoSplit[1] != null)
					giornoDto.setCliente(giornoSplit[1]);

				if (giornoSplit[2] != null)
					giornoDto.setOreOrdinarie(Double.parseDouble(giornoSplit[2]));

				mese.add(giornoDto);

			}
			
			rapportinoDto.getMese().setMese(mese);


		} else {

			createRapportinoForMonth(rapportinoDto);
		}

		
		return rapportinoDto;

	}

	public void creatFolder(String percorso) throws Exception {

		File file = new File(percorso);
		if (!file.exists()) {
			try {
				Files.createDirectories(Paths.get(percorso).getParent());
				file.createNewFile();
			} catch (Exception e) {
				throw new Exception();
			}
		}
	}

	private int getNumeroGiorniMese(int anno, int mese) {
		YearMonth yearMonth = YearMonth.of(anno, mese);
		return yearMonth.lengthOfMonth();
	}

	private void createRapportinoForMonth(RapportinoDto rapportinoDto) {

		LocalDate oggi = LocalDate.now();

		rapportinoDto.setMese(new MeseDto());

		List<GiornoDto> mese = new ArrayList<GiornoDto>();

		for (int i = 0; i < getNumeroGiorniMese(oggi.getYear(), oggi.getMonthValue()); i++) {

			GiornoDto giornoDto = new GiornoDto();

			giornoDto.setGiorno(null);

			giornoDto.setCliente(null);

			giornoDto.setOreOrdinarie(null);

			mese.add(giornoDto);

		}

		rapportinoDto.getMese().setMese(mese);

	}

}

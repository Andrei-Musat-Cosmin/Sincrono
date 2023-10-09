package it.sincrono.services.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.MeseDto;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Component
public class FileUtil {
	private static final Logger LOGGER = LogManager.getLogger(FileUtil.class);

	public RapportinoDto readFile(String percorso) throws Exception {

		RapportinoDto rapportinoDto = new RapportinoDto();

		creatFolder(percorso);

		try (BufferedReader reader = new BufferedReader(new FileReader(percorso))) {
			StringBuilder fileStringBuilder = new StringBuilder();
			String linea = null;

			// fileStringBuilder.append(reader.readLine()).append("\n");
			// String fileString = fileStringBuilder.toString();

			rapportinoDto = covertStringInRapportinoDto(reader.readLine());

			rapportinoDto.setNote(reader.readLine());

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return rapportinoDto;
	}

	private RapportinoDto covertStringInRapportinoDto(String fileString) {

		RapportinoDto rapportinoDto = new RapportinoDto();

		rapportinoDto.setMese(new MeseDto());

		List<GiornoDto> mese = new ArrayList<GiornoDto>();

		if (fileString != null && !fileString.isEmpty()) {

			fileString = fileString.replaceAll("\n", "");

			for (String giornoNotSplit : fileString.split(";")) {

				List<String> cliente = new ArrayList<String>();

				List<Double> oreOrdinarie = new ArrayList<Double>();

				String[] giornoSplit = giornoNotSplit.split(",");

				GiornoDto giornoDto = new GiornoDto();

				if (giornoSplit[0] != null && !giornoSplit[0].isEmpty() && !giornoSplit[0].equals("null"))
					giornoDto.setGiorno(Integer.parseInt(giornoSplit[0]));

				for (String elem : giornoSplit[1].split("/")) {

					if (elem.split("-")[0] != null && !elem.split("-")[0].isEmpty()
							&& !elem.split("-")[0].equals("null"))
						cliente.add(elem.split("-")[0]);

					if (elem.split("-")[1] != null && !elem.split("-")[1].isEmpty()
							&& !elem.split("-")[1].equals("null"))
						oreOrdinarie.add(Double.parseDouble(elem.split("-")[1]));

				}

				if (cliente != null)
					giornoDto.setCliente(cliente);

				if (oreOrdinarie != null)
					giornoDto.setOreOrdinarie(oreOrdinarie);

				mese.add(giornoDto);

			}

			rapportinoDto.getMese().setGiorni(mese);

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
				LOGGER.log(Level.ERROR, e.getMessage());
				throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
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

		rapportinoDto.getMese().setGiorni(mese);

	}

	public void saveFile(String path, RapportinoRequestDto rapportino) throws ServiceException {
		if (rapportino != null) {
			try {
				Path filePath = Path.of(path);
				if (Files.exists(filePath)) {
					Files.delete(filePath);
				}
				try (FileWriter writer = new FileWriter(filePath.toFile())) {
					for (GiornoDto giorno : rapportino.getRapportinoDto().getMese().getGiorni()) {
						writer.write(
								giorno.getGiorno() + "," + giorno.getCliente() + "," + giorno.getOreOrdinarie() + ";");
					}
				}
			} catch (HttpMessageNotReadableException e) {
				LOGGER.log(Level.ERROR, e.getMessage());
				throw new ServiceException(ServiceMessages.FORMATO_INCORRETTO);
			} catch (IOException e) {
				LOGGER.log(Level.ERROR, e.getMessage());
				throw new ServiceException(ServiceMessages.ERRORE_SALVATAGGIO_FILE);
			} catch (Exception e) {
				LOGGER.log(Level.ERROR, e.getMessage());
				throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
			}
		}
		LOGGER.log(Level.INFO, "Rapportino salvato con successo.");
	}
}
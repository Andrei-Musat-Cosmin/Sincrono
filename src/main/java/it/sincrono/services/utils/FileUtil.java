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
import it.sincrono.repositories.dto.StraordinarioDto;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.responses.DocumentResponse;
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
			throw new Exception(e);
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

				List<StraordinarioDto> straordinari = new ArrayList<StraordinarioDto>();

				String[] giornoSplit = giornoNotSplit.split(",");

				GiornoDto giornoDto = new GiornoDto();

				if (giornoSplit[0] != null && !giornoSplit[0].isEmpty() && !giornoSplit[0].equals("null"))
					giornoDto.setGiorno(Integer.parseInt(giornoSplit[0]));

				for (String elem : giornoSplit[1].split("/")) {

					if (elem != null && !elem.equals("null")) {

						StraordinarioDto straordinario = new StraordinarioDto();

						if (elem.split("-")[0] != null && !elem.split("-")[0].isEmpty()
								&& !elem.split("-")[0].equals("null"))
							cliente.add(elem.split("-")[0]);

						if (elem.split("-")[1] != null && !elem.split("-")[1].isEmpty()
								&& !elem.split("-")[1].equals("null"))
							oreOrdinarie.add(Double.parseDouble(elem.split("-")[1]));

						if (elem.split("-")[2] != null && !elem.split("-")[2].isEmpty()
								&& !elem.split("-")[2].equals("null"))
							straordinario.setFascia1(Double.parseDouble(elem.split("-")[2]));

						if (elem.split("-")[3] != null && !elem.split("-")[3].isEmpty()
								&& !elem.split("-")[3].equals("null"))
							straordinario.setFascia2(Double.parseDouble(elem.split("-")[3]));

						if (elem.split("-")[4] != null && !elem.split("-")[4].isEmpty()
								&& !elem.split("-")[4].equals("null"))
							straordinario.setFascia3(Double.parseDouble(elem.split("-")[4]));

						straordinari.add(straordinario.getFascia1() != null || straordinario.getFascia2() != null
								|| straordinario.getFascia3() != null ? straordinario : null);

					} else {

						cliente = null;

						oreOrdinarie = null;

						straordinari = null;

					}

				}

				if (cliente != null)
					giornoDto.setCliente(cliente);

				if (oreOrdinarie != null)
					giornoDto.setOreOrdinarie(oreOrdinarie);

				if (straordinari != null)
					giornoDto.setStraordinari(straordinari);

				if (giornoSplit[2] != null && !giornoSplit[2].isEmpty() && !giornoSplit[2].equals("null"))
					giornoDto.setFerie(Boolean.parseBoolean(giornoSplit[2]));

				if (giornoSplit[3] != null && !giornoSplit[3].isEmpty() && !giornoSplit[3].equals("null"))
					giornoDto.setMalattie(Boolean.parseBoolean(giornoSplit[3]));

				if (giornoSplit[4] != null && !giornoSplit[4].isEmpty() && !giornoSplit[4].equals("null"))
					giornoDto.setPermessi(Double.parseDouble(giornoSplit[4]));

				if (giornoSplit[5] != null && !giornoSplit[5].isEmpty() && !giornoSplit[5].equals("null"))
					giornoDto.setNote(giornoSplit[5]);

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
				if (Files.exists(filePath))
					Files.delete(filePath);
				String dati = "";
				try (FileWriter writer = new FileWriter(filePath.toFile())) {
					for (GiornoDto giorno : rapportino.getRapportinoDto().getMese().getGiorni()) {
						/** SET DEL GIORNO **/
						dati += giorno.getGiorno() + ",";
						if (giorno.getCliente() != null) {
							for (int i = 0; i < giorno.getCliente().size(); i++) {

								/** SET DELLA COMMESSA & SET ORE ORDINARIE **/
								dati += giorno.getCliente().get(i) + "-" + giorno.getOreOrdinarie().get(i);

								/** SET DI 1FASCIA &OR 2FASCIA &OR 3FASCIA **/
								if (giorno.getStraordinari() != null) {
									if (giorno.getStraordinari().get(i).getFascia1() != null) {
										dati += "-" + giorno.getStraordinari().get(i).getFascia1();
									} else
										dati += "-null";
									if (giorno.getStraordinari().get(i).getFascia2() != null) {
										dati += "-" + giorno.getStraordinari().get(i).getFascia2();
									} else
										dati += "-null";
									if (giorno.getStraordinari().get(i).getFascia3() != null) {
										dati += "-" + giorno.getStraordinari().get(i).getFascia3();
									} else
										dati += "-null";
								} else {
									dati += "-null-null-null";
								}

								/** SEPARATORER PER IL PROSSIMO CLIENTE **/
								if (i < giorno.getCliente().size() - 1)
									dati += "/";
							}
							/** SET DELLE FEIRE E DELLE MALATTIE A NULL **/
							dati += ",null,null,";

							/** SET DEI PERMESSI **/
							if (giorno.getPermessi() != null) {
								dati += giorno.getPermessi() + ",";
							} else
								dati += "null,";

							/** SET DELLE NOTE **/
							if (giorno.getNote() != null) {
								dati += giorno.getNote();
							} else
								dati += "null";

						} else {
							/** SE IL GIORNO E' NULL **/
							dati += "null,null,null,null,null,";

							/** SET DI FERIE OR MALATTIE **/
							if (giorno.getFerie() != null) {
								dati += giorno.getFerie() + ",";
							} else
								dati += "null,";
							if (giorno.getMalattie() != null) {
								dati += giorno.getMalattie() + ",";
							} else
								dati += "null,";
							dati += "null,";
							if (giorno.getNote() != null && giorno.getNote() != "") {
								dati += giorno.getNote();
							} else
								dati += "null";
						}
						dati += ";";
					}
					writer.write(dati);
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

	public void appendNote(String path, String note) throws ServiceException {
		if (note != null) {
			try {
				Path filePath = Path.of(path);
				try (FileWriter writer = new FileWriter(filePath.toFile(), true)) {
					writer.append("\n" + note);
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
		LOGGER.log(Level.INFO, "Note salvate con successo.");
	}

	// ------------metodi per la lettura e scrittura di un image in base64 nel
	// file---------------------------------------

	public void saveFileImage(String percorso, String base64) throws Exception {

		try {

			Path filePath = Path.of(percorso);
			try (FileWriter writer = new FileWriter(filePath.toFile(), true)) {
				writer.write(base64);
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

		LOGGER.log(Level.INFO, "image salvata con successo.");
	}

	public DocumentResponse readFileImage(String percorso) throws Exception {

		creatFolder(percorso);

		DocumentResponse documentResponse = null;

		try (BufferedReader reader = new BufferedReader(new FileReader(percorso))) {

			documentResponse = covertStringInDocumentResponse(reader.readLine());

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new Exception(e);
		}

		return documentResponse;
	}

	private DocumentResponse covertStringInDocumentResponse(String fileString) {

		return new DocumentResponse(fileString);

	}

}
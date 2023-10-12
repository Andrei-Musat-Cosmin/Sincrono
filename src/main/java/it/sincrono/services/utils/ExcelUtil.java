package it.sincrono.services.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Rapportino;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.impl.RapportinoServiceImpl;

@Component
public class ExcelUtil {
	private static final Logger LOGGER = LogManager.getLogger(RapportinoServiceImpl.class);

	private static final String EXCELPATH = "C:/Users/SINCRONO/Desktop/provaSalvataggioExcel.xlsl";

	public String toExcel(List<Rapportino> rapportini) throws ServiceException {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Dati Excel");

//			Row headerRow = sheet.createRow(0);

			int rowNum = 0;
			int cellNum = 0;
			String cognomeNome = "";
			Row row = null;
			row = sheet.createRow(rowNum);
			for (int i = 1; i < 32; i++) {
				row.createCell(i).setCellValue(i);
			}
			for (Rapportino rapportino : rapportini) {
				if (!cognomeNome
						.equals(rapportino.getAnagrafica().getCognome() + " " + rapportino.getAnagrafica().getNome())) {
					cognomeNome = rapportino.getAnagrafica().getCognome() + " " + rapportino.getAnagrafica().getNome();
					cellNum = 0;
					rowNum++;
					row = sheet.createRow(rowNum);
					row.createCell(cellNum).setCellValue(cognomeNome);
					cellNum++;
				}
				if (rapportino.getOre() != null) {
					row.createCell(cellNum).setCellValue(rapportino.getOre());
				} else if (rapportino.getFerie() != null) {
					row.createCell(cellNum).setCellValue("F");
				} else if (rapportino.getMalattie() != null) {
					row.createCell(cellNum).setCellValue("M");
				} else if (rapportino.getPermessi() != null) {
					row.createCell(cellNum).setCellValue(rapportino.getPermessi() + "p");
				}
				cellNum++;

			}
			FileOutputStream outputStream = new FileOutputStream(EXCELPATH);
			workbook.write(outputStream);
			outputStream.close();

			// return excelToBase64();
			return "fatto";
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	public static String excelToBase64(String filePath) throws ServiceException {
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fis.read(bytes);
			fis.close();

			// Encode the bytes to Base64
			String base64EncodedString = Base64.getEncoder().encodeToString(bytes);

			return base64EncodedString;
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.IMPOSSIBILE_SCRIVERE_EXCEL);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}
}

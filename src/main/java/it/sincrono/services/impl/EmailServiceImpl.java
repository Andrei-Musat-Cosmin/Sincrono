package it.sincrono.services.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.sincrono.services.EmailService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	private static final Logger LOGGER = LogManager.getLogger(EmailServiceImpl.class);

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body)
			throws ServiceException {
		try {
			if (to == null || to == "") {
				LOGGER.log(Level.ERROR, "Destinatario dell'email non valido }");
				throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
			}
			if (cc != null && cc.length>0) {
				for (int i = 0; i < cc.length; i++)
					if (cc[i] == "") {
						LOGGER.log(Level.ERROR, "Copie carbone non valide }");
						throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
					}
			}
			
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setCc(cc != null ? cc : new String[0]);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body);

			if (file != null) {

				for (int i = 0; i < file.length; i++) {
					mimeMessageHelper.addAttachment(file[i].getOriginalFilename(),
							new ByteArrayResource(file[i].getBytes()));
				}

			}
			javaMailSender.send(mimeMessage);
			return "mail sent";
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}
	
	@Override
	public String sendMail(MultipartFile[] file, String[] to, String[] cc, String subject, String body)
			throws ServiceException {
		try {
			if (to != null && to.length>0) {
				for (int i = 0; i < to.length; i++)
					if (to[i] == "") {
						LOGGER.log(Level.ERROR, "Destinatario dell'email non valido }");
						throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
					}
			}else {
				
				LOGGER.log(Level.ERROR, "Destinatario dell'email non valido }");
				throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
				
				
			}
			if (cc != null && cc.length>0) {
				for (int i = 0; i < cc.length; i++)
					if (cc[i] == "") {
						LOGGER.log(Level.ERROR, "Copie carbone non valide }");
						throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
					}
			}
			
			
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setCc(cc != null ? cc : new String[0]);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body);

			if (file != null) {

				for (int i = 0; i < file.length; i++) {
					mimeMessageHelper.addAttachment(file[i].getOriginalFilename(),
							new ByteArrayResource(file[i].getBytes()));
				}

			}
			javaMailSender.send(mimeMessage);
			return "mail sent";
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}
}
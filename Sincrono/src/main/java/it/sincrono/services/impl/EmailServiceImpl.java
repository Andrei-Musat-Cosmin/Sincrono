package it.sincrono.services.impl;

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

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body) {
		try {
			if (to != null && to != "") {
				System.out.println("Exception occurs { ERRORE_VALIDAZIONE: destinatario dell'email non valido }");
				throw new ServiceException(ServiceMessages.ERRORE_VALIDAZIONE);
			}
			if (cc != null) {
				for (int i = 0; i < cc.length; i++)
					if (cc[i] != "") {
						System.out.println("Exception occurs { ERRORE_VALIDAZIONE: copie carbone non valide }");
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
			throw new RuntimeException(e);
		}

	}
}
package it.sincrono.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.requests.EmailRequest;
import it.sincrono.responses.GenericResponse;
import it.sincrono.services.EmailService;

@RestController
@RequestMapping("/mail")
public class EmailController {
	private EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/send")
	public @ResponseBody HttpEntity<GenericResponse> sendMail(@RequestBody EmailRequest emailRequest) {
		HttpEntity<GenericResponse> httpEntity;
		GenericResponse genericResponse = new GenericResponse();
		try {
			emailService.sendMail(emailRequest.getFile(), emailRequest.getTo(), emailRequest.getCc(),
					emailRequest.getSubject(), emailRequest.getBody());
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		} catch (Exception e) {
			genericResponse.setEsito(new Esito(500, e.getMessage(), new String[] { null }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		return httpEntity;
	}

}
package it.sincrono.services;

import org.springframework.web.multipart.MultipartFile;

import it.sincrono.services.exceptions.ServiceException;

public interface EmailService {
	String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body) throws ServiceException;
	
	public String sendMail(MultipartFile[] file, String[] to, String[] cc, String subject, String body)
			throws ServiceException;
	
}

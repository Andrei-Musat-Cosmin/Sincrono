package it.sincrono.requests;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class EmailRequest extends GenericRequest {

	private MultipartFile[] file;
	private String to;
	private String[] cc;
	private String subject;
	private String body;
	private String[] toRapportini;



	public EmailRequest(MultipartFile[] file, String to, String[] cc, String subject, String body,
			String[] toRapportini) {
		super();
		this.file = file;
		this.to = to;
		this.cc = cc;
		this.subject = subject;
		this.body = body;
		this.toRapportini = toRapportini;
	}

	public EmailRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultipartFile[] getFile() {
		return file;
	}

	public void setFile(MultipartFile[] file) {
		this.file = file;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
	

	public String[] getToRapportini() {
		return toRapportini;
	}

	public void setToRapportini(String[] toRapportini) {
		this.toRapportini = toRapportini;
	}

	@Override
	public String toString() {
		return "'EmailRequest':{" + "'file'= '" + Arrays.toString(file) + "'," + "'to'= '" + to + "'," + "'cc'='"
				+ Arrays.toString(cc) + "'," + "'subject'='" + subject + "'," + "'body'= '" + body + "'}";
	}

}

package br.com.autentication.mail;

import java.util.List;

public class Message {

	private String mailer;

	private List<String> recipients;

	private String subject;

	private String body;

	public Message() {
		super();
	}

	public Message(String mailer, List<String> recipients, String subject, String body) {
		super();
		this.mailer = mailer;
		this.recipients = recipients;
		this.subject = subject;
		this.body = body;
	}

	public String getMailer() {
		return mailer;
	}

	public void setMailer(String mailer) {
		this.mailer = mailer;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
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

	@Override
	public String toString() {
		return "Message [mailer=" + mailer + ", recipients=" + recipients + ", subject=" + subject + ", body=" + body
				+ "]";
	}

}

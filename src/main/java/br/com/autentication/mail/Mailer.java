package br.com.autentication.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;



@Component
public class Mailer {

	@Autowired
	private JavaMailSender javaMailSender;

	public void enviar(Message message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setFrom(message.getMailer());
		simpleMailMessage.setTo(message.getRecipients().toArray(new String[message.getRecipients().size()]));
		simpleMailMessage.setSubject(message.getSubject());
		simpleMailMessage.setText(message.getBody());

		javaMailSender.send(simpleMailMessage);
	}
}


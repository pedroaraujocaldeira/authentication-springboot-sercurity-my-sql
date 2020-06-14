package br.com.autentication.service;

import java.util.Arrays;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.autentication.mail.Mailer;
import br.com.autentication.mail.Message;
import br.com.autentication.model.UserCredencial;
import br.com.autentication.model.UserDTO;
import br.com.autentication.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	Mailer mail;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	Environment env;

	public ResponseEntity<?> createUser(@Valid UserDTO user) {
		String body = "Bem vindo ao ADV!";
		Optional<UserCredencial> userList = userRepository.findByUserName(user.getMail());
		
		if (userList.isPresent()) {
			body = "Você já possui cadastro no nosso sisteme! Não se preocupe, acesso com o codigo e acompanhe o andamento do seu processo!\n\nSeu código é: ";
			body = String.format("Bem vindo ao ADV! Seu Login:%s Senha:%s",user.getMail(), "NOVASENHA");
			UserCredencial novo = userList.get();
			String encodedPassword = passwordEncoder.encode("NOVASENHA");
			novo.setPassword(encodedPassword);

		} else {
			body = String.format("Bem vindo ao ADV! Seu Login:%s Senha:%s",user.getMail(), user.getPassword());
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			UserCredencial dto = ToEntities(user);
			userRepository.save(dto);
		}
		enviarEmail(user.getMail(),body);
		return ResponseEntity.ok(user);
	}
	
	private UserCredencial ToEntities(@Valid UserDTO user) {

		UserCredencial u = new UserCredencial();
		u.setActive(true);
		u.setPassword(user.getPassword());
		u.setRole("ROLE_USER");
		u.setUserName(user.getMail());
		
		return u;
	}

	public void enviarEmail(String mailSend, String body) {
		mail.enviar(new Message(env.getProperty("mail.smtp.username"),
				Arrays.asList("Plano Ativado " + "<" + mailSend + ">"), "Usuário Criado Com Sucesso!", body));
	}
}

package br.com.autentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.autentication.repositories.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class AutenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutenticationApplication.class, args);
	}

}

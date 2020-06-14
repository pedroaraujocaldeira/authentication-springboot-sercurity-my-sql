package br.com.autentication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AutenticationApplicationTests {

	@Test
	void contextLoads() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "pass";
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println();
		System.out.println("Password is         : " + password);
		System.out.println("Encoded Password is : " + encodedPassword);
		System.out.println();

		boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
		System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);

		password = "yawin";
		isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
		System.out.println("Password : " + password + "           isPasswordMatch    : " + isPasswordMatch);
	}

}

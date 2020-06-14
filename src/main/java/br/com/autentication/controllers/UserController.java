package br.com.autentication.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.autentication.model.AuthenticationRequest;
import br.com.autentication.model.AuthenticationResponse;
import br.com.autentication.model.UserDTO;
import br.com.autentication.service.UserAuthenticationService;
import br.com.autentication.service.UserService;
import br.com.autentication.utils.JwtUtils;

@RestController
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;

	@Autowired
	UserAuthenticationService userDetailsService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@GetMapping("/")
	public String home() {
		return "Welcome";
	}
	

	@GetMapping("/user")
	public String user() {
		return "user";
	}
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {   
//			ResponseEntity.status(HttpStatus.UNAUTHORIZED);
			throw new Exception("BadCredentials");
		}
		UserDetails user = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	
		String jwt = jwtUtils.generateToken(user);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
}

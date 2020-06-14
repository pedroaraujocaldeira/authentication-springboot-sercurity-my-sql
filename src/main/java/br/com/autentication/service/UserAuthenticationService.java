package br.com.autentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.autentication.model.UserCredencial;
import br.com.autentication.model.UserPerson;
import br.com.autentication.repositories.UserRepository;

@org.springframework.stereotype.Service
public class UserAuthenticationService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<UserCredencial> user = userRepository.findByUserName(userName);
		
		user.orElseThrow(()-> new UsernameNotFoundException("Not found "+ userName));
		
		return user.map(UserPerson::new).get();
	}
}

package br.com.autentication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autentication.model.UserCredencial;

public interface UserRepository extends JpaRepository<UserCredencial, Integer> {

	Optional<UserCredencial> findByUserName(String userName);


}

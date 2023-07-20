package it.sincrono.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.sincrono.entities.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

	Optional<Utente> findByTokenPassword(String token);

}
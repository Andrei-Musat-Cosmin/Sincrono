package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.sincrono.entities.Funzione;


public interface FunzioneRepository extends JpaRepository<Funzione, Integer>,FunzioneCustomRepository {

	

}

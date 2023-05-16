package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Contratto;

@Repository
public interface ContrattoRepository extends JpaRepository<Contratto, Integer> {

}

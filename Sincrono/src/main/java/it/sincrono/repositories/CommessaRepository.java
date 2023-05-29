package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Commessa;
import it.sincrono.repositories.exceptions.RepositoryException;

@Repository
public interface CommessaRepository extends JpaRepository<Commessa, Integer>, CommessaCustomRepository {



}

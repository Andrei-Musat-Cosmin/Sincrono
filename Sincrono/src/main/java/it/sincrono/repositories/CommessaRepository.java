package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Commessa;

@Repository
public interface CommessaRepository extends JpaRepository<Commessa, Integer>, CommessaCustomRepository {
	

}

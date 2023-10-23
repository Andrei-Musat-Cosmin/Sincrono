package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Commessa;
import it.sincrono.entities.StoricoCommesse;

@Repository
public interface StoricoCommesseRepository extends JpaRepository<StoricoCommesse, Integer> {

	List<Commessa> getStoricoCommesseByAnagrafica(Integer id);

}

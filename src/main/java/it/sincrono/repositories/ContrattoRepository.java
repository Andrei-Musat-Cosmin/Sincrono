package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Contratto;

@Repository
public interface ContrattoRepository extends JpaRepository<Contratto, Integer>, ContrattoCustomRepository {

	@Query("SELECT a FROM Contratto a INNER JOIN StoricoContratti b ON b.contratto.id=a.id WHERE a.id=(SELECT max(a1.id) FROM Contratto a1 INNER JOIN StoricoContratti b1 ON b1.contratto.id=a1.id WHERE b1.anagrafica.id=?1 AND a1.id!=0)")
	Contratto findByIdAnagrafica(Integer id);

}

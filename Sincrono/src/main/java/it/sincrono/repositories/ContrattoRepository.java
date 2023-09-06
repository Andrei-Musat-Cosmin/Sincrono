package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Contratto;

@Repository
public interface ContrattoRepository extends JpaRepository<Contratto, Integer>, ContrattoCustomRepository {

//	@Query("SELECT a FROM Contratto a INNER JOIN StoricoContratti b ON b.idContratto=a.id WHERE a.id=(select max(a1.id) from contratto a1 inner join storico_contratti b1 on b1.id_contratto=a1.id where b1.id_anagrafica=?1)")
//	Contratto getById(Integer id);

}

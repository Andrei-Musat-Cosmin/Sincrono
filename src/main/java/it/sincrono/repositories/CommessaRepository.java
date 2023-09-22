package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Commessa;

@Repository
public interface CommessaRepository extends JpaRepository<Commessa, Integer>, CommessaCustomRepository {
	
	//select c.* from commessa c inner join storico_commesse sc on sc.id_commessa=c.id where sc.id_anagrafica={0} and c.id!=0 and c.stato=true
	
}

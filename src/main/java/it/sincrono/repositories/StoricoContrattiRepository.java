package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Contratto;
import it.sincrono.entities.StoricoContratti;

@Repository
public interface StoricoContrattiRepository
		extends JpaRepository<StoricoContratti, Integer>, StoricoContrattiCustomRepository {
	
	
	@Query("select sc.contratto from StoricoContratti sc where sc.anagrafica.id=(?1) and sc.contratto.attivo=false")
	List<Contratto> getStoricoContratti(Integer idAnagrafica);
	
	

}

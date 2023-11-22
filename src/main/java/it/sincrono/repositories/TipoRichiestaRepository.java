package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Richieste;
import it.sincrono.entities.TipoRichieste;
import it.sincrono.repositories.dto.RichiestaDto;
import it.sincrono.requests.RichiestaRequest;

public interface TipoRichiestaRepository extends JpaRepository<TipoRichieste, Integer> {
	

	@Query("SELECT a FROM TipoRichieste a WHERE a.richiesta.anno=?1 and a.richiesta.mese=?2 and a.richiesta.anagrafica.id=?3")
	public List<TipoRichieste> getRichieste(Integer anno,Integer mese,Integer idAnagrafica);



}

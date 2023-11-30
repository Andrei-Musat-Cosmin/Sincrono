package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Richieste;
import it.sincrono.entities.TipoRichieste;
import it.sincrono.repositories.dto.RichiestaDto;
import it.sincrono.requests.RichiestaRequest;

public interface RichiestaRepository extends JpaRepository<Richieste, Integer> {

	@Query("SELECT a FROM TipoRichieste a WHERE a.richiesta.id=?1")
	public List<TipoRichieste> getRichiesta(Integer id);

	
	
	/*@Query("SELECT a FROM tipoRichieste a WHERE a.richieste.mese=?1.richiestaDto.mese AND a.richieste.anno=?1.richiestaDto.anno AND a.n_giorno=?3 AND a.richieste.id_anagrafica=?4")
	public List<RichiestaDto> getRichiesta(RichiestaRequest richiestaRequest);*/
	
}

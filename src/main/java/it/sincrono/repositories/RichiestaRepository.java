package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.repositories.dto.RichiestaDto;

public interface RichiestaRepository extends JpaRepository<RichiestaDto, Integer> {

	@Query("SELECT a FROM tipoRichieste a WHERE a.richieste.mese=?1 AND a.richieste.anno=?2 AND a.n_giorno=?3 AND a.richieste.id_anagrafica=?4")
	public List<RichiestaDto> getRichiesta(Integer anno, Integer mese, Integer giorno, Integer idAnagrafica);
	
}

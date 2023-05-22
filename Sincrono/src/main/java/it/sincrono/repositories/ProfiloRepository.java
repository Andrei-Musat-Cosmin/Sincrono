package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Profilo;
import it.sincrono.repositories.dto.ProfiloDto;

@Repository
public interface ProfiloRepository extends JpaRepository<Profilo, Integer> {
	@Query(value = "SELECT a.ruolo.nome,a.dataInizio,a.dataFine FROM Profilo a WHERE a.utente.id = :id")
	public List<ProfiloDto> getProfiloByAnagrafica(@Param("id") Integer id);
}

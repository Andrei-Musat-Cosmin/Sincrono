package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sincrono.entities.Operazione;

public interface OperazioniRepository extends JpaRepository<Operazione, Integer>{
	
	@Query(value = "SELECT a FROM Operazione a WHERE a.funzione.id = :id")
	public List<Operazione> getOperazioniByFunzioni(@Param("id") Integer id);

}

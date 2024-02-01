package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Provincia;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer>, TipologicheCustomRepository {
	
	@Query(value = "SELECT a FROM Provincia a ORDER BY a.id")
	public List<Provincia> getProvinceMap() throws RepositoryException;
	
}

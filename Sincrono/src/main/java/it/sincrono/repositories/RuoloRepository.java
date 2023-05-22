package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Ruolo;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface RuoloRepository extends JpaRepository<Ruolo, Integer>, RuoloCustomRepository {
	@Query(value = "SELECT new Ruolo(a.id, a.nome) FROM Ruolo a ORDER BY a.nome ASC")
	public List<Ruolo> map();

	public Ruolo getPadre(Integer id) throws RepositoryException;

	public Integer getRuoloByUsername(String username);
}

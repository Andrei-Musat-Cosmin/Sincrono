package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import it.sincrono.services.exceptions.ServiceException;

public interface DashboardRepository extends JpaRepository<Anagrafica, Integer>, DashboardCustomRepository {
	


	

}
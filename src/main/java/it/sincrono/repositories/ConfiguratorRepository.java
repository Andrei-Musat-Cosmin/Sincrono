package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Configurator;

public interface ConfiguratorRepository extends JpaRepository<Anagrafica, Integer> {

	@Query("SELECT c FROM Configurator c")
	List<Configurator> listConfigurator();

}
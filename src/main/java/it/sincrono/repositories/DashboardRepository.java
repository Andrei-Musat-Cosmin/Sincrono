package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.sincrono.entities.Anagrafica;

public interface DashboardRepository extends JpaRepository<Anagrafica, Integer>, DashboardCustomRepository {

}
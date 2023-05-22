package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import it.sincrono.entities.StoricoContratti;

@Repository
public interface StoricoContrattiRepository  extends JpaRepository<StoricoContratti, Integer>  {

}

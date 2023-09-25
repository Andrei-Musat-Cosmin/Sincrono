package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.sincrono.entities.Privilegio;

public interface PrivilegioRepository extends JpaRepository<Privilegio, Integer>, PrivilegioCustomRepository {}
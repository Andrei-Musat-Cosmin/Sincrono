package it.sincrono.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BaseRepositoryImpl {

	@PersistenceContext
	protected EntityManager entityManager = null;

	/**
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}

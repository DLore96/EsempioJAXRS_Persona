package it.prova.hellojaxrspersona.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.hellojaxrspersona.model.Persona;

public class PersonaDAOImpl implements IPersonaDAO{

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Persona> list() throws Exception {
		return entityManager.createQuery("from Persona ", Persona.class).getResultList();
	}

	@Override
	public Optional<Persona> findOne(Long id) throws Exception {
		 Persona persona = entityManager.find(Persona.class, id);
		 return persona != null ? Optional.of(persona) : Optional.empty();
	}

	@Override
	public void update(Persona input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Persona input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Persona input) throws Exception {

		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	

}

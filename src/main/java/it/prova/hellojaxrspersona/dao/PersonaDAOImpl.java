package it.prova.hellojaxrspersona.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

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

	@Override
	public List<Persona> findByExample(Persona input) {

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select p from Persona p where 1 = 1 ");

		if (StringUtils.isNotEmpty(input.getNome())) {
			whereClauses.add(" p.nome  like :nome ");
			paramaterMap.put("nome", "%" + input.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(input.getCognome())) {
			whereClauses.add(" p.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + input.getCognome() + "%");
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Persona> typedQuery = entityManager.createQuery(queryBuilder.toString(), Persona.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	

}

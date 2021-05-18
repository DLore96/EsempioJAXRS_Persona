package it.prova.hellojaxrspersona.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.hellojaxrspersona.dao.IPersonaDAO;
import it.prova.hellojaxrspersona.model.Persona;
import it.prova.hellojaxrspersona.web.listener.LocalEntityManagerFactoryListener;

public class PersonaServiceImpl implements IPersonaService {
	
	private IPersonaDAO personaDAO;

	@Override
	public void setPersonaDAO(IPersonaDAO personaDAO) {

		this.personaDAO=personaDAO;
	}

	@Override
	public List<Persona> listAll() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			personaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return personaDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Persona caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			personaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return personaDAO.findOne(id).get();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Persona input) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			personaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			personaDAO.update(input);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Persona input) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			personaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			personaDAO.insert(input);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Persona input) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
	
			entityManager.getTransaction().begin();

			personaDAO.setEntityManager(entityManager);
			input=entityManager.merge(input);
			personaDAO.update(input);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}



}

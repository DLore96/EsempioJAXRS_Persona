package it.prova.hellojaxrspersona.service;

import java.util.List;

import it.prova.hellojaxrspersona.dao.IPersonaDAO;
import it.prova.hellojaxrspersona.model.Persona;

public interface IPersonaService {
	
	public List<Persona> listAll() throws Exception;

	public Persona caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Persona input) throws Exception;

	public void inserisciNuovo(Persona input) throws Exception;

	public void rimuovi(Persona input) throws Exception;
	
	public List<Persona> findByExample(Persona input)throws Exception;
	
	
	//Injection
	public void setPersonaDAO(IPersonaDAO personaDAO);

}

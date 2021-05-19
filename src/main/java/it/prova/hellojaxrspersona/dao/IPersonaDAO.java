package it.prova.hellojaxrspersona.dao;

import java.util.List;

import it.prova.hellojaxrspersona.model.Persona;

public interface IPersonaDAO extends IBaseDAO<Persona> {
	
	public List<Persona> findByExample(Persona input);

}

package it.prova.hellojaxrspersona.service;

import it.prova.hellojaxrspersona.dao.IPersonaDAO;
import it.prova.hellojaxrspersona.dao.PersonaDAOImpl;

public class MyServiceFactory {
	
	private static IPersonaService PERSONA_SERVICE_INSTANCE = null;
	private static IPersonaDAO PERSONA_DAO_INSTANCE = null;

	public static IPersonaService getPersonaServiceInstance() {
		if (PERSONA_SERVICE_INSTANCE == null)
			PERSONA_SERVICE_INSTANCE = new PersonaServiceImpl();

		if (PERSONA_DAO_INSTANCE == null)
			PERSONA_DAO_INSTANCE = new PersonaDAOImpl();

		PERSONA_SERVICE_INSTANCE.setPersonaDAO(PERSONA_DAO_INSTANCE);
		return PERSONA_SERVICE_INSTANCE;
	}
}

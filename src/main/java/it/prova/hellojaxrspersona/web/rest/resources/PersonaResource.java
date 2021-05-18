package it.prova.hellojaxrspersona.web.rest.resources;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.prova.hellojaxrspersona.model.Persona;
import it.prova.hellojaxrspersona.service.IPersonaService;
import it.prova.hellojaxrspersona.service.MyServiceFactory;


@Path("/persona")
public class PersonaResource {
	
	private static final Logger LOGGER = Logger.getLogger(PersonaResource.class.getName());

	@Context
	HttpServletRequest request;
	
	private IPersonaService personaServiceInstance;
	
	public PersonaResource() {
		personaServiceInstance = MyServiceFactory.getPersonaServiceInstance();
	}
	
	//METODI PER LE VARIE RISORSE
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Persona> result= new ArrayList<Persona>();
		try {
			result = personaServiceInstance.listAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(result).build();
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertPersona(Persona personaInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		try {
			personaServiceInstance.inserisciNuovo(personaInput);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(personaInput).build();
	}

}

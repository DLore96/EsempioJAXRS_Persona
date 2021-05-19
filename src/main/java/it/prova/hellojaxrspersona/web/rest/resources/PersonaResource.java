package it.prova.hellojaxrspersona.web.rest.resources;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

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
			ObjectMapper objectMapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
			objectMapper.setDateFormat(df);
			String risultato = objectMapper.writeValueAsString(result);
			return Response.status(200).entity(risultato).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Qualcosa è andato storto");
		}
		
		

		
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
	
	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cercaPerId(@PathParam("id") Long id) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Persona personaInstance= new Persona();
		try {
			personaInstance = personaServiceInstance.caricaSingoloElemento(id);
			
			ObjectMapper objectMapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
			objectMapper.setDateFormat(df);
			String risultato = objectMapper.writeValueAsString(personaInstance);
			return Response.status(200).entity(risultato).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Qualcosa è andato storto");
		}
		
		

	}
	
	@DELETE
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response rimuoviPersona(@PathParam("id") Long id) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Persona personaInstance;
		try {
			
			personaInstance = personaServiceInstance.caricaSingoloElemento(id);
			if( personaInstance!=null) {
				
				personaServiceInstance.rimuovi(personaInstance);
				return Response.status(200).entity("Rimossa Persona con id: "+id).build();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response aggiornaPersona(Persona personaInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		try {
			personaServiceInstance.aggiorna(personaInput);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(personaInput).build();
	}
	
	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchPersona(Persona personaInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		try {
			List<Persona> result = personaServiceInstance.findByExample(personaInput);

			ObjectMapper objectMapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
			objectMapper.setDateFormat(df);
			String risultato = objectMapper.writeValueAsString(result);

			return Response.status(200).entity(risultato).build();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Qualcosa è andato storto");
		}
	}

}

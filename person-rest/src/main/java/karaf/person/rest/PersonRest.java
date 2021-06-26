package karaf.person.rest;


import karaf.person.model.Person;
import karaf.person.model.PersonResponse;
import karaf.person.service.PersonService;

import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("")
public class PersonRest {
    private final PersonService personService;

    private final PersonResponse getPersonResponse = new PersonResponse().getPersonResponse();

    public PersonRest(PersonService personService) {
        this.personService = personService;
    }


    @GET
    @Consumes("application/json")
    @Path("/")
    public Response allPerson() {
        return Response.status(Response.Status.OK).entity(personService.allPerson()).build();
    }


    @GET
    @Consumes("application/json")
    @Path("/{id}")
    public Response getPersonId(@PathParam("id") int id) {
        try {
            if (!personService.checkId(id)) {
                return Response.status(Response.Status.NOT_FOUND).entity(getPersonResponse.errorPerson("Not found this person")).build();
            } else {
                Person person = personService.getPersonById(id);
                return Response.status(Response.Status.OK).entity(person).build();
            }
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(getPersonResponse.errorPerson("Not found this person")).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Path("/")
    public Response addPerson(Person person) {
        try {
            personService.addPerson(person);
            return Response.status(Response.Status.OK).entity(getPersonResponse.successPerson("Add success")).build();
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(getPersonResponse.errorPerson(e.getMessage())).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") int id, Person person) {
        boolean bol = personService.checkId(id);
        if (!bol) {
            return Response.status(Response.Status.NOT_FOUND).entity(getPersonResponse.errorPerson("Not exist id person")).build();
        } else {
            personService.updatePerson(person, id);
            return Response.status(Response.Status.OK).entity(getPersonResponse.successPerson("Update id = " + id + " success")).build();
        }
    }

    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        boolean bol = personService.checkId(id);
        if (bol == false) {
            return Response.status(Response.Status.NOT_FOUND).entity(getPersonResponse.errorPerson("Not exist id person")).build();
        } else {
            personService.deletePerson(id);
            return Response.status(Response.Status.OK).entity(getPersonResponse.successPerson("Delete id = " + id + " success")).build();
        }
    }
}

package rest;

import domain.Journey;
import domain.Step;
import domain.User;
import service.JourneyService;
import service.StepService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

@Path("Step")
@Stateless
public class StepResource {

    @Context
    SecurityContext securityContext;

    @Inject
    private UserService uS;
    @Inject
    private StepService sS;

    @Inject
    private JourneyService jS;

    @GET
    @Path("{stepname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Step getStep(@PathParam("stepname") String name) {
        return sS.findByName(name);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Step createStep(Step step) {
        sS.addStep(step);
        return step;
    }
    @GET
    @Path("search/{journeyname}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Step> getJourneyByUser(@PathParam("journeyname") String journeyname) {
        Journey journey = jS.findByName(journeyname);
        return sS.findByJourney(journey);
    }
    @DELETE
    @Path("{name}")
    public void deleteJourney(@PathParam("name")String name) {
        sS.removeStep(name);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/like")
    public Response likeKweet(@PathParam("id") int id) {
        try {
            User user = getUserFromToken();
            Step step =  sS.likeStep(user, id);

            return Response.status(Response.Status.OK).entity(step).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/unlike")
    public Response unlikeKweet(@PathParam("id") int id) {
        try {
            User user = getUserFromToken();
            Step step =  sS.unlikeStep(user, id);

            return Response.status(Response.Status.OK).entity(step).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }
    }

    private User getUserFromToken() {
        Principal principal = securityContext.getUserPrincipal();
        String username = principal.getName();

        return uS.findByName(username);
    }
}

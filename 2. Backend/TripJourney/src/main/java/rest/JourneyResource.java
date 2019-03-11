package rest;

import domain.Journey;
import domain.User;
import service.JourneyService;
import service.UserService;
import util.JourneyJson;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("Journey")
@Stateless
public class JourneyResource {
    @Inject
    private JourneyService jS;

    @Inject
    private UserService uS;

    @GET
    @Path("get/{journeyname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Journey getJourney(@PathParam("journeyname") String name) {
        return jS.findByName(name);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public void createJourney(Journey j) {
        jS.addJourney(j);
    }
    @GET
    @Path("search/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Journey> getJourneyByUser(@PathParam("username") String username) {
        User user = uS.findByName(username);
        return jS.findByUsername(user);
    }
    @DELETE
    @Path("/delete/{name}")
    public void deleteJourney(@PathParam("name")String name) {
        jS.removeJourney(name);
    }
}

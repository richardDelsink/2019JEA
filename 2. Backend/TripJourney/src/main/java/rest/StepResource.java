package rest;

import domain.Journey;
import domain.Step;
import service.JourneyService;
import service.StepService;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Step")
@Stateless
public class StepResource {

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
}

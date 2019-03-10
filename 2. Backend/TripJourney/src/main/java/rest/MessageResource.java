package rest;

import domain.Message;
import domain.Step;
import service.MessageService;
import service.StepService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Message")
@Stateless
public class MessageResource {

    @Inject
    private MessageService mS;

    @Inject
    private StepService sS;

    @GET
    @Path("{messagecontext}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message getComment(@PathParam("messagecontext") String messagecontext) {
        return mS.findByName(messagecontext);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Message createComment(Message comment) {
        mS.addComment(comment);
        return comment;
    }
    @GET
    @Path("search/{comments}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getCommentByStep(@PathParam("comments") String name) {
        Step step = sS.findByName(name);
        return mS.findByStep(step);
    }
    @DELETE
    @Path("{name}")
    public void deleteComment(@PathParam("name")String name) {
        mS.removeComment(name);
    }
}

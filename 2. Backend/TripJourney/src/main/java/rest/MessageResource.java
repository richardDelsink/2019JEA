package rest;

import domain.Comment;
import domain.Step;
import service.MessageService;
import service.StepService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Comment")
@Stateless
public class MessageResource {

    @Inject
    private MessageService mS;

    @Inject
    private StepService sS;

    @GET
    @Path("{messagecontext}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment getComment(@PathParam("messagecontext") String messagecontext) {
        return mS.findByName(messagecontext);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Comment createComment(Comment comment) {
        mS.addComment(comment);
        return comment;
    }
    @GET
    @Path("search/{comments}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getCommentByStep(@PathParam("comments") String name) {
        Step step = sS.findByName(name);
        return mS.findByStep(step);
    }
    @DELETE
    @Path("{name}")
    public void deleteComment(@PathParam("name")String name) {
        mS.removeComment(name);
    }
}

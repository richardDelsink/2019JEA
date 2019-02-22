package rest;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("User")
@Stateless
public class UserResource {

    @Inject
    private UserService uS;

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("name") String name) {
        User user = uS.findByName(name);
        return user;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public User createStudent(User user) {
        uS.addUser(user);
        return user;
    }

    @DELETE
    @Path("{name}")
    public void deleteUser(@PathParam("name")String name) {
        uS.removeUser(name);
    }

}

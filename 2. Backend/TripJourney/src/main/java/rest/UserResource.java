package rest;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Principal;
import java.util.List;


@Path("User")
@Stateless
public class UserResource {
    @Context
    SecurityContext securityContext;

    @Inject
    private UserService uS;

    @GET
    @Path("get/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("name") String name) {
        try {
            return Response.status(Response.Status.OK).entity(new GenericEntity<User>(uS.findByName((name))) {}).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createUser(User user) {
        try {
            uS.addUser(user);
            return Response.status(Response.Status.OK).entity("Success").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }
    }

    @DELETE
    @Path("delete/{name}")
    public Response deleteUser(@PathParam("name")String name) {
        try {
            uS.removeUser(name);
            return Response.status(Response.Status.OK).entity("Success").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }
    }

    @GET
    @Path("{username}/followers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowers(@PathParam("username") String name) {
        try {
            List<User> followers = uS.getFollowers(name);
            return Response.status(Response.Status.OK).entity(new GenericEntity<List<User>>(followers) {}).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }
    }
// lager in  dan de rest laag tevoegen , aanpassen
    @POST
    @Path("{username}/follow")
    public Response followUser(@PathParam("username") String username) {
        try {
            User user = getUserFromToken();
            uS.followUser(user, username);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }

    }
    @GET
    @Path("{username}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowing(@PathParam("username") String name) {
        try {
            List<User> following = uS.getFollowing(name);
            return Response.status(Response.Status.OK).entity(new GenericEntity<List<User>>(following) {}).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong").build();
        }
    }

    @POST
    @Path("{username}/unfollow")
    public Response unfollowUser(@PathParam("username") String username) {
        try {
            User user = getUserFromToken();
            uS.unfollowUser(user, username);
            return Response.status(Response.Status.NO_CONTENT).build();
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

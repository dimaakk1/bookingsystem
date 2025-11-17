package org.example.user.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.user.model.UserEntity;
import org.example.user.service.UserService;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        UserEntity user = userService.getById(id);

        if (user == null) {
            return Response.status(404).entity("User not found").build();
        }

        return Response.ok(user).build();
    }

    @POST
    public Response create(UserEntity user) {
        UserEntity created = userService.create(user);
        return Response.status(201).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UserEntity updates) {
        UserEntity updated = userService.update(id, updates);

        if (updated == null) {
            return Response.status(404).entity("User not found").build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean removed = userService.delete(id);

        if (!removed) {
            return Response.status(404).entity("User not found").build();
        }

        return Response.noContent().build();
    }
}

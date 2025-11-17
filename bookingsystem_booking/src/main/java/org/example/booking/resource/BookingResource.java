package org.example.booking.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.booking.client.*;
import org.example.booking.model.Booking;

import java.util.List;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    @Inject
    @RestClient
    UserClient userClient;

    @Inject
    @RestClient
    EventClient eventClient;


    @POST
    @Transactional
    public Response createBooking(CreateBookingRequest request) {

        UserDTO user = userClient.getUserById(request.userId);
        if (user == null) {
            return Response.status(404).entity("User not found").build();
        }

        EventDTO event = eventClient.getEventById(request.eventId);
        if (event == null) {
            return Response.status(404).entity("Event not found").build();
        }

        Booking booking = new Booking(
                request.userId,
                request.eventId,
                "CONFIRMED"
        );

        booking.persist();

        return Response.ok(booking).build();
    }


    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Booking booking = Booking.findById(id);

        if (booking == null) {
            return Response.status(404).entity("Booking not found").build();
        }

        return Response.ok(booking).build();
    }


    @GET
    public Response getAll() {
        return Response.ok(Booking.listAll()).build();
    }


    @PUT
    @Path("/{id}/status")
    @Transactional
    public Response updateStatus(@PathParam("id") Long id, String newStatus) {
        Booking booking = Booking.findById(id);

        if (booking == null) {
            return Response.status(404).entity("Booking not found").build();
        }

        booking.status = newStatus;
        return Response.ok(booking).build();
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Booking.deleteById(id);

        if (!deleted) {
            return Response.status(404).entity("Booking not found").build();
        }

        return Response.noContent().build();
    }
}

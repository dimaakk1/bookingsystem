package org.example.event.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.event.model.Event;

import java.util.List;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    @GET
    public List<Event> getAll() {
        return Event.listAll();
    }

    @GET
    @Path("/{id}")
    public Event getById(@PathParam("id") Long id) {
        return Event.findById(id);
    }

    @POST
    @Transactional
    public Event create(Event event) {
        event.id = null;
        event.persist();
        return event;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Event event = Event.findById(id);
        if (event != null) event.delete();
    }
}

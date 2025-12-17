package org.example.booking.producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.example.booking.dto.BookingEvent;

@ApplicationScoped
public class BookingEventProducer {

    @Inject
    @Channel("booking-created")
    Emitter<String> emitter;

    public void sendBookingEvent(BookingEvent event) {
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(event);
        emitter.send(json);

    }
}

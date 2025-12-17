package org.example.user.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.example.user.dto.BookingEvent;
import org.example.user.model.UserEntity;
import org.example.user.repository.UserRepository;


@ApplicationScoped
public class BookingConsumer {

    @Inject
    UserRepository userRepository;

    @Incoming("booking-created")
    @Transactional
    public void consume(String message) {
        Jsonb jsonb = JsonbBuilder.create();
        BookingEvent event = jsonb.fromJson(message, BookingEvent.class);

        UserEntity user = userRepository.findById(event.userId);

        if (user == null) {
            System.out.println("User not found for booking event");
            return;
        }

        user.totalBookings++;

        System.out.println(
                "User " + user.id + " bookings updated: " + user.totalBookings
        );
    }


}
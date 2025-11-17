package org.example.booking.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Booking extends PanacheEntity {

    public Long userId;
    public Long eventId;
    public String status;

    public Booking() {}

    public Booking(Long userId, Long eventId, String status) {
        this.userId = userId;
        this.eventId = eventId;
        this.status = status;
    }
}
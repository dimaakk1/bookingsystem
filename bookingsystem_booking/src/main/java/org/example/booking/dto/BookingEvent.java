package org.example.booking.dto;

public class BookingEvent {
    public Long bookingId;
    public Long userId;
    public Long eventId;
    public String status;

    public BookingEvent() {}

    public BookingEvent(Long bookingId, Long userId, Long eventId, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.eventId = eventId;
        this.status = status;
    }
}

package com.hotel.booking.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(int id) {
        super("Bokning med ID " + id + " hittades inte.");
    }
}
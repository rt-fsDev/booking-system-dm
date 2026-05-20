package com.hotel.booking.exception;

public class GuestCapacityException extends RuntimeException {
    public GuestCapacityException(String roomType, int maxCapacity, int requestedGuests) {
        super("Rumstypen " + roomType + " har plats för max " + maxCapacity + " gäster. Du försökte boka för " + requestedGuests + " gäster.");
    }
}
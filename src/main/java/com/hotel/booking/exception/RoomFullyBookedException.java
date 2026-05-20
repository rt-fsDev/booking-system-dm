package com.hotel.booking.exception;

public class RoomFullyBookedException extends RuntimeException {
    public RoomFullyBookedException(String roomType) {
        super("Inga " + roomType + " kvar. Alla rum av denna typ är fullbokade.");
    }
}
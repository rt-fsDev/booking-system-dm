package com.hotel.booking.dto;

import jakarta.validation.constraints.*;

public class BookingRequest {
    @NotBlank(message = "Namn får inte vara tomt")
    private String guestName;

    @NotBlank(message = "Rumstyp krävs")
    private String roomType;

    @Min(value = 1, message = "Minst 1 gäst")
    @Max(value = 3, message = "Max 3 gäster")
    private int numberOfGuests;

    // Getters and Setters
    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
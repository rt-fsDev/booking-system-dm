package com.hotel.booking.model;

import jakarta.validation.constraints.*;

public class Booking {
    private static int counter = 1;
    private int id;

    @NotBlank(message = "Namn får inte vara tomt")
    @Pattern(regexp = "^[A-Öa-ö\\s]+$", message = "Namn får bara innehålla bokstäver och mellanslag")
    private String guestName;

    @NotNull(message = "Rumstyp krävs")
    private RoomType roomType;

    @Min(value = 1, message = "Minst 1 gäst")
    @Max(value = 3, message = "Max 3 gäster")
    private int numberOfGuests;

    private int totalPrice;

    // Constructor
    public Booking() {}

    public Booking(String guestName, RoomType roomType, int numberOfGuests) {
        this.id = counter++;
        this.guestName = guestName;
        this.roomType = roomType;
        this.numberOfGuests = numberOfGuests;
        this.totalPrice = roomType.pricePerNight;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
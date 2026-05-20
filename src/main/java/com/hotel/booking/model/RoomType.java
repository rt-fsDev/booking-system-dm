package com.hotel.booking.model;

public enum RoomType {
    SINGLE("Enkelrum", 1, 500),
    DOUBLE("Dubbelrum", 2, 1000),
    SUITE("Svit", 3, 2000);

    public final String svName;
    public final int capacity;
    public final int pricePerNight;

    RoomType(String svName, int capacity, int pricePerNight) {
        this.svName = svName;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
    }

    public static RoomType fromSvName(String svName) {
        for (RoomType type : values()) {
            if (type.svName.equals(svName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Okänd rumstyp: " + svName);
    }
}
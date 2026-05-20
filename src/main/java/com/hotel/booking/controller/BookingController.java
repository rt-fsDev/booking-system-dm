package com.hotel.booking.controller;

import com.hotel.booking.dto.BookingRequest;
import com.hotel.booking.model.Booking;
import com.hotel.booking.model.RoomType;
import com.hotel.booking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Map<String, Object>>> getAvailableRooms() {
        List<Map<String, Object>> response = new ArrayList<>();
        Map<RoomType, Integer> available = bookingService.getAllAvailableRooms();

        for (Map.Entry<RoomType, Integer> entry : available.entrySet()) {
            Map<String, Object> roomInfo = new HashMap<>();
            roomInfo.put("roomType", entry.getKey().svName);
            roomInfo.put("capacity", entry.getKey().capacity);
            roomInfo.put("available", entry.getValue());
            roomInfo.put("pricePerNight", entry.getKey().pricePerNight);
            response.add(roomInfo);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bookings")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping("/bookings")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingRequest request) {
        RoomType roomType = RoomType.fromSvName(request.getRoomType());
        Booking booking = bookingService.createBooking(
                request.getGuestName(),
                roomType,
                request.getNumberOfGuests()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    @DeleteMapping("/bookings/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
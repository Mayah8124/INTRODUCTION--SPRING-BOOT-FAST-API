package org.springBoot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingController {

    private List<Booking> bookingList = new ArrayList<>();

    // 5.a retrieve a booking list saved
    @GetMapping("/booking")
    public List<Booking> getBooking() {
        return bookingList;
    }

    // 5.b create a booking list
    @PostMapping("/booking")
    public ResponseEntity<List<Booking>> createBooking(@RequestParam Booking booking) {
        bookingList.add(booking);
        return ResponseEntity.ok(bookingList);
    }
}

package org.springBoot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    // 5.c get available room
    @GetMapping("/{roomNumber}/available")
    public List<Booking> getAvailableBooking(@PathVariable int roomWanted, @RequestParam LocalDate requestDate) {
        LocalDate requestedDate = LocalDate.parse(requestDate.toString());

        Boolean isAvailable = bookingList.stream()
                .filter(booking -> booking.roomNumber() == roomWanted)
                .anyMatch(booking -> booking.date() != null && booking.date().equals(requestDate));

        if (isAvailable) {
            return (List<Booking>) ResponseEntity.status(409).body("room already reserved");
        } else  {
            return (List<Booking>) ResponseEntity.ok("room available");
        }
    }
}

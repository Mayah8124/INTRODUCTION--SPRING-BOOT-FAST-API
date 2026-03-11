package org.springBoot;

import org.springframework.http.HttpStatus;
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
    public List<Booking> getAvailableBooking(@PathVariable int roomNumber, @RequestParam LocalDate requestDate) {
        LocalDate requestedDate = LocalDate.parse(requestDate.toString());

        // 5.d verification of room number
        if (roomNumber < 1 || roomNumber > 9) {
            return (List<Booking>) ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Room numbers must be between 1 and 9.");
        }

        boolean isAvailable = bookingList.stream()
                .anyMatch(booking -> booking.roomNumber() == roomNumber && booking.date().isEqual(requestedDate));

        if (isAvailable) {
            return (List<Booking>) ResponseEntity.status(409).body("room already reserved");
        } else  {
            return (List<Booking>) ResponseEntity.ok("room available");
        }
    }
}

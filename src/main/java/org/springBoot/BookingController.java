package org.springBoot;

import org.springframework.web.bind.annotation.GetMapping;
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
}

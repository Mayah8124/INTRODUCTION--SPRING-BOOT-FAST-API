package org.springBoot;

import java.time.LocalDate;

public record Booking(
        String clientName,
        String phone,
        String email,
        int roomNumber,
        String roomDescription,
        LocalDate date) {
}

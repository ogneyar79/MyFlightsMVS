package com.gridnine.testing.predicate;

import com.gridnine.testing.Flight;


import java.time.LocalDateTime;

import java.util.function.Predicate;


public class FlightAfter implements Predicate<Flight> {
    private final LocalDateTime dateAfterFlight;

    public FlightAfter(LocalDateTime dateAfterFlight) {
        this.dateAfterFlight = dateAfterFlight;
    }

    @Override
    public boolean test(Flight flight) {
        long result = flight.getSegments().stream().filter(el -> el.getDepartureDate().isAfter(this.dateAfterFlight)).count();
        if (result > 0) {
            return true;
        }
        return false;
    }
}

package com.gridnine.testing.checkingFlight;

import com.gridnine.testing.FilterFlight;
import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.predicate.FlightAfter;
import com.gridnine.testing.predicate.IncorrectSegment;
import com.gridnine.testing.predicate.WaitingTime;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    /**
     * Checking class to except from test list some flights.
     */
    public static void main(String... args) {
        LocalDateTime timeNow = LocalDateTime.now();
        List<Flight> flightList = FlightBuilder.createFlights();
        FilterFlight filter = new FilterFlight();
        Predicate<Flight> predicate = new FlightAfter(timeNow);

        // We are excepting a flight departing in the past
        filter.filter(flightList, predicate).stream().forEach(System.out::println);

        //  //We are excepting a flight that departs before it arrives
        predicate = new IncorrectSegment();
        filter.filter(flightList, predicate).stream().forEach(System.out::println);

        // with more than two hours ground time if change ">" on ">=" we have correct result.
        predicate = new WaitingTime(2);
        filter.filter(flightList, predicate).stream().forEach(System.out::println);
    }
}

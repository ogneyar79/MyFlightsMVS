package com.gridnine.testing.checkingFlight;

import com.gridnine.testing.model.FilterFlight;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.predicate.FlightAfter;
import com.gridnine.testing.predicate.IncorrectSegment;
import com.gridnine.testing.predicate.WaitingTime;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

/**
 * Checking class to except from test list some flights.
 */
public class Main {
    public static void main(String... args) {
        LocalDateTime timeNow = LocalDateTime.now();
        List<Flight> flightList = FlightBuilder.createFlights();
        FilterFlight filter = new FilterFlight();
        Predicate<Flight> predicate = new FlightAfter(timeNow);

        // We are excepting a flight departing in the past
        System.out.println(" We are excepting flights that was at pass until date  timeNow: " + timeNow);
        System.out.println();
        filter.filter(flightList, predicate).stream().forEach(System.out::println);

        //  //We are excepting a flight that departs before it arrives
        System.out.println();
        System.out.println(" We are excepting flights that was incorrect \"that departs before it arrives:\"");
        predicate = new IncorrectSegment();
        filter.filter(flightList, predicate).stream().forEach(System.out::println);

        // with more than two hours ground time
        System.out.println();
        System.out.println("We are excepting flights with more than two hours ground time: ");
        predicate = new WaitingTime(2);
        filter.filter(flightList, predicate).stream().forEach(System.out::println);
    }
}

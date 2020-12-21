package com.gridnine.testing;

import com.gridnine.testing.predicate.FlightAfter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * We test here class that cut all flights before point, hire time now..
 */
public class FilterFlightTest {

    FlightAfter predicate;
    Flight testFlight;

    List<Flight> flightList;
    FilterFlight filter;
    LocalDateTime timeNow;

    @Before
    public void install() {
        filter = new FilterFlight();
        flightList = FlightBuilder.createFlights();
        timeNow = LocalDateTime.now();
        predicate = new FlightAfter(timeNow);
    }

    @Test
    public void filter() {
        List coll = filter.filter(flightList, predicate);
        System.out.println(coll.size() + " количиесво ранних рейсов");
        ArrayList list = new ArrayList();
        System.out.println(coll.get(0) + " Отобранный ранний рейс");
        list.add(new Segment(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2)));
        testFlight = (Flight) coll.stream().findAny().orElse(new Flight(list));

        boolean real = testFlight.getSegments().get(0).getDepartureDate().isAfter(timeNow);
        System.out.println(real);
        assertThat(real, is(true));
    }
}
package com.gridnine.testing;

import com.gridnine.testing.model.FilterFlight;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.WaitingTime;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * We test here method of class that cut flights where ground time more than two hours.
 */
public class FilterTimeWaitTest {

    WaitingTime predicate;
    long testTime = 2;      // ?exactly 2 hours or more then 2 hours?.
    FilterFlight filter;
    List<Flight> flightList;

    @Before
    public void install() {
        filter = new FilterFlight();
        flightList = FlightBuilder.createFlights();
        predicate = new WaitingTime(testTime);
    }

    @Test
    public void filter() {
        List coll = filter.filter(flightList, predicate);
        ArrayList list = new ArrayList();

        list.add(new Segment(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2)));
        int quantityFlightAfterFilter = coll.size();
        int expected = 4;
        assertThat(quantityFlightAfterFilter, is(expected));
    }
}

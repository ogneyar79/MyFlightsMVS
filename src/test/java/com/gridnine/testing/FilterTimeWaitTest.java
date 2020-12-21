package com.gridnine.testing;

import com.gridnine.testing.predicate.FlightAfter;
import com.gridnine.testing.predicate.WaitingTime;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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

    // ??????????????????????? NOW TEST WILL FAIL   ????????????????????????????????????    NOW TEST WILL FAIL.
    // Here we have incorrect task, because You gave two flights how with more than two hours ground time
    // but we have exactly 2 hours in -> createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
    //                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),   (6-4)=2
    //                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));

    // with more than two hours ground time if change ">" on ">=" at our  method test we have correct result.

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

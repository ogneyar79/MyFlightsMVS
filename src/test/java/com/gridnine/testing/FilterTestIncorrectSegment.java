package com.gridnine.testing;

import com.gridnine.testing.model.FilterFlight;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.predicate.IncorrectSegment;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * We test here method of class that cut flights where as example departs before it arrives.
 */
public class FilterTestIncorrectSegment {

    IncorrectSegment predicate;
    List<Flight> flightList;
    FilterFlight filter;

    @Before
    public void install() {
        filter = new FilterFlight();
        flightList = FlightBuilder.createFlights();
        predicate = new IncorrectSegment();
    }

    @Test
    public void filter() {
        System.out.println("Size of Collection before filter" + flightList.size());
        List coll = filter.filter(flightList, predicate);
        System.out.println(coll.size() + "quantity of flights  after method ");
        int quantityFlightAfterFilter = coll.size();
        int expected = 5;

        assertThat(quantityFlightAfterFilter, is(expected));
    }
}

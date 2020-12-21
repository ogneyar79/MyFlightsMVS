package com.gridnine.testing.predicate;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FlightAfterTest {
    FlightAfter predicate;
    LocalDateTime testTime = LocalDateTime.now();
    Flight testFlight;
    List<Segment> segments;
    Segment segmentFirst;
    Segment segmentSecond;

    @Before
    public void install() {
        segments = new ArrayList<>(4);
        testFlight = new Flight(segments);
        predicate = new FlightAfter(testTime);
    }

    @Test
    public void testWhenFlightAfterNow() {
        segmentFirst = new Segment(LocalDateTime.now().minusDays(8),LocalDateTime.now().minusDays(7));
        segmentSecond = new Segment(LocalDateTime.now().minusDays(7),LocalDateTime.now().minusHours(6));
        segments.add(segmentFirst);
        segments.add(segmentSecond);

        boolean real =  predicate.test(testFlight);
        assertThat(real, is(false));
    }

    @Test
    public void testWhenFlightBeforeNow() {
        segmentFirst = new Segment(LocalDateTime.now().plusHours(3),LocalDateTime.now().plusHours(9));
        segmentSecond = new Segment(LocalDateTime.now().plusHours(12),LocalDateTime.now().minusHours(20));
        segments.add(segmentFirst);
        segments.add(segmentSecond);

        boolean real =  predicate.test(testFlight);
        assertThat(real, is(true));
    }
}
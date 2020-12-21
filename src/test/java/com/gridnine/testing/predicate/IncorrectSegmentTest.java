package com.gridnine.testing.predicate;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IncorrectSegmentTest {

    IncorrectSegment predicate;
    Flight testFlight;
    List<Segment> segments;
    Segment segmentFirst;
    Segment segmentSecond;

    @Before
    public void install() {
        segments = new ArrayList<>(4);
        testFlight = new Flight(segments);
        predicate = new IncorrectSegment();
    }

    @Test
    public void testWhenFlightHasIncorrectTimeAtOneSegment() {
        segmentFirst = new Segment(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(1));
        segmentSecond = new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4));
        segments.add(segmentFirst);
        segments.add(segmentSecond);

        boolean real = predicate.test(testFlight);
        assertThat(real, is(false));
    }

    @Test
    public void testWhenFlightHasIncorrectTimeBetweenSegments() {
        segmentFirst = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(9));
        segmentSecond = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        segments.add(segmentFirst);
        segments.add(segmentSecond);

        boolean real = predicate.test(testFlight);
        assertThat(real, is(false));
    }
    @Test
    public void testFlightWithOneSegment() {
        segmentFirst = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(9));
        segments.add(segmentFirst);

        boolean real = predicate.test(testFlight);
        assertThat(real, is(true));
    }

}
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

public class WaitingTimeTest {

    WaitingTime predicate;
    long testTime = 2;
    Flight testFlight;
    List<Segment> segments;
    Segment segmentFirst;
    Segment segmentSecond;


    @Before
    public void install() {
        segments = new ArrayList<>(4);
        testFlight = new Flight(segments);
        predicate = new WaitingTime(testTime);
    }

    @Test
    public void whenTimeWaitingMoreThenTestTime() {
        segmentFirst = new Segment(LocalDateTime.now().minusDays(8), LocalDateTime.now().minusDays(7));
        segmentSecond = new Segment(LocalDateTime.now().minusDays(6), LocalDateTime.now().minusDays(6));
        segments.add(segmentFirst);
        segments.add(segmentSecond);

        boolean real = predicate.test(testFlight);
        assertThat(real, is(false));
    }
    @Test
    public void whenTimeWaitingLessThenTestTime() {
        segmentFirst = new Segment(LocalDateTime.now().minusDays(8), LocalDateTime.now().minusDays(7));
        segmentSecond = new Segment(LocalDateTime.now().minusDays(7), LocalDateTime.now().minusDays(6));
        segments.add(segmentFirst);
        segments.add(segmentSecond);

        boolean real = predicate.test(testFlight);
        assertThat(real, is(true));
    }
}
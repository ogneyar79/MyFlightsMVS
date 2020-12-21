package com.gridnine.testing.predicate;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WaitingTime implements Predicate<Flight> {

    private final long timeWaiting;

    public WaitingTime(long timeWaiting) {
        this.timeWaiting = timeWaiting;
    }

    @Override
    public boolean test(Flight flight) {
        ArrayList<Segment> result = flight.getSegments().stream().collect(Collectors.toCollection(ArrayList::new));
        List<Segment> list = flight.getSegments();
        for (int i = 0; i < result.size() - 1; i++) {
            long sumTime = 0;
            Segment segmentFirst = list.get(i);
            Segment segmentSecond = list.get(i + 1);

            long hours = segmentFirst.getArrivalDate().until(segmentSecond.getDepartureDate(), ChronoUnit.HOURS);
            sumTime = ++hours;
            if (sumTime > timeWaiting) {
                return false;
            }
        }
        return true;
    }
}
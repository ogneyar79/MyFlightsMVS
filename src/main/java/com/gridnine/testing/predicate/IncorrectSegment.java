package com.gridnine.testing.predicate;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IncorrectSegment implements Predicate<Flight> {

    /**
     * This function look for flight with incorrect segments, if have this return false.
     */
    @Override
    public boolean test(Flight flight) {
        ArrayList<Segment> result = flight.getSegments().stream().collect(Collectors.toCollection(ArrayList::new));
        List<Segment> list = flight.getSegments();
        for (int i = 0; i < result.size() - 1; i++) {
            Segment segmentFirst = list.get(i);
            Segment segmentSecond = list.get(i + 1);
            if (segmentFirst.getDepartureDate().isAfter(segmentSecond.getArrivalDate())) {
                return false;
            }
        }
        return flight.getSegments().stream().filter(el -> el.getDepartureDate().isAfter(el.getArrivalDate())).count() > 0 ? false : true;
    }

}

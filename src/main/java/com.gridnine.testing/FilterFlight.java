package com.gridnine.testing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterFlight {
    public List<Flight> filter(Collection<Flight> target, Predicate<Flight> predicate) {
        return target.stream().filter(flight -> predicate.test(flight)).collect(Collectors.toList());
    }
}

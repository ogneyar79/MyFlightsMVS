package com.gridnine.testing.model;

import com.gridnine.testing.model.Flight;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Interface makes filter.
 */
public interface IFilterFlight<T> {
    List<Flight> filter(Collection<Flight> target, Predicate<T> predicate);
}

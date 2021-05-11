package pl.orlowski.model;

import lombok.Builder;

import javax.persistence.Entity;
import java.time.LocalDate;

@Builder
public class Report {

    Long totalAmountOfFuelConsumption;
    Long totalPriceForFuel;
    int kilometerStatus;
    double averageFuelConsumption;
    LocalDate lastRefueling;
}

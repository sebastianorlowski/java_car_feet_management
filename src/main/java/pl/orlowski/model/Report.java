package pl.orlowski.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class Report {

    Long totalAmountOfFuelConsumption;
    Double totalPriceForFuel;
    int kilometerStatus;
    double averageFuelConsumption;
    LocalDate lastRefueling;
}

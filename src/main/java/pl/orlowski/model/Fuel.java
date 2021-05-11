package pl.orlowski.model;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

@Entity
@Table(name = "fuels")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class Fuel {

    public Fuel(Long id, LocalDate dateRefueling, int kilometerStatus, double price,
                double pricePerLiter, double amountOfFuel, Car car, Owner owner) {
        this.id = id;
        this.dateRefueling = dateRefueling;
        this.kilometerStatus = kilometerStatus;
        this.price = price;
        this.pricePerLiter = pricePerLiter;
        this.amountOfFuel = amountOfFuel;
        this.car = car;
        this.owner = owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateRefueling;
    private int kilometerStatus;
    private double price;
    private double pricePerLiter;
    private double amountOfFuel;

    @ManyToOne
    @JoinColumn
    private Car car;

    @ManyToOne
    @JoinColumn
    private Owner owner;

}

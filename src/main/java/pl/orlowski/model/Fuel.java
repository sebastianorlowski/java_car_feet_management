package pl.orlowski.model;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "fuels")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class Fuel {

    public Fuel(Long id, Date dateRefueling, int kilometerStatus, float price, float fuelPerLiter, Car car) {
        this.id = id;
        this.dateRefueling = dateRefueling;
        this.kilometerStatus = kilometerStatus;
        this.price = price;
        this.fuelPerLiter = fuelPerLiter;
        this.car = car;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateRefueling;
    private int kilometerStatus;
    private float price;
    private float fuelPerLiter;

    @ManyToOne
    @JoinColumn
    private Car car;


}

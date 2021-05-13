package pl.orlowski.model;

import lombok.*;
import javax.persistence.*;

@Builder
@Entity
@Table(name = "cars")
@Getter
@Setter
@RequiredArgsConstructor
public class Car {

    public Car(Long id, String registration, String brand, String model,
               FuelType fuelType, String engineCapacity, String enginePower) {
        this.id = id;
        this.registration = registration;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.engineCapacity = engineCapacity;
        this.enginePower = enginePower;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registration;
    private String brand;
    private String model;
    private FuelType fuelType;
    private String engineCapacity;
    private String enginePower;

}

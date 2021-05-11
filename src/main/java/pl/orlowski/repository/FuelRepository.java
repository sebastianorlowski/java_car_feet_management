package pl.orlowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orlowski.model.Car;
import pl.orlowski.model.Fuel;

import java.util.List;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {

    List<Fuel> findFuelByCar(Car car);
}

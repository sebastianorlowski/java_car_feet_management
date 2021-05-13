package pl.orlowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orlowski.model.Car;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findCarByRegistration(String registration);

    List<Car> findCarByBrand(String brand);
}

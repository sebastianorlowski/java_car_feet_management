package pl.orlowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orlowski.model.Fuel;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {

}

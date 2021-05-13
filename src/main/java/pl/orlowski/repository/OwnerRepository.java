package pl.orlowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.orlowski.model.Car;
import pl.orlowski.model.Owner;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findOwnerByCar(Car car);

    Owner findOwnerByEmail(String email);

    Owner findOwnerByPhoneNumber(String phoneNumber);

    Owner findOwnerByPesel(Long pesel);

    boolean existsByPesel(Long pesel);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

}

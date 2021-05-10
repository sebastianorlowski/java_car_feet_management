package pl.orlowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orlowski.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findOwnerByCarId(Long id);
}

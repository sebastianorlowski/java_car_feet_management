package pl.orlowski.validation.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.orlowski.model.Owner;
import pl.orlowski.repository.CarRepository;

@Component
@RequiredArgsConstructor
public class OwnerNullValidation {

    private final CarRepository carRepository;

    public Owner ownerIsNull(Owner owner) {
        if (owner.getCar() == null) {
            owner.setCar(carRepository.findCarByRegistration("0"));
        }
        return owner;
    }
}

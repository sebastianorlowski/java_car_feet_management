package pl.orlowski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orlowski.model.Car;
import pl.orlowski.model.Owner;
import pl.orlowski.repository.OwnerRepository;
import pl.orlowski.validation.owner.OwnerNullValidation;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerNullValidation ownerNullValidation;

    public void save(Owner owner) {
        owner = ownerNullValidation.ownerIsNull(owner);
        ownerRepository.save(owner);
    }

    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    public void edit(Owner owner) {
        Owner newOwner = Owner.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .email(owner.getEmail())
                .pesel(owner.getPesel())
                .phoneNumber(owner.getPhoneNumber())
                .car(owner.getCar())
                .build();

        save(newOwner);
    }

    public Owner getOwnerByCar(Car car) {
        return ownerRepository.findOwnerByCar(car);
    }

    public Owner getOwnerByPeselOrPhoneOrEmail(String value) {
        if (value.matches("\\d+")) {
            if (ownerRepository.existsByPhoneNumber(value)) {
                return ownerRepository.findOwnerByPhoneNumber(value);
            } else if (ownerRepository.existsByPesel(Long.parseLong(value))) {
                return ownerRepository.findOwnerByPesel(Long.parseLong(value));
            } else {
                return null;
            }
        } else if (ownerRepository.existsByEmail(value)) {
            return ownerRepository.findOwnerByEmail(value);
        }
        return null;
    }

    public List<Car> getOwnersWithCar() {
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream()
                .map(Owner::getCar)
                .collect(Collectors.toList());
    }
}

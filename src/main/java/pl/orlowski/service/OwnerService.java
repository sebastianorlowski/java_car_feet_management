package pl.orlowski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orlowski.model.Owner;
import pl.orlowski.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public void save(Owner owner) {
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
                .phoneNumber(owner.getPhoneNumber())
                .car(owner.getCar())
                .build();

        delete(owner);
        save(newOwner);
    }

    public Owner getOwnerByCarId(Long id) {
        return ownerRepository.findOwnerByCarId(id);
    }

}

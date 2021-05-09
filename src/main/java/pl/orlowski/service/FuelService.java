package pl.orlowski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orlowski.model.Fuel;
import pl.orlowski.repository.FuelRepository;

@Service
@RequiredArgsConstructor
public class FuelService {

    private final FuelRepository fuelRepository;

    public void save(Fuel fuel) {
        fuelRepository.save(fuel);
    }

    public void delete(Fuel fuel) {
        fuelRepository.delete(fuel);
    }

    public void editFuel(Fuel fuel) {
        Fuel newFuel = Fuel.builder()
                .id(fuel.getId())
                .dateRefueling(fuel.getDateRefueling())
                .car(fuel.getCar())
                .price(fuel.getPrice())
                .fuelPerLiter(fuel.getFuelPerLiter())
                .build();

        delete(fuel);
        save(newFuel);
    }
}

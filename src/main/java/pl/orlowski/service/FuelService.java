package pl.orlowski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orlowski.model.Fuel;
import pl.orlowski.repository.FuelRepository;

import java.text.DecimalFormat;

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
                .pricePerLiter(fuel.getPricePerLiter())
                .build();

        delete(fuel);
        save(newFuel);
    }

    public double amountOfFuelCalculate(double price, double pricePerLiter) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.parseDouble(decimalFormat.format(price / pricePerLiter));
    }
}

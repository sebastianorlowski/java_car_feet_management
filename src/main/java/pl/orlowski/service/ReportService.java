package pl.orlowski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orlowski.model.Car;
import pl.orlowski.model.Fuel;
import pl.orlowski.model.Report;
import pl.orlowski.repository.CarRepository;
import pl.orlowski.repository.FuelRepository;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final CarRepository carRepository;
    private final FuelRepository fuelRepository;

    public Report getTotalReportCar(String registration) {
        List<Fuel> info = getFuelInformation(registration);

        return Report.builder()
                .totalAmountOfFuelConsumption(getTotalAmountOfFuelConsumption(info))
                .totalPriceForFuel(getTotalPriceForFuel(info))
                .kilometerStatus(getKilometerStatus(info))
                .averageFuelConsumption(getAverageFuelConsumption(info))
                .lastRefueling(getLastRefueling(info))
                .build();
    }

    public List<Fuel> getFuelInformation(String registration) {
        Car car = carRepository.findCarByRegistration(registration);
        return fuelRepository.findFuelByCar(car);
    }

    public Long getTotalAmountOfFuelConsumption(List<Fuel> fuels) {
        if (!fuels.isEmpty()) {
            return fuels.stream()
                    .map(Fuel::getAmountOfFuel)
                    .reduce(Double::sum)
                    .get()
                    .longValue();
        }
        return 0L;
    }

    public Double getTotalPriceForFuel(List<Fuel> fuels) {
        if (!fuels.isEmpty()) {
            return fuels.stream()
                    .map(Fuel::getPrice)
                    .reduce(Double::sum)
                    .get();
        }
        return 0.0;
    }

    public Integer getKilometerStatus(List<Fuel> fuels) {
        if (!fuels.isEmpty()) {
            return fuels.stream()
                    .map(Fuel::getKilometerStatus)
                    .reduce((first, second) -> second)
                    .get();
        }
        return 0;
    }

    public Double getAverageFuelConsumption(List<Fuel> fuels) {
        double kilometer = fuels.get(fuels.size() - 1).getKilometerStatus() - fuels.get(0).getKilometerStatus();
        kilometer /= 100;
        double totalFuel = getTotalAmountOfFuelConsumption(fuels) - fuels.get(fuels.size() - 1).getAmountOfFuel();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.parseDouble(decimalFormat.format(totalFuel / kilometer));
    }

    public LocalDate getLastRefueling(List<Fuel> fuels) {
        Fuel fuel = fuels.get(fuels.size() - 1);
        return fuel.getDateRefueling();
    }
}

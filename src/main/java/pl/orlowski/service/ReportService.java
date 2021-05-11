package pl.orlowski.service;

import lombok.RequiredArgsConstructor;
import pl.orlowski.model.Car;
import pl.orlowski.model.Fuel;
import pl.orlowski.model.Report;
import pl.orlowski.repository.CarRepository;
import pl.orlowski.repository.FuelRepository;
import pl.orlowski.repository.OwnerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

@RequiredArgsConstructor
public class ReportService {

    private final CarRepository carRepository;
    private final FuelRepository fuelRepository;
    private final OwnerRepository ownerRepository;

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
         return Long.valueOf(String.valueOf(fuels.stream()
                .map(Fuel::getAmountOfFuel)
                .reduce(Double::sum)
                .get()));
    }

    public Long getTotalPriceForFuel(List<Fuel> fuels) {
        return Long.valueOf(String.valueOf(fuels.stream()
                .map(Fuel::getPrice)
                .reduce(Double::sum)
                .get()));
    }

    public Integer getKilometerStatus(List<Fuel> fuels) {
        return fuels.stream()
                .map(Fuel::getKilometerStatus)
                .reduce(Integer::sum)
                .get();
    }

    public Double getAverageFuelConsumption(List<Fuel> fuels) {
        double kilometer = fuels.get(fuels.size() - 1).getKilometerStatus() - fuels.get(0).getKilometerStatus();
        kilometer /= 100;
        Long totalFuel = getTotalAmountOfFuelConsumption(fuels);
        return totalFuel / kilometer;
    }

    public LocalDate getLastRefueling(List<Fuel> fuels) {
        Fuel fuel = fuels.get(fuels.size() - 1);
        return fuel.getDateRefueling();
    }
}

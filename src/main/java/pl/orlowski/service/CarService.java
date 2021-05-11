package pl.orlowski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orlowski.model.Car;
import pl.orlowski.model.FuelType;
import pl.orlowski.repository.CarRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public void save(Car car) {
        carRepository.save(car);
    }

    public void delete(Car car) {
        carRepository.delete(car);
    }

    public void editCar(Car car) {
        Car newCar = Car.builder()
                .id(car.getId())
                .registration(car.getRegistration())
                .brand(car.getBrand())
                .model(car.getModel())
                .engineCapacity(car.getEngineCapacity())
                .enginePower(car.getEnginePower())
                .fuelType(car.getFuelType())
                .build();

        delete(car);
        save(newCar);
    }

    public Car getCarByRegistration(String registration) {
        return carRepository.findCarByRegistration(registration);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findCarByBrand(brand);
    }

    public List<Car> getCarsByFuelType(FuelType fuelType) {
        return carRepository.findCarByFuelType(fuelType);
    }
}

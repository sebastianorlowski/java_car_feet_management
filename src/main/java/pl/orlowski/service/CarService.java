package pl.orlowski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orlowski.model.Car;
import pl.orlowski.model.Owner;
import pl.orlowski.repository.CarRepository;
import pl.orlowski.repository.OwnerRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;

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

    public List<String> getCarsByRegistration() {
        return getAllCars().stream()
                .map(Car::getRegistration)
                .collect(Collectors.toList());
    }

    public List<String> getCarsWithoutOwnerByRegistration() {

        List<String> cars = carRepository.findAll().stream()
                .map(Car::getRegistration)
                .collect(Collectors.toList());

        List<Owner> ownersWithCar = ownerRepository.findAll();

        List<String> carWithOwner = ownersWithCar.stream()
                .map(Owner::getCar)
                .map(Car::getRegistration)
                .collect(Collectors.toList());

        cars.removeAll(carWithOwner);

        return cars;
    }
}

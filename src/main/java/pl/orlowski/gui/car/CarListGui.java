package pl.orlowski.gui.car;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;
import pl.orlowski.gui.MainGui;
import pl.orlowski.model.Car;
import pl.orlowski.service.CarService;

import java.util.List;

@Route(value = "", layout = MainGui.class)
public class CarListGui extends VerticalLayout {

    public CarListGui(CarService carService) {
        TreeGrid<Car> car = new TreeGrid<>();

        List<Car> cars = carService.getAllCars();
        cars.remove(0);
        car.setItems(cars);
        car.addHierarchyColumn(Car::getRegistration)
                .setHeader("Registration");

        car.addColumn(Car::getBrand)
                .setHeader("Brand");
        car.addColumn(Car::getModel)
                .setHeader("Model");
        car.addColumn(Car::getFuelType)
                .setHeader("Fuel type");

        add(car);
    }
}

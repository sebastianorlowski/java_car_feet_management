package pl.orlowski.gui.car;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;
import pl.orlowski.gui.MainGui;
import pl.orlowski.model.Car;
import pl.orlowski.service.CarService;

@Route(value = "", layout = MainGui.class)
public class CarListGui extends VerticalLayout {

    public CarListGui(CarService carService) {
        TreeGrid<Car> car = new TreeGrid<>();

        car.setItems(carService.getAllCars());
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

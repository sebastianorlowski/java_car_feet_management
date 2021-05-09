package pl.orlowski.gui.car;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;
import pl.orlowski.model.Car;
import pl.orlowski.service.CarService;

@Route("car/list")
public class CarListGui extends VerticalLayout {

    public CarListGui(CarService carService) {
        TreeGrid<Car> car = new TreeGrid<>();

        car.setItems(carService.getAllCars());
        car.addHierarchyColumn(Car::getRegistration)
                .setHeader("Registration");

        car.addHierarchyColumn(Car::getBrand)
                .setHeader("Brand");
        car.addColumn(Car::getModel)
                .setHeader("Model");

        add(car);
    }
}

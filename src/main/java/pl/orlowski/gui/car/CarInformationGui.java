package pl.orlowski.gui.car;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.orlowski.model.Car;
import pl.orlowski.model.Owner;
import pl.orlowski.service.CarService;
import pl.orlowski.service.OwnerService;

@Route
public class CarInformationGui extends VerticalLayout {

    public CarInformationGui(CarService carService,
                             OwnerService ownerService) {
        TextField textFieldGetCarByRegistration = new TextField("Find car by registration");
        Button buttonFindCar = new Button("Find car", new Icon(VaadinIcon.SEARCH));

        buttonFindCar.addClickListener(buttonClickEvent -> {

            String carRegistration = textFieldGetCarByRegistration.getValue();
            Car car = carService.getCarByRegistration(carRegistration);

            new Span("Car: " + car.getBrand() + " " + car.getModel()),
                    new Span("Fuel type: " + car.getFuelType().getType()),
                    new Span("Engine capacity and power: " + car.getEngineCapacity() + "cm3 |" +
                            car.getEnginePower() + "kW")

            Details carDetails = new Details(new Span("Car information"),
                    new Span("Car registration: " + carRegistration + "\n" +
                            "Car: " + car.getBrand() + " " + car.getModel() + "\n" +
                            "Fuel type: " + car.getFuelType().getType() + "\n" +
                            "Engine capacity and power: " + car.getEngineCapacity() + "cm3 |" +
                            car.getEnginePower() + "kW"));

            Owner owner = ownerService.getOwnerByCarId(car.getId());

            Details ownerDetails = new Details("Owner information",
                    new Text("Person: " + owner.getFirstName() + " " + owner.getLastName() + "\n" +
                    "Phone: " + owner.getPhoneNumber() + "\n" +
                    "Email: " + owner.getEmail() + "\n" +
                    "PESEL" + owner.getPesel()));

            
        });
    }
}

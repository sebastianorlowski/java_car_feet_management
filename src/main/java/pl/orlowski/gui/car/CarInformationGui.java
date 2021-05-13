package pl.orlowski.gui.car;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.orlowski.gui.MainGui;
import pl.orlowski.model.Car;
import pl.orlowski.model.Owner;
import pl.orlowski.service.CarService;
import pl.orlowski.service.OwnerService;

import java.util.Collection;
import java.util.List;

@Route(value = "car/info", layout = MainGui.class)
@SpringComponent
public class CarInformationGui extends VerticalLayout {

    private final OwnerService ownerService;
    private final CarService carService;

    @Autowired
    public CarInformationGui(OwnerService ownerService,
                             CarService carService) {
        this.ownerService = ownerService;
        this.carService = carService;

        findCarContentByDialog();
    }

    public void findCarContentByDialog() {
        TextField textFieldGetCarByRegistration = new TextField();
        textFieldGetCarByRegistration.setPlaceholder("Registration");
        Button buttonFindCar = new Button("Find car", new Icon(VaadinIcon.SEARCH));

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(textFieldGetCarByRegistration);
        horizontalLayout.add(buttonFindCar);

        add(horizontalLayout);

        buttonFindCar.addClickListener(buttonClickEvent -> {

            String registration = textFieldGetCarByRegistration.getValue();

            Car car = carService.getCarByRegistration(registration);

            Span info = new Span("Car information");
            Span spanCarRegistration = new Span("Car registration: " + registration);
            Span spanCar = new Span("Car: " + car.getBrand() + " " + car.getModel());
            Span spanFuelType = new Span("Fuel type: " + car.getFuelType().getType());
            Span spanEngineCapacityAndPower = new Span("Engine capacity and power: " + car.getEngineCapacity() + "cm3 | " +
                    car.getEnginePower() + "kW");

            textFieldGetCarByRegistration.clear();

            VerticalLayout content = new VerticalLayout(info, spanCarRegistration, spanCar,
                    spanFuelType, spanEngineCapacityAndPower);

            Dialog dialog = new Dialog(content);

            Button buttonFindOwner = new Button("Get owner", new Icon(VaadinIcon.MALE));

            buttonFindOwner.addClickListener(buttonClickEvent1 -> {
                Owner owner = ownerService.getOwnerByCar(car);

                if (owner != null) {

                    Span spanPerson = new Span("Person: " + owner.getFirstName() + " " + owner.getLastName());
                    Span spanPhone = new Span("Phone: " + owner.getPhoneNumber());
                    Span spanEmail = new Span("Email: " + owner.getEmail());
                    Span spanPesel = new Span("PESEL: " + owner.getPesel());

                    VerticalLayout contentOwner = new VerticalLayout(spanPerson,
                            spanPhone, spanEmail, spanPesel);

                    Dialog dialogOwner = new Dialog(contentOwner);

                    Button cancelButton = new Button("Cancel", event -> {
                        dialogOwner.close();
                    });

                    dialogOwner.add(cancelButton);
                    dialogOwner.open();
                } else {
                    Notification notification = new Notification(
                            "This car is without owner", 2000,
                            Notification.Position.MIDDLE);
                    notification.open();
                }

            });

            Button cancelButton = new Button("Cancel", event -> {
                dialog.close();
            });

            dialog.add(buttonFindOwner, cancelButton);
            dialog.open();
        });
    }

}

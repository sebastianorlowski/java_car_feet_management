package pl.orlowski.gui.car;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.orlowski.gui.MainGui;
import pl.orlowski.model.Car;
import pl.orlowski.model.FuelType;
import pl.orlowski.service.CarService;

import java.util.Collections;

@Route(value = "car", layout = MainGui.class)
public class CarGui extends VerticalLayout {

    public CarGui(CarService carService) {

        TextField textFieldRegistration = new TextField("Registration: ");
        TextField textFieldBrand = new TextField("Brand: ");
        TextField textFieldModel = new TextField("Model: ");
        TextField textFieldEngineCapacity = new TextField("Engine capacity: ");
        TextField textFieldEnginePower = new TextField("Engine power(kw): ");
        ComboBox<FuelType> comboBoxFuelType = new ComboBox<>("Type:", FuelType.values());
        Button buttonAddCar = new Button("Add new car", new Icon(VaadinIcon.CAR));
        Label labelAddCar = new Label();

        buttonAddCar.addClickListener(buttonClickEvent -> {
            Car car = Car.builder()
                    .registration(textFieldRegistration.getValue())
                    .brand(textFieldBrand.getValue())
                    .model(textFieldModel.getValue())
                    .engineCapacity(textFieldEngineCapacity.getValue())
                    .enginePower(textFieldEnginePower.getValue())
                    .fuelType(comboBoxFuelType.getValue())
                    .build();

            carService.save(car);
            textFieldRegistration.clear();
            textFieldBrand.clear();
            textFieldModel.clear();
            textFieldEngineCapacity.clear();
            textFieldEnginePower.clear();
            comboBoxFuelType.clear();
            labelAddCar.setText("Youre add new car!");
        });

        add(textFieldRegistration,
                textFieldBrand,
                textFieldModel,
                textFieldEngineCapacity,
                textFieldEnginePower,
                comboBoxFuelType,
                buttonAddCar);
    }
}

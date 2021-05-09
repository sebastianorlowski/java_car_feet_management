package pl.orlowski.gui.car;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.orlowski.model.Car;
import pl.orlowski.model.FuelType;
import pl.orlowski.service.CarService;

@Route("car-edit")
public class CarEditGui extends VerticalLayout {

    public CarEditGui(CarService carService) {

        TextField textFieldFindCar = new TextField("Get car by registration");
        Button buttonFindCar = new Button("Find car", new Icon(VaadinIcon.CAR));

        TextField textFieldId = new TextField("ID: ");
        textFieldId.setReadOnly(true);
        TextField textFieldRegistration = new TextField("Registration: ");
        TextField textFieldBrand = new TextField("Brand: ");
        TextField textFieldModel = new TextField("Model: ");
        TextField textFieldEngineCapacity = new TextField("Engine capacity: ");
        TextField textFieldEnginePower = new TextField("Engine power(kw): ");
        ComboBox<FuelType> comboBoxFuelType = new ComboBox<>("Type:", FuelType.values());
        Button buttonUpdateCar = new Button("Add new car", new Icon(VaadinIcon.CAR));
        Label labelUpdateCar = new Label();

        buttonFindCar.addClickListener(buttonClickEvent -> {
            Car car = carService.getCarByRegistration(textFieldFindCar.getValue());
            textFieldId.setValue(String.valueOf(car.getId()));
            textFieldRegistration.setValue(car.getRegistration());
            textFieldBrand.setValue(car.getBrand());
            textFieldModel.setValue(car.getModel());
            textFieldEngineCapacity.setValue(car.getEngineCapacity());
            textFieldEnginePower.setValue(car.getEnginePower());
            comboBoxFuelType.setValue(car.getFuelType());
        });

        buttonUpdateCar.addClickListener(buttonClickEvent -> {
            Car car = Car.builder()
                    .id(Long.valueOf(textFieldId.getValue()))
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
            labelUpdateCar.setText("Car has been updated!");
        });

        add(textFieldFindCar,
                buttonFindCar,

                textFieldRegistration,
                textFieldBrand,
                textFieldModel,
                textFieldEngineCapacity,
                textFieldEnginePower,
                comboBoxFuelType,
                buttonUpdateCar);
    }
}
